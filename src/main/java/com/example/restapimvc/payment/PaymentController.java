package com.example.restapimvc.payment;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.payment.dto.PaymentDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api( tags = "결제 관련")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "결제 요청")
    @PostMapping(value = "/payments")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<String> sendPayment(@RequestBody PaymentDto.PaymentRequest paymentRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        paymentService.payment(userInfoFromToken,paymentRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("hey");

    }

}
