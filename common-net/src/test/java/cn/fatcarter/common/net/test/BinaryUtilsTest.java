package cn.fatcarter.common.net.test;

import cn.fatcarter.common.net.BinaryUtils;
import org.junit.Assert;
import org.junit.Test;

public class BinaryUtilsTest {
    @Test
    public void toBinaryIntsTest() {
        String binary = "11111110";
        int[] binaryInts = BinaryUtils.toBinaryInts(binary);
        Assert.assertArrayEquals(new int[]{
                1, 1, 1, 1, 1, 1, 1, 0
        }, binaryInts);
    }

    @Test
    public void toBinaryBooleansTest(){
        String binary = "11111110";
        boolean[] binaryBooleans = BinaryUtils.toBinaryBooleans(binary);
        Assert.assertArrayEquals(new boolean[]{
                true, true, true, true, true, true, true, false
        }, binaryBooleans);
    }
}
