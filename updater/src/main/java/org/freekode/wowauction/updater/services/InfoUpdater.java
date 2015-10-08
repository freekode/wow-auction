package org.freekode.wowauction.updater.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowauction.persistence.models.ItemEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InfoUpdater {
    private static final Logger logger = LogManager.getLogger(InfoUpdater.class);


    public void updateInfo(List<ItemEntity> items) {

    }
}
