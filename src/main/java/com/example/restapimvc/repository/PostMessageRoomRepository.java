package com.example.restapimvc.repository;

import com.example.restapimvc.domain.PostMessage;
import com.example.restapimvc.domain.PostMessageRoom;
import com.example.restapimvc.domain.ReportReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostMessageRoomRepository extends JpaRepository<PostMessageRoom,Long> {


}
