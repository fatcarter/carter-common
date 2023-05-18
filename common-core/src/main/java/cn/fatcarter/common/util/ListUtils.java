package cn.fatcarter.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class ListUtils {
    public static <T> List<T> removeHead(List<T> list, int size) {
        List<T> res = new ArrayList<>();
        while (list.size() > 0 && res.size() < size) {
            res.add(list.remove(0));
        }
        return res;
    }

    public static <T> List<T> subtract(Collection<T> source, Collection<T> willBeRemoved, Function<Collection<T>, List<T>> creator) {
        List<T> list = creator.apply(source);
        list.removeAll(willBeRemoved);
        return list;
    }

    public static <T> List<T> subtract(Collection<T> source, Collection<T> willBeRemoved) {
        return subtract(source, willBeRemoved, ArrayList::new);
    }
}
