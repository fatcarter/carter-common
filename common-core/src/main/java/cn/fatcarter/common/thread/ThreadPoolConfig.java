package cn.fatcarter.common.thread;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.RejectedExecutionHandler;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThreadPoolConfig {
    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Long keepAliveMillis;
    private Integer queueSize;
    private String threadNameFormat;
    private RejectedExecutionHandler rejectedExecutionHandler;
}
