package com.example.restapimvc.pre.chat.repository;

import com.example.restapimvc.pre.chat.domain.ChatMessage;
import com.example.restapimvc.pre.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
