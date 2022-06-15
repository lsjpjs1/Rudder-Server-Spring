package com.example.restapimvc.pre.chat.repository;

import com.example.restapimvc.pre.chat.domain.ChatRoomMember;
import com.example.restapimvc.pre.party.command.domain.PartyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {

    @Query(value = "select * from " +
            "(select distinct on (crm.chat_room_id) " +
            "crm.chat_room_id, cm.chat_message_id, cm.message_body, cm.message_time, " +
            "(select count(*) from chat_message where chat_room_id = cm.chat_room_id and message_time>crm.latest_read_time) " +
            "from chat_room_member crm " +
            "left join chat_message cm on cm.chat_room_id = crm.chat_room_id " +
            "where crm.user_info_id = (:userInfoId) " +
            "order by crm.chat_room_id,cm.chat_message_id desc) as res " +
            "order by res.chat_message_id desc "
            , nativeQuery = true)
    List<Tuple> findChatRooms(@Param("userInfoId") Long userInfoId);

    Optional<ChatRoomMember> findByChatRoomIdAndUserInfoId(Long chatRoomId, Long userInfoId);
}
