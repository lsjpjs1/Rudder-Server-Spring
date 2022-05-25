package com.example.restapimvc.repository;

import com.example.restapimvc.domain.PostMessage;
import com.example.restapimvc.domain.PostMessageRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMessageRepository extends JpaRepository<PostMessage,Long>{
}
