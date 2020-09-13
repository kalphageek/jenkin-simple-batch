package me.kalpha.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Task1Service {
    Logger log = LoggerFactory.getLogger(this.getClass());
    public void task1 (String requestDate) {
        log.info(">>>>>> This is Task1 Step");
        log.info(">>>>>> requestDate = {}", requestDate);
    }
}
