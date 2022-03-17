package com.example.restapimvc.enums;

import com.example.restapimvc.dto.MailDTO;
import lombok.Getter;

@Getter
public enum MailRequestEnum implements ConvertibleToMailRequest {
    FORGOT_USER_ID("Your Rudder ID","ID : "){
        @Override
        public MailDTO.MailRequest getMailRequest(String receiverEmail,String userId){
            return MailDTO.MailRequest.builder()
                    .receiverEmail(receiverEmail)
                    .subject(getSubject())
                    .body(getBody()+userId)
                    .build();
        }
    },
    FORGOT_USER_PASSWORD("Your new Rudder password","Password : "){
        @Override
        public MailDTO.MailRequest getMailRequest(String receiverEmail,String newUserPassword){
            return MailDTO.MailRequest.builder()
                    .receiverEmail(receiverEmail)
                    .subject(getSubject())
                    .body(getBody()+newUserPassword)
                    .build();
        }
    }
    ;
    private String subject;
    private String body;
    MailRequestEnum(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

}
