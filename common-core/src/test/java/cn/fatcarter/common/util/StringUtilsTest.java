package cn.fatcarter.common.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {


    @Test
    public void blankTest(){
        String whitespace = " ";
        String empty = "";
        String chars = "sdf";
        String innerWhitespace = "sdf fsd";
        String outerWhitespace = " sdfsf ";
        String all = " sdf sdf ";

        Assert.assertTrue(StringUtils.isBlank(whitespace));
        Assert.assertTrue(StringUtils.isBlank(empty));
        Assert.assertFalse(StringUtils.isBlank(chars));
        Assert.assertFalse(StringUtils.isBlank(innerWhitespace));
        Assert.assertFalse(StringUtils.isBlank(outerWhitespace));
        Assert.assertFalse(StringUtils.isBlank(all));
    }
}
