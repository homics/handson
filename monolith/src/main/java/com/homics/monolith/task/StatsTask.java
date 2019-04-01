package com.homics.monolith.task;

import com.homics.monolith.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatsTask {

    private static final Logger log = LoggerFactory.getLogger(StatsTask.class);

    private StatsService statsService;

    public StatsTask(StatsService statsService) {
        this.statsService = statsService;
    }

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        log.info("Running the stats task");
        statsService.sendStats();
    }
}
