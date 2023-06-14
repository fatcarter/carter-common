package cn.fatcarter.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public static <T> List<T> distinct(Collection<T> collect) {
        return distinct(collect, ArrayList::new);
    }

    public static <T> List<T> distinct(Collection<T> collect, Function<Collection<T>, List<T>> creator) {
        if (collect instanceof Set) {
            return creator.apply(collect);
        }
        Set<T> set = new HashSet<>(collect);
        return creator.apply(set);
    }

    public static <T> List<T> merge(Collection<T> one, Collection<T> two) {
        return merge(one, two, ArrayList::new);
    }

    public static <T> List<T> merge(Collection<T> one, Collection<T> two, Function<Collection<T>, List<T>> creator) {
        List<T> res = creator.apply(one);
        res.addAll(two);
        return res;
    }

}
