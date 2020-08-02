package me.kalpha.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContinuousJobService {
    public void step1() {
        log.info(">>>>이것은 step1 입니다");
    }
    public void step2() {
        log.info(">>>>이것은 step3 입니다");
    }
    public void step3() {
        log.info(">>>>이것은 step3 입니다");
    }
}
