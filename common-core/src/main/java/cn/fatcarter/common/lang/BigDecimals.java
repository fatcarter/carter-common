package cn.fatcarter.common.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BigDecimals {
    public static final BigDecimal ZERO = new BigDecimal("0");
    public static final BigDecimal ONE = new BigDecimal("1");
    public static final BigDecimal TEN = new BigDecimal("10");
    public static final BigDecimal HUNDRED = new BigDecimal("100");
    public static final BigDecimal THOUSAND = new BigDecimal("1000");
    public static final BigDecimal TEN_THOUSAND = new BigDecimal("10000");
    public static final BigDecimal HUNDRED_THOUSAND = new BigDecimal("10000");
    public static final BigDecimal MILLION = new BigDecimal("1000000");
    public static final BigDecimal TEN_MILLION = new BigDecimal("10000000");
    public static final BigDecimal HUNDRED_MILLION = new BigDecimal("100000000");

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
}
