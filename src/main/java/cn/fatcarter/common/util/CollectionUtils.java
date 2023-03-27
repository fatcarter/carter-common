package cn.fatcarter.common.util;

import java.util.Arrays;
import java.util.Collection;

public class CollectionUtils {
    public static String toString(Collection<?> collection) {
        return Arrays.toString(collection.toArray());
    }
}
