package cn.fatcarter.common.net;

import cn.fatcarter.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ByteUtils {

    public static String toBinary(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(toBinary(b));
        }
        return builder.toString();
    }

    public static String toBinary(byte bytes) {
        String binary = Integer.toBinaryString(bytes & 0xff);
        if (binary.length() < 8) {
            int size = 8 - binary.length();
            for (int i = 0; i < size; i++) {
                binary = StringUtils.padStartZero(binary, 1);
            }
            return binary;
        }
        return binary;
    }


    public static List<Byte> asList(byte[] bytes) {
        List<Byte> list = new ArrayList<>(bytes.length);
        for (byte aByte : bytes) {
            list.add(aByte);
        }
        return list;
    }

    public static byte[] toArray(List<Byte> bytes) {
        if (bytes.size() == 0) return new byte[0];

        byte[] array = new byte[bytes.size()];
        int offset = 0;
        for (Byte b : bytes) {
            array[offset] = b;
            offset++;
        }
        return array;
    }

    public static String toHex(byte[] bytes, String delimiter) {
        if (bytes.length == 1) {
            return toHex(bytes[0]);
        }
        StringBuilder builder = new StringBuilder();
        if (delimiter.length() > 0) {
            for (byte b : bytes) {
                builder.append(toHex(b)).append(delimiter);
            }
            return builder.length() > 0 ? builder.substring(0, builder.length() - delimiter.length()) : "";
        } else {
            for (byte b : bytes) {
                builder.append(toHex(b));
            }
            return builder.toString();
        }
    }

    public static String toHex(byte[] bytes) {
        return toHex(bytes, "");
    }

    public static String toHex(byte b) {
        String r = Integer.toHexString(b & 0xFF);
        if (r.length() < 2) {
            r = "0" + r;
        }
        return r;
    }

    public static byte fromHex(String hex) {
        return (byte) Integer.parseInt(hex, 16);
    }

    public static byte[] fromIntToShort(int value) {
        if (value > 65535) {
            throw new IllegalArgumentException("short must be less than 65535");
        }
        byte[] res = new byte[2];
        System.arraycopy(fromInt(value), 2, res, 0, 2);
        return res;
    }

    public static byte[] fromInt(int value) {
        byte[] bs = new byte[4];
        for (int i = 0; i < 4; i++) {
            bs[3-i] = (byte) (value >> (i * 8));
        }
        return bs;
    }

    public static Integer toInt(byte... bytes) {

        byte[] bs = bytes;
        if (bs.length < 4) {
            byte[] b = new byte[]{0, 0, 0, 0};
            System.arraycopy(bs, 0, b, b.length - bs.length, bs.length);
            bs = b;
        }
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            ans <<= 8;
            ans |= bs[i] & 0xFF;
        }
        return ans;
    }
}

