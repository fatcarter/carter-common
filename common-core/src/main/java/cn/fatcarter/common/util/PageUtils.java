package cn.fatcarter.common.util;

import cn.fatcarter.common.lang.Tuple;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {
    public interface PageHandler<T> {
        Tuple<List<T>, Integer> fetch(int pageNo, int pageSize);
    }

    public static <T> List<T> each(int size, int startPage, PageHandler<T> handler) {
        int page = startPage;
        int pages = -1;
        List<T> result = new ArrayList<T>();
        while (pages < 0 || ++page <= pages) {
            Tuple<List<T>, Integer> value = handler.fetch(page, size);
            if (value.getFirst() == null || value.getFirst().isEmpty()) {
                break;
            }
            pages = value.getSecond();
            result.addAll(value.getFirst());
        }
        return result;
    }

}
