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
        byte[] bs = bytes;
        if (bs.length < 4) {
            byte[] b = new byte[]{0, 0, 0, 0};
            System.arraycopy(bs, 0, b, 0, bs.length);
            bs = b;
        }
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            ans <<= 8;
            ans |= bs[3-i] & 0xFF;
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
