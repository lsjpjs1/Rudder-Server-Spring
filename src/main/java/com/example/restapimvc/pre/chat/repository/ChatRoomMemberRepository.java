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
            "(select count(*) from chat_message where chat_room_id = cm.chat_room_id and message_time>crm.latest_read_time), " +
            "(select user_nickname from user_info where user_info_id!= (:userInfoId) and user_info_id in (select user_info_id from chat_room_member where chat_room_id = crm.chat_room_id)), " +
            "(select number_applicants from party_member where party_id = (:partyId) and user_info_id!= (:userInfoId) and user_info_id in (select user_info_id from chat_room_member where chat_room_id = crm.chat_room_id)), " +
            "(select user_info_id from user_info where user_info_id!= (:userInfoId) and user_info_id in (select user_info_id from chat_room_member where chat_room_id = crm.chat_room_id)) " +
            "from chat_room_member crm " +
            "left join chat_message cm on cm.chat_room_id = crm.chat_room_id " +
            "left join chat_room cr on cr.chat_room_id = crm.chat_room_id " +
            "where cr.chat_room_item_id = (:partyId) and chat_room_type = 'PARTY_ONE_TO_ONE' " +
            "order by crm.chat_room_id,cm.chat_message_id desc) as res " +
            "order by res.chat_message_id desc "
            , nativeQuery = true)
    List<Tuple> findHostPartyOneToOneChatRooms(@Param("partyId") Long partyId,@Param("userInfoId") Long userInfoId);

    @Query(value = "select * from " +
            "(select distinct on (crm.chat_room_id) " +
            "crm.chat_room_id, cm.chat_message_id, cm.message_body, cm.message_time, " +
            "(select count(*) from chat_message where chat_room_id = cm.chat_room_id and message_time>crm.latest_read_time), " +
            "(select user_nickname from user_info where user_info_id!= (:userInfoId) and user_info_id in (select user_info_id from chat_room_member where chat_room_id = crm.chat_room_id)), " +
            "(select user_info_id from user_info where user_info_id!= (:userInfoId) and user_info_id in (select user_info_id from chat_room_member where chat_room_id = crm.chat_room_id)) " +
            "from chat_room_member crm " +
            "left join chat_message cm on cm.chat_room_id = crm.chat_room_id " +
            "left join chat_room cr on cr.chat_room_id = crm.chat_room_id " +
            "left join party p on p.party_id = cr.chat_room_item_id " +
            "left join party_member pm on p.party_id = pm.party_id " +
            "where pm.user_info_id = (:userInfoId) and pm.party_status = 'PENDING' and cr.chat_room_type = 'PARTY_ONE_TO_ONE' " +
            "and cr.chat_room_id in (select distinct (crm2.chat_room_id) from chat_room_member crm2 where crm2.user_info_id=(:userInfoId)) " +
            "order by crm.chat_room_id,cm.chat_message_id desc) as res " +
            "order by res.chat_message_id desc "
            , nativeQuery = true)
    List<Tuple> findAppliedPartyOneToOneChatRooms(@Param("userInfoId") Long userInfoId);

    Optional<ChatRoomMember> findByChatRoomIdAndUserInfoId(Long chatRoomId, Long userInfoId);

    List<ChatRoomMember> findByChatRoomId(Long chatRoomId);

    @Query(value = "select * from " +
            "(select distinct on (crm.chat_room_id) " +
            "crm.chat_room_id, cm.chat_message_id, cm.message_body, cm.message_time, " +
            "(select count(*) from chat_message where chat_room_id = (:chatRoomId) and message_time>crm.latest_read_time) as not_read_message_count, " +
            "(select count(*) from chat_room_member where chat_room_id = (:chatRoomId)) as number_chat_room_member " +
            "from chat_room_member crm " +
            "left join chat_message cm on cm.chat_room_id = crm.chat_room_id " +
            "where crm.chat_room_id = (:chatRoomId) " +
            "order by crm.chat_room_id,cm.chat_message_id desc) as res " +
            "order by res.chat_message_id desc "
            , nativeQuery = true)
    Tuple findPartyGroupChatRoom(@Param("chatRoomId") Long chatRoomId);
}
