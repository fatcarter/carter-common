package cn.fatcarter.common.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class Date8Utils {
    public static DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Map<String, DateTimeFormatter> PATTERN_CACHE = new HashMap<>();
    private static final ZoneId TIME_ZONE = ZoneId.systemDefault();

    public static LocalDateTime fromTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, TIME_ZONE);
    }

    public static long toTimestamp(LocalDateTime dt) {
        return dt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(TIME_ZONE).toInstant());
    }

    public static Date toDate(LocalDate date) {
        return toDate(date.atStartOfDay());
    }

    public static LocalDateTime fromDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), TIME_ZONE);
    }

    public static LocalDateTime parseDate(String src, String pattern) {
        return parseDate(src, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseDate(String src, DateTimeFormatter formatter) {
        return LocalDateTime.parse(src, formatter);
    }

    public static LocalDateTime atStartOfDay(LocalDateTime date) {
        return Op.opn(date).map(LocalDateTime::toLocalDate).map(Date8Utils::atStartOfDay).orElse(null);
    }

    public static LocalDateTime atStartOfDay(LocalDate date) {
        return Op.opn(date).map(LocalDate::atStartOfDay).orElse(null);
    }

    public static LocalDateTime atEndOfDay(LocalDateTime date) {
        return Op.opn(date).map(LocalDateTime::toLocalDate).map(Date8Utils::atEndOfDay).orElse(null);
    }

    public static LocalDateTime atEndOfDay(LocalDate date) {
        return Op.opn(date).map(t -> t.atTime(LocalTime.MAX)).orElse(null);
    }

    public static Duration diff(LocalDateTime d1, LocalDateTime d2) {
        long diff = Date8Utils.toTimestamp(d2) - Date8Utils.toTimestamp(d1);
        return Duration.of(diff, ChronoUnit.MILLIS);
    }

    public static String format(LocalDateTime dateTime, DateTimeFormatter formatter) {
        return formatter.format(dateTime);
    }

    public static String format(LocalDateTime dateTime, String pattern) {
        return format(dateTime, PATTERN_CACHE.computeIfAbsent(pattern,DateTimeFormatter::ofPattern));
    }


    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return formatter.format(date);
    }

    public static String format(LocalDate date, String pattern) {
        return format(date, PATTERN_CACHE.computeIfAbsent(pattern, DateTimeFormatter::ofPattern));
    }

    /**
     * 获取指定日期所在月份的总天数
     *
     * @param date 日期
     * @return 日期所在月份的总天数
     */
    public static int getMaxDaysOfMonth(LocalDate date) {
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        return lastDay.getDayOfMonth();
    }

    /**
     * 获取指定日期所在月份的总天数
     *
     * @param dateTime 日期
     * @return 日期所在月份的总天数
     */
    public static int getMaxDaysOfMonth(LocalDateTime dateTime) {
        return getMaxDaysOfMonth(dateTime.toLocalDate());
    }

    /**
     * 将小时格式成HH:mm
     *
     * @param hour
     */
    public static String parseHourAndMinute(int hour) {
        if (hour < 1 || hour > 24) {
            return "";
        }
        return ((hour < 10) ? "0" + hour : hour) + ":00";
    }

    public static LocalDateTime min(LocalDateTime... times) {
        Assert.isTrue(times != null && times.length > 0, "times must not be empty");
        return Stream.of(times).min(LocalDateTime::compareTo).get();
    }

    public static LocalDate min(LocalDate... times) {
        Assert.isTrue(times != null && times.length > 0, "times must not be empty");
        return Stream.of(times).min(LocalDate::compareTo).get();
    }

    public static LocalDateTime max(LocalDateTime... times) {
        Assert.isTrue(times != null && times.length > 0, "times must not be empty");
        return Stream.of(times).max(LocalDateTime::compareTo).get();
    }

    public static LocalDate max(LocalDate... times) {
        Assert.isTrue(times != null && times.length > 0, "times must not be empty");
        return Stream.of(times).max(LocalDate::compareTo).get();
    }

    public static void eachDays(LocalDate start, LocalDate end, Consumer<LocalDate> consumer) {
        eachDays(start, end, (day) -> {
            consumer.accept(day);
            return null;
        });
    }

    public static <T> List<T> eachDays(LocalDate start, LocalDate end, Function<LocalDate, T> mapper) {
        return StreamUtils.mapToList(listDays(start, end), mapper);
    }

    public static List<LocalDate> listDays(LocalDate start, LocalDate end) {
        List<LocalDate> days = new ArrayList<>();
        long dayCount = ChronoUnit.DAYS.between(start, end);
        int inc = 0;
        while (inc <= dayCount) {
            days.add(start.plusDays(inc));
            inc++;
        }
        return days;
    }
}
