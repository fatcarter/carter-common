package cn.fatcarter.common.net.test;

import cn.fatcarter.common.net.ByteUtils;
import cn.fatcarter.common.net.IpUtils;
import cn.fatcarter.common.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class IpUtilsTest {

    @Test
    public void testZipIp(){
        int i = -1;
        String iBin = Integer.toBinaryString(i);
        System.out.println(iBin.length() + iBin);
        long a = i & 0x00000000FFFFFFFFL;
        System.out.println(a);
        String bin = Long.toBinaryString(a);
        System.out.println(bin.length() + bin);


//        String ip = "1.0.0.0";
//        long zipped = IpUtils.zipIp(ip, true);
//        Assert.assertEquals(837452625L, zipped);
//
//        long littleZipped = IpUtils.zipIp(ip, false);
//        System.out.println(littleZipped);
    }

    @Test
    public void testUnzipIp(){
        Long value = 1098971754496L;
        String ip = IpUtils.unzipIp(value, true);
        Assert.assertEquals("223.210.48.0", ip);

        Long littleValue = 1367599665L;
        ip = IpUtils.unzipIp(littleValue, false);
        Assert.assertEquals("49.234.131.81", ip);
    }
}
