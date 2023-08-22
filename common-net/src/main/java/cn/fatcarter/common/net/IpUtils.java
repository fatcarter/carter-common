package cn.fatcarter.common.net;

public class IpUtils {
    public static String unzipIp(Long ip) {
        return unzipIp(ip, true);
    }

    public static String unzipIp(Long ip, boolean big) {
        String[] arr = new String[4];
        if (big) {
            for (int i = 3; i >= 0; i--) {
                int b = ((byte) (ip >> (i * 8))) & 0xff;
                arr[3-i] = String.valueOf(b);
            }
        } else {
            for (int i = 3; i >= 0; i--) {
                int b = ((byte) (ip >> (i * 8))) & 0xff;
                arr[i] = String.valueOf(b);
            }
        }
        return String.join(".", arr);
    }

    public static Long zipIp(String ip) {
        return zipIp(ip, true);
    }

    public static Long zipIp(String ip, boolean big) {
        String[] ips = ip.split("\\.");
        if (ips.length != 4) throw new IllegalArgumentException("仅支持IPv4格式");
        if (big) {
            return ByteUtils.toLong(
                    (byte) Short.parseShort(ips[0]),
                    (byte) Short.parseShort(ips[1]),
                    (byte) Short.parseShort(ips[2]),
                    (byte) Short.parseShort(ips[3])
            );
        } else {
            return LittleByteUtils.toLong(
                    (byte) Short.parseShort(ips[0]),
                    (byte) Short.parseShort(ips[1]),
                    (byte) Short.parseShort(ips[2]),
                    (byte) Short.parseShort(ips[3])
            );
        }
    }
}
