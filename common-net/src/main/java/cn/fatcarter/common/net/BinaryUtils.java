package cn.fatcarter.common.net;

import cn.fatcarter.common.util.Assert;
import cn.fatcarter.common.util.StringUtils;

public class BinaryUtils {


    public static String padStart(String binary) {
        return padStart(binary, false);
    }

    public static String padStart(String binary, boolean value) {
        return padStart(binary, value, 1);
    }

    public static String padStart(String binary, boolean value, Integer size) {
        Assert.greaterThenZero(size, "size 必须大于0");
        String c = value ? "1" : "0";
        return StringUtils.padStart(binary, size, c);
    }

    public static String padEnd(String binary) {
        return padEnd(binary, false);
    }

    public static String padEnd(String binary, boolean value) {
        return padEnd(binary, value, 1);
    }

    public static String padEnd(String binary, boolean value, Integer size) {
        Assert.greaterThenZero(size, "size 必须大于0");
        String c = value ? "1" : "0";
        return StringUtils.padEnd(binary, size, c);
    }


    public static int binaryToInt(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static int[] toBinaryInts(String binary) {
        char[] charArray = binary.toCharArray();
        int[] ret = new int[charArray.length];
        int offset = 0;
        for (char c : charArray) {
            ret[offset++] = Integer.parseInt(String.valueOf(c));
        }
        return ret;
    }

    public static boolean[] toBinaryBooleans(String binary) {
        boolean[] ret = new boolean[binary.length()];
        int offset = 0;
        for (char c : binary.toCharArray()) {
            ret[offset++] = c == '1';
        }
        return ret;
    }

}
