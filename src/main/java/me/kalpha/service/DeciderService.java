package me.kalpha.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeciderService {
    Logger log = LoggerFactory.getLogger(this.getClass());
    public void oddStep() {
        log.info(">>>>> Odd Step");
    };

    public void evenStep() {
        log.info(">>>>> Even Step");
    }
}
