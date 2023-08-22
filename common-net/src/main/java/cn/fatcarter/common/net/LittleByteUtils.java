package cn.fatcarter.common.net;

public class LittleByteUtils {
    public static byte[] fromInt(int value) {
        byte[] bs = new byte[4];
        for (int i = 0; i < 4; i++) {
            bs[i] = (byte) (value >> (i * 8));
        }
        return bs;
    }
    public static int toInt(byte... bytes) {
        return (int) toLong(bytes);
    }
    public static long toLong(byte... bytes) {
        int len = 8;
        byte[] bs = bytes;
        if (bs.length < len) {
            byte[] b = new byte[len];
            System.arraycopy(bs, 0, b, 0, bs.length);
            bs = b;
        }
        long ans = 0;
        for (int i = 0; i < len; i++) {
            ans <<= 8;
            ans |= bs[len - 1 - i] & 0xFF;
        }
        return ans;
    }

    public static String toBinary(Byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; i--) {
            byte b = bytes[i];
            builder.append(ByteUtils.toBinary(b));
        }
        return builder.toString();
    }
}
