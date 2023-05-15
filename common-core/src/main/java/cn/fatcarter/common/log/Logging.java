package cn.fatcarter.common.log;

public interface Logging {
    void trace(String format, Object... args);

    void info(String format, Object... args);

    void debug(String format, Object... args);

    void warn(String format, Object... args);

    void error(String format, Throwable throwable, Object... args);
}
