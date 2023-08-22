package cn.fatcarter.common.net.test;

import cn.fatcarter.common.net.IpUtils;
import org.junit.Assert;
import org.junit.Test;

public class IpUtilsTest {

    @Test
    public void testZipIp(){
        String ip = "49.234.131.81";
        long zipped = IpUtils.zipIp(ip, true);
        Assert.assertEquals(837452625L, zipped);

        long littleZipped = IpUtils.zipIp(ip, false);
        System.out.println(littleZipped);
    }

    @Test
    public void testUnzipIp(){
        Long value = 837452625L;
        String ip = IpUtils.unzipIp(value, true);
        Assert.assertEquals("49.234.131.81", ip);

        Long littleValue = 1367599665L;
        ip = IpUtils.unzipIp(littleValue, false);
        Assert.assertEquals("49.234.131.81", ip);
    }
}
