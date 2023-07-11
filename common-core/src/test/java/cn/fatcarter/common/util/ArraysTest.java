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
}
