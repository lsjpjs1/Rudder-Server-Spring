package com.example.restapimvc.pre.party.scheduler;

import com.example.restapimvc.notification.NotificationPayload;
import com.example.restapimvc.notification.SocketPayload;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyQueryRepository;
import com.example.restapimvc.notification.pushnotification.FCMNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PartyScheduler {


    private final PartyQueryRepository partyQueryRepository;
    private final FCMNotificationService fcmNotificationService;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void partyRecruitNotificationScheduler() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        setMidnight(calendar);
        Calendar calendar2 = Calendar.getInstance();
        setMidnight(calendar2);
        calendar2.add(Calendar.DATE,2);
        List<Party> parties = partyQueryRepository.findPartyByDate(new Timestamp(calendar.getTimeInMillis()),new Timestamp(calendar2.getTimeInMillis()));
        parties.stream()
                .forEach(party -> {
                    try {
                        fcmNotificationService.sendMessage(party.getPartyHostUserInfo().getNotificationToken(), NotificationPayload.builder().build(),"오늘 파티 모집 종료됨","알겠냐");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });

    }

    @Scheduled(cron = "0 0 10 * * ?")
    @Transactional
    public void partyRatingNotificationScheduler() {

    }

    private void setMidnight(Calendar calendar){
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
    }
}
