package cn.fatcarter.common.util;

import org.junit.Test;

import java.util.List;

public class StreamUtilsTest {
    @Test
    public void testMaps(){
        List<Integer> intList = List.of(1, 2, 3, 4, 5);

        // Example: Integer -> String -> Long -> Double
//        List<Double> result = StreamUtils.maps(
//                intList,
//                i -> String.valueOf(i),  // Integer -> String
//                s -> Long.parseLong(s),  // String -> Long
//                l -> l.doubleValue()     // Long -> Double
//        );

//        System.out.println(result);  // Output: [1.0, 2.0, 3.0, 4.0, 5.0]
    }
}
