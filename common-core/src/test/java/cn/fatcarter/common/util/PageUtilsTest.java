package cn.fatcarter.common.util;

import cn.fatcarter.common.lang.Tuple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PageUtilsTest {

    @Test
    public void testEach(){
        List<Integer> each = PageUtils.each(5, 1, (page, size) -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                list.add(page);
            }
            return Tuple.of(list, 10);
        });
        System.out.println(each.toString());

    }
}
