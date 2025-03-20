package cn.fatcarter.common.util;

import org.junit.Assert;
import org.junit.Test;

public class OpTest {
    @Test
    public void testOrNull() {
        String value = "123";
        Assert.assertEquals(value,Op.orNull(value));
        Assert.assertNull(Op.orNull(null));
    }

    @Test
    public void testOrElse() {
        String value = "123";
        String orValue = "456";
        String nullValue = null;
        Assert.assertEquals(value, Op.orElse(value, orValue));
        Assert.assertEquals(orValue, Op.orElse(nullValue, orValue));
        Assert.assertEquals(orValue, Op.orElse(nullValue, () -> orValue));
    }
}
