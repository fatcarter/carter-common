package cn.fatcarter.common.util;

import cn.fatcarter.common.lang.BigDecimals;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalsTest {

    @Test
    public void test(){
        BigDecimal big = new BigDecimal("20");
        BigDecimal sameBig = new BigDecimal("20");
        BigDecimal small = new BigDecimal("19");
        BigDecimal negative = new BigDecimal("-1");
        BigDecimal zero = new BigDecimal("0");
        Assert.assertTrue(BigDecimals.isGreater(big, small));
        Assert.assertTrue(BigDecimals.isLess(small, big));
        Assert.assertTrue(BigDecimals.isSame(big, sameBig));
        Assert.assertTrue(BigDecimals.isPositive(small));
        Assert.assertTrue(BigDecimals.isNegative(negative));
        Assert.assertTrue(BigDecimals.isZero(zero));
    }

    @Test
    public void test2(){
        System.out.println(new BigDecimal("10.0").divide(new BigDecimal("3"),100,RoundingMode.HALF_UP));

    }
}
