package com.example.restapimvc.enums;

import com.example.restapimvc.dto.MailDTO;

public interface ConvertibleToMailRequest {
    public MailDTO.MailRequest getMailRequest(String receiverEmail, String payload);
}
