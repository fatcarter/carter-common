package cn.fatcarter.common.net;

import java.util.ArrayList;
import java.util.List;

public class ByteUtils {

    public static List<Byte> asList(byte[] bytes) {
        List<Byte> list = new ArrayList<>(bytes.length);
        for (byte aByte : bytes) {
            list.add(aByte);
        }
        return list;
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

