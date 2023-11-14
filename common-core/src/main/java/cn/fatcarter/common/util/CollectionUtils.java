package cn.fatcarter.common.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {
    public static String toString(Collection<?> collection) {
        return Arrays.toString(collection.toArray());
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }


    public static <T> T get(Collection<T> collection, int index) {
        if (collection == null) return null;
        int size = collection.size();
        if (index < 0) {
            index += size;
        }
        if (index >= size || index < 0) {
            return null;
        }
        if (collection instanceof List) {
            return ((List<T>) collection).get(index);
        }
        int offset = 0;
        for (T t : collection) {
            if (offset > index)
                break;
            else if (offset == index)
                return t;
            offset++;
        }
        return null;
    }
}
