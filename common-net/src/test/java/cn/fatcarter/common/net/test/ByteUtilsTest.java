package cn.fatcarter.common.net.test;

import cn.fatcarter.common.net.ByteUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

public class ByteUtilsTest {

    @Test
    public void toLongTest(){
        byte[] bytes = new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
        long value = ByteUtils.toLong(bytes);
        System.out.println(value);
    }


    @Test
    public void toBinaryTest(){
        byte b = -2;
        String value = ByteUtils.toBinary(b);
        Assert.assertEquals("11111110", value);
    }

    @Test
    public void bcdTest(){
        for (int i = 1; i < 10; i++) {
            String binary = ByteUtils.toBinary((byte) i);
            System.out.printf("二进制: %s, 十进制: %s\n", binary, i);
        }
    }
    @Test
    public void hexTest(){
        String hex = "01 00 00 00 74 65 73 74 5f 64 65 6d 6f 5f 74 6f 6b 65 6e";
        String[] arr = hex.split(" ");
        byte[] bytes = new byte[arr.length];
        int offset = 0;
        for (String s : arr) {
            bytes[offset++] = ByteUtils.fromHex(s);
        }
        String res = new String(bytes, Charset.forName("GBK"));
        System.out.println(res);
    }
}
