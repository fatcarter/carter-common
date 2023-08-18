package cn.fatcarter.common.util;

import cn.fatcarter.common.list.ListSplitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class ListUtils {

    public static <T> ListSplitter.SplitInfo<T> split(List<T> list, Integer pageSize) {
        return new ListSplitter<>(pageSize, list).split();
    }

    public static <T> void pageEach(List<T> list, int pageSize, Consumer<List<T>> consumer) {
        if (list == null) return;
        ListSplitter<T> splitter = new ListSplitter<>(pageSize, list);
        pageEach(splitter.split(), consumer);
    }

    public static <T> void pageEach(ListSplitter.SplitInfo<T> splitInfo, Consumer<List<T>> consumer) {
        while (splitInfo.hasNext()) {
            List<T> data = splitInfo.next();
            consumer.accept(data);
        }
    }

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
