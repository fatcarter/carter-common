package cn.fatcarter.common.net;

public class IpUtils {
    public static String unzipIp(Integer ip) {
        return unzipIp(ip, true);
    }

    public static String unzipIp(Integer ip, boolean big) {
        String[] arr = new String[4];
        if (big) {
            for (int i = 3; i >= 0; i--) {
                int b = ((byte) (ip >> (i * 8))) & 0xff;
                arr[3] = String.valueOf(b);
            }
        } else {
            for (int i = 3; i >= 0; i--) {
                int b = ((byte) (ip >> (i * 8))) & 0xff;
                arr[3 - i] = String.valueOf(b);
            }
        }
        return String.join(".", arr);
    }

    public static Integer zipIp(String ip) {
        return zipIp(ip, true);
    }

    public static Integer zipIp(String ip, boolean big) {
        String[] ips = ip.split("\\.");
        if (ips.length != 4) throw new IllegalArgumentException("仅支持IPv4格式");
        if (big) {
            return ByteUtils.toInt(
                    Byte.parseByte(ips[0]),
                    Byte.parseByte(ips[1]),
                    Byte.parseByte(ips[2]),
                    Byte.parseByte(ips[3])
            );
        } else {
            return LittleByteUtils.toInt(
                    Byte.parseByte(ips[0]),
                    Byte.parseByte(ips[1]),
                    Byte.parseByte(ips[2]),
                    Byte.parseByte(ips[3])
            );
        }
    }
}
