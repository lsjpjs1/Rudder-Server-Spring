package com.example.restapimvc.pre.chat.repository;

import com.example.restapimvc.pre.chat.domain.ChatMessage;
import com.example.restapimvc.pre.chat.domain.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
