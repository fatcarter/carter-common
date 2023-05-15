package cn.fatcarter.common.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.RejectedExecutionHandler;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadPoolConfig {
    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Long keepAliveMillis;
    private Integer queueSize;
    private String threadNameFormat;
    private RejectedExecutionHandler rejectedExecutionHandler;
}
