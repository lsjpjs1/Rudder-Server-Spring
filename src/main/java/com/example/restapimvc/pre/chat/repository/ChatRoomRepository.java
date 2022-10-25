package com.example.restapimvc.pre.chat.repository;

import com.example.restapimvc.pre.chat.domain.ChatMessage;
import com.example.restapimvc.pre.chat.domain.ChatRoom;
import com.example.restapimvc.pre.party.command.domain.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.sql.Timestamp;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
