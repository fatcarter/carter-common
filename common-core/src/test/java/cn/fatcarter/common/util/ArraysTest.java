package cn.fatcarter.common.util;

import cn.fatcarter.common.lang.Arrays;
import org.junit.Test;

public class ArraysTest {

    @Test
    public void testOf(){
        Integer[] array = Arrays.of(1, 2, 5);
        System.out.println(array.length);
        System.out.println(java.util.Arrays.toString(array));
    }

    @Test
    public void testJoin(){
        Integer[] of = Arrays.of(1, 3, 6);
        System.out.println(ArrayUtils.join("-", of));;
    }
}
