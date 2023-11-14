package cn.fatcarter.common.net;

import cn.fatcarter.common.function.Filter;
import cn.fatcarter.common.util.CollectionUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedHashSet;

public class NetUtils {
    public static String getLocalHostMacAddress() {
        return getMacAddress(getLocalhost());
    }
    public static String getMacAddress(InetAddress address) {
        return getMacAddress(address, ":");
    }

    public static String getMacAddress(InetAddress address, String separator) {
        if (address == null) return null;
        byte[] mac = null;
        try {
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            if (ni != null) {
                mac = ni.getHardwareAddress();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (mac != null) {
            String[] adds = new String[mac.length];
            for (int i = 0; i < mac.length; i++) {
                String hex = Integer.toHexString(mac[i] & 0xFF);
                adds[i] = hex.length() == 1 ? "0" + hex : hex;
            }
            return String.join(separator, adds);
        }
        return null;
    }

    public static InetAddress getLocalhost() {
        LinkedHashSet<InetAddress> addresses = localAddressSet(address -> !address.isLoopbackAddress()
                && !address.isSiteLocalAddress()
                && address instanceof Inet4Address);

        if (CollectionUtils.isNotEmpty(addresses)) {
            return CollectionUtils.get(addresses, 0);
        }
        try {
            return InetAddress.getLocalHost();
        } catch (Exception ignore) {
        }
        return null;
    }


    public static LinkedHashSet<InetAddress> localAddressSet(Filter<InetAddress> filter) {
        Enumeration<NetworkInterface> networkInterfaces;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (networkInterfaces == null) {
            throw new RuntimeException("Get network error!");
        }
        LinkedHashSet<InetAddress> ipSet = new LinkedHashSet<>();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = networkInterfaces.nextElement();
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress inetAddress = addresses.nextElement();
                if (inetAddress != null && (filter == null || filter.accept(inetAddress))) {
                    ipSet.add(inetAddress);
                }
            }
        }
        return ipSet;
    }
}
