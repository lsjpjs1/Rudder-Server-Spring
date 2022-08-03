package com.example.restapimvc.pre.party.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PartyScheduler {
        @Scheduled(cron = "0 * * * * ?")
        public void testSchedule(){
            log.info("test");
        }
    }
