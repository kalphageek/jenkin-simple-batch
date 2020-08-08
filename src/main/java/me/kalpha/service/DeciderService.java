package me.kalpha.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeciderService {
    public void oddStep() {
        log.info(">>>>> Odd Step");
    };

    public void evenStep() {
        log.info(">>>>> Even Step");
    }
}
