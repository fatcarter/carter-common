package cn.fatcarter.common.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BigDecimals {
    public static final BigDecimal ZERO = new BigDecimal("0");
    public static final BigDecimal ONE = new BigDecimal("1");
    public static final BigDecimal TEN = new BigDecimal("10");
    public static final BigDecimal HUNDRED = TEN.multiply(TEN);
    public static final BigDecimal THOUSAND = HUNDRED.multiply(TEN);
    public static final BigDecimal TEN_THOUSAND = THOUSAND.multiply(TEN);
    public static final BigDecimal HUNDRED_THOUSAND = TEN_THOUSAND.multiply(TEN);
    public static final BigDecimal MILLION = HUNDRED_THOUSAND.multiply(TEN);
    public static final BigDecimal TEN_MILLION = MILLION.multiply(TEN);
    public static final BigDecimal HUNDRED_MILLION = TEN_MILLION.multiply(TEN);
    public static final BigDecimal THOUSAND_MILLION = HUNDRED_MILLION.multiply(TEN);

    // Time
    public static final BigDecimal MILLIS_OF_SECONDS = THOUSAND;
    public static final BigDecimal SECONDS_OF_MINUTE = new BigDecimal("60");
    public static final BigDecimal MINUTES_OF_HOUR = SECONDS_OF_MINUTE;
    public static final BigDecimal HOURS_OF_DAY = new BigDecimal("24");
    public static final BigDecimal DAYS_OF_WEEK = new BigDecimal("7");

    public static final BigDecimal MILLIS_OF_MINUTE = MILLIS_OF_SECONDS.multiply(SECONDS_OF_MINUTE);
    public static final BigDecimal MILLIS_OF_HOUR = MILLIS_OF_MINUTE.multiply(MINUTES_OF_HOUR);
    public static final BigDecimal MILLIS_OF_DAY = MILLIS_OF_HOUR.multiply(HOURS_OF_DAY);
    public static final BigDecimal MILLIS_OF_WEEK = MILLIS_OF_DAY.multiply(DAYS_OF_WEEK);


    public static final BigDecimal SECONDS_OF_HOUR = SECONDS_OF_MINUTE.multiply(MINUTES_OF_HOUR);
    public static final BigDecimal SECONDS_OF_DAY = SECONDS_OF_HOUR.multiply(HOURS_OF_DAY);
    public static final BigDecimal SECONDS_OF_WEEK = SECONDS_OF_DAY.multiply(DAYS_OF_WEEK);

    public static final BigDecimal MINUTES_OF_DAY = MINUTES_OF_HOUR.multiply(HOURS_OF_DAY);
    public static final BigDecimal MINUTES_OF_WEEK = MINUTES_OF_DAY.multiply(DAYS_OF_WEEK);



    public static BigDecimal divide(Integer v1, Integer v2, RoundingMode roundingMode) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), roundingMode);
    }

    public static BigDecimal divide(Integer v1, Integer v2, int scale, RoundingMode roundingMode) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), scale, roundingMode);
    }


    public static BigDecimal divide(Long v1, Long v2, RoundingMode roundingMode) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), roundingMode);
    }

    public static BigDecimal divide(Long v1, Long v2, int scale, RoundingMode roundingMode) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), scale, roundingMode);
    }


    public static BigDecimal divide(Double v1, Double v2, RoundingMode roundingMode) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), roundingMode);
    }

    public static BigDecimal divide(Double v1, Double v2, int scale, RoundingMode roundingMode) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), scale, roundingMode);
    }

    public static BigDecimal divide(String v1, String v2, RoundingMode roundingMode) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), roundingMode);
    }

    public static BigDecimal divide(String v1, String v2, int scale, RoundingMode roundingMode) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), scale, roundingMode);
    }

    public static BigDecimal diff(BigDecimal v1, BigDecimal v2) {
        return v1.subtract(v2);
    }
    public static  BigDecimal diffAbs(BigDecimal v1, BigDecimal v2) {
        return diff(v1, v2).abs();
    }

    public static boolean isGreater(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) > 0;
    }

    public static boolean isLess(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) < 0;
    }

    public static boolean isSame(BigDecimal v1, BigDecimal v2) {
        return v1.equals(v2);
    }

    public static boolean isPositive(BigDecimal v) {
        return isGreater(v, ZERO);
    }

    public static boolean isNegative(BigDecimal v) {
        return isLess(v, ZERO);
    }

    public static boolean isZero(BigDecimal v) {
        return isSame(v, ZERO);
    }

    public static BigDecimal min(BigDecimal v1, BigDecimal v2) {
        return isLess(v1, v2) ? v1 : v2;
    }
    public static BigDecimal max(BigDecimal v1, BigDecimal v2) {
        return isGreater(v1, v2) ? v1 : v2;
    }
}
