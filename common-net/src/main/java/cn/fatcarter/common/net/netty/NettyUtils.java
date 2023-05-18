package cn.fatcarter.common.net.netty;

import io.netty.channel.epoll.Epoll;

public class NettyUtils {
    public static boolean epoll() {
        String osName = System.getProperty("os.name");
        if (!osName.contains("linux")) {
            return false;
        }
        if (!Epoll.isAvailable()) {
            return false;
        }
        return Boolean.parseBoolean(System.getProperty("netty.epoll.enable", "true"));
    }

}
