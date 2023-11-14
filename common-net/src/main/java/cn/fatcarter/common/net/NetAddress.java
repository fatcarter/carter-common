package cn.fatcarter.common.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetAddress {
    public static String getMacAddress() throws UnknownHostException, SocketException {
        return NetUtils.getLocalHostMacAddress();
    }

}
