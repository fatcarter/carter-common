package cn.fatcarter.common.util;

public final class NumberUtils {
    private NumberUtils() {
    }

    public static Integer hexToDecimal(String hexNumber) {
        String hex = (hexNumber.startsWith("0x") || hexNumber.startsWith("0X")) ? hexNumber.substring(2) : hexNumber;
        return Integer.valueOf(hex, 16);
    }

    public static String decimalToHex(Integer decimal) {
        return String.format("0X%s", Integer.toHexString(decimal).toUpperCase());
    }
}
