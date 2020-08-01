package me.kalpha.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Task1Service {
    public void task1 (String requestDate) {
        log.info(">>>>>> This is Task1 Step");
        log.info(">>>>>> requestDate = {}", requestDate);
    }
}
