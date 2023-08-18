package cn.fatcarter.common.util;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilsTest {

    @Test
    public void testAppend(){
        Integer[] array = {1, 2, 3, 4};
        Integer[] elems = {5, 6, 7};

        Integer[] result = ArrayUtils.append(array, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7}, result);
    }

    @Test
    public void testNegativeInsert() {
        Integer[] array = {1, 2, 3, 4};
        Integer[] elems = {5, 6, 7};

        Integer[] result = ArrayUtils.insert(array, -1, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 7, 4}, result);

        result = ArrayUtils.insert(array, -2, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 5, 6, 7, 3, 4}, result);

        result = ArrayUtils.insert(array, -3, elems);
        Assert.assertArrayEquals(new Integer[]{1, 5, 6, 7, 2, 3, 4}, result);

        result = ArrayUtils.insert(array, -4, elems);
        Assert.assertArrayEquals(new Integer[]{5, 6, 7, 1, 2, 3, 4}, result);

        result = ArrayUtils.insert(array, -5, elems);
        Assert.assertArrayEquals(new Integer[]{5, 6, 7, null, 1, 2, 3, 4}, result);

        result = ArrayUtils.insert(array, -6, elems);
        Assert.assertArrayEquals(new Integer[]{5, 6, 7, null, null, 1, 2, 3, 4}, result);


        result = ArrayUtils.insert(array, -7, elems);
        Assert.assertArrayEquals(new Integer[]{5, 6, 7, null, null, null, 1, 2, 3, 4}, result);


        result = ArrayUtils.insert(array, -8, elems);
        Assert.assertArrayEquals(new Integer[]{5, 6, 7, null, null, null, null, 1, 2, 3, 4}, result);
    }

    @Test
    public void testBigPositiveInsert() {
        Integer[] array = {1, 2, 3, 4};
        Integer[] elems = {5, 6, 7};

        Integer[] result = ArrayUtils.insert(array, 5, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, null, 5, 6, 7}, result);

        result = ArrayUtils.insert(array, 6, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, null, null, 5, 6, 7}, result);

        result = ArrayUtils.insert(array, 7, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, null, null, null, 5, 6, 7}, result);

        result = ArrayUtils.insert(array, 8, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, null, null, null, null, 5, 6, 7}, result);

    }

    @Test
    public void testNormalInsert() {
        Integer[] array = {1, 2, 3, 4};
        Integer[] elems = {5, 6, 7};
        Integer[] result = ArrayUtils.insert(array, 0, elems);
        Assert.assertArrayEquals(new Integer[]{5, 6, 7, 1, 2, 3, 4}, result);

        result = ArrayUtils.insert(array, 1, elems);
        Assert.assertArrayEquals(new Integer[]{1, 5, 6, 7, 2, 3, 4}, result);

        result = ArrayUtils.insert(array, 2, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 5, 6, 7, 3, 4}, result);

        result = ArrayUtils.insert(array, 3, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 7, 4}, result);

        result = ArrayUtils.insert(array, 4, elems);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7}, result);
    }

    @Test
    public void testFirstMatch() {
        Integer[] array = {
                4, 43, 35, 35, 566, 6, 57, 76, 8, 2, 23, 5, 6, 7, 7, 45, 43
        };
        Integer res = ArrayUtils.firstMatch(num -> {
            return num == 87;
        }, array);
        Assert.assertNull(res);

        res = ArrayUtils.firstMatch(num -> {
            return num == 76;
        }, array);
        Assert.assertEquals((Object) 76, res);
    }
}
