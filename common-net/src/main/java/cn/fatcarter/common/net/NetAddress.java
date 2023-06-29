package cn.fatcarter.common.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetAddress {
    public static String getMacAddress() throws UnknownHostException, SocketException {
        NetworkInterface add = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        byte[] hardwareAddress = add.getHardwareAddress();
        String[] adds = new String[hardwareAddress.length];
        for (int i = 0; i < hardwareAddress.length; i++) {
            adds[i] = String.format("%02X", hardwareAddress[i]);
        }
        return String.join(":", adds);
    }
}
