package com.example.restapimvc.payment;

import com.example.restapimvc.pre.chat.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
}
