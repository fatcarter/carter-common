package cn.fatcarter.common.list;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
public class ListSplitter<T> {
    private Integer pageSize;
    private List<T> src;

    public ListSplitter(List<T> src) {
        this(10, src);
    }


    public SplitInfo<T> split() {
        if (pageSize <= 0) pageSize = 10;
        int pages = (src.size() + pageSize - 1) / pageSize;
        List<List<T>> pageData = new ArrayList<>(pages);
        for (int i = 0; i < pages; i++) {
            int toIndex = i * pageSize + pageSize;
            if (toIndex > src.size()) toIndex = src.size();
            List<T> page = new ArrayList<>(src.subList(i * pageSize, toIndex));
            pageData.add(page);
        }
        SplitInfo<T> pageInfo = new SplitInfo<>();
        pageInfo.pageData = pageData;
        return pageInfo;
    }


    public static class SplitInfo<T> implements Iterator<List<T>> {
        private List<List<T>> pageData;
        private Integer next = 0;

        @Override
        public boolean hasNext() {
            if (pageData == null) return false;
            return next < pageData.size();
        }

        @Override
        public List<T> next() {
            return pageData.get(next++);
        }


        public List<List<T>> getPageData() {
            return Collections.unmodifiableList(this.pageData);
        }

    }
}