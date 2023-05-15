package cn.fatcarter.common.log;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;

@AllArgsConstructor
public class Slf4jLogging implements Logging {
    private Logger logger;

    @Override
    public void trace(String format, Object... args) {
        if (logger.isTraceEnabled()) {
            logger.trace(format, args);
        }
    }

    @Override
    public void info(String format, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(format, args);
        }
    }

    @Override
    public void debug(String format, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, args);
        }
    }

    @Override
    public void warn(String format, Object... args) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, args);
        }
    }

    @Override
    public void error(String format, Throwable throwable, Object... args) {
        if (logger.isErrorEnabled()) {
            logger.error(throwable.getMessage(), throwable);
            if (args != null && args.length > 0) {
                logger.error(format, args);
            }
        }
    }
}
