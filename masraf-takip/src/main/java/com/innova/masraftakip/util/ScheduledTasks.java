package com.innova.masraftakip.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 5000)
    public void dailyAggregate() {
        System.out.println("Günlük görev çalıştı ");
    }

    @Scheduled(fixedRate = 10000)
    public void weeklyAggregate() {
        System.out.println("Haftalık görev çalıştı ");
    }

    @Scheduled(fixedRate = 15000)
    public void monthlyAggregate() {
        System.out.println("Aylık görev çalıştı ");
    }
}
