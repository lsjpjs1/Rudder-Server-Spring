package com.example.restapimvc.payment;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.domain.ChatRoom;
import com.example.restapimvc.pre.chat.domain.ChatRoomMember;
import com.example.restapimvc.util.ObjectMappingUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Transactional
    public void payment(UserInfo userInfo, PaymentDto.PaymentRequest paymentRequest) {
        String url = "https://connect.squareupsandbox.com/v2/payments";

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory()); // 비동기 전달
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Square-Version","2022-06-16");
        httpHeaders.add("Authorization", "Bearer EAAAEPYy3qdzymtQrwIRGOGNhVJpsYCtL-o2QsqfD5PzkD7YlynX-6dH885_Zm97");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        PaymentDto.SquarePaymentRequest squarePaymentRequest = PaymentDto.SquarePaymentRequest.builder()
                .accessToken(paymentRequest.getSourceId())
                .idempotencyKey(UUID.randomUUID().toString())
                .amountMoney(PaymentDto.SquarePaymentRequest.AmountMoney.builder().amount(paymentRequest.getAmount()).currency("USD").build())
                .build();

        HttpEntity<String> logRequest = new HttpEntity<>(objectMapper.writeValueAsString(squarePaymentRequest), httpHeaders);
        ResponseEntity<String> stringResponseEntity;
        try{
            stringResponseEntity = restTemplate.postForEntity(url, logRequest, String.class);
            System.out.println(stringResponseEntity.getBody());
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
