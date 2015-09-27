package org.freekode.wowauction.engine.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

public class TestTask {
    private static final Logger logger = LogManager.getLogger(TestTask.class);


    @Scheduled(cron = "*/30 * * * * ?")
    public void test() {
        logger.info("gogogo!!!!!!!!!!");
    }
}
