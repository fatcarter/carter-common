package cn.fatcarter.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <T> List<T> removeHead(List<T> list, int size) {
        List<T> res = new ArrayList<>();
        while (list.size() > 0 && res.size() < size) {
            res.add(list.remove(0));
        }
        return res;
    }
}
