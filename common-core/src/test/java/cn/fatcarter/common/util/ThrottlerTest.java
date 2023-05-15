package cn.fatcarter.common.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Duration;
import java.util.Scanner;

@Slf4j
public class ThrottlerTest {


    @Test
    public void testThrottler() throws InterruptedException {
        int i = 20;
        for (int i1 = 0; i1 < i; i1++) {
            log.info("调用节流器 " + i1);
            Throttler.throttle(this.getClass().getName(), () -> {
                log.info("Actual run!");
            }, Duration.ofSeconds(1));
            Thread.sleep(200);
        }
        Thread.sleep(3000);
    }
}
