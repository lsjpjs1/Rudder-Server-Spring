package com.example.restapimvc.payment;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.payment.dto.PaymentDto;
import com.example.restapimvc.payment.dto.SquarePaymentResponse;
import com.example.restapimvc.repository.UserInfoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserInfoRepository userInfoRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final ObjectMapper objectMapper;


    @Value("${square.access-token}")
    private String SQUARE_ACCESS_TOKEN;

    private final String SQUARE_VERSION = "2022-06-16";
    @Transactional
    public void payment(UserInfo userInfo, PaymentDto.PaymentRequest paymentRequest) {
        String url = "https://connect.squareupsandbox.com/v2/payments";

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory()); // 비동기 전달
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Square-Version",SQUARE_VERSION);
        httpHeaders.add("Authorization", "Bearer "+SQUARE_ACCESS_TOKEN);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        String idempotencyKey = UUID.randomUUID().toString();
        PaymentDto.SquarePaymentRequest squarePaymentRequest = PaymentDto.SquarePaymentRequest.builder()
                .accessToken(paymentRequest.getSourceId())
                .idempotencyKey(idempotencyKey)
                .amountMoney(PaymentDto.SquarePaymentRequest.AmountMoney.builder().amount(paymentRequest.getAmount()).currency("USD").build())
                .build();


        try{
            HttpEntity<String> logRequest = new HttpEntity<>(objectMapper.writeValueAsString(squarePaymentRequest), httpHeaders);
            ResponseEntity<SquarePaymentResponse> responseEntity = restTemplate.postForEntity(url, logRequest, SquarePaymentResponse.class);
            SquarePaymentResponse squarePaymentResponse = responseEntity.getBody();
            PaymentHistory paymentHistory = PaymentHistory.builder()
                    .squarePaymentId(squarePaymentResponse.getPayment().getId())
                    .userInfo(userInfoRepository.findById(userInfo.getUserInfoId()).get())
                    .paymentTime(Timestamp.from(Instant.parse(squarePaymentResponse.getPayment().getCreatedAt())))
                    .idempotencyKey(idempotencyKey)
                    .build();
            paymentHistoryRepository.save(paymentHistory);
        }catch (Exception e){

            e.printStackTrace();
            throw new CustomException(ErrorCode.PAYMENT_FAIL);
        }



    }
}
