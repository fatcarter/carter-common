package cn.fatcarter.common.util;

import cn.fatcarter.common.lang.Matcher;

import java.lang.reflect.Array;

public class ArrayUtils {
    public static <T> boolean isEmpty(T[] ts) {
        if (ts == null) return true;
        return ts.length == 0;
    }

    public static <T> boolean isBlank(T[] ts) {
        if (isEmpty(ts)) {
            return true;
        }
        for (T t : ts) {
            if (t != null) {
                return false;
            }
        }
        return true;
    }

    public static <T> String join(String delimiter, T... ts) {
        if (isEmpty(ts)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ts.length; i++) {
            String value = String.valueOf(ts[i]);
            builder.append(value);
            if (i + 1 < ts.length) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }

    /**
     * 使用指定类型创建一个指定长度的新数组, 数组内填充null
     *
     * @param componentType 数组元素类型
     * @param size          数组长度
     * @param <T>           数组类型
     * @return 创建的新数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<T> componentType, int size) {
        return (T[]) Array.newInstance(componentType, size);
    }

    /**
     * 获取指定数组的长度, null或空数组返回0
     *
     * @param array 数组
     * @param <T>   数组元素的类型
     * @return 数组的长度
     */
    public static <T> int len(T[] array) {
        if (isEmpty(array)) return 0;
        return Array.getLength(array);
    }

    /**
     * 在指定数组的末尾追加另一个数组, 会创建新的数组, 不影响原来的数组
     *
     * @param src         在此数组后追加
     * @param newElements src数组后面要追加的内容
     * @param <T>         数组元素类型
     * @return 追加后的新数组
     */
    public static <T> T[] append(T[] src, T[] newElements) {
        if (isEmpty(newElements)) {
            return src;
        }
        return insert(src, src.length, newElements);
    }

    /**
     * 在指定数组的指定位置插入另一个相同类型的数组, 得到一个新的数组, 不会影响原本的数组,
     * index允许负数, 表示src从后向前查找,-1为数组的最后一个元素位置,
     * 不论index正负, 当index的绝对值大于src的长度时, 多出来的位置填充null
     *
     * @param src         需要在这个数组的某个位置插入
     * @param index       插入数组的索引位置, 允许负数
     * @param newElements 需要插入的数组内容
     * @param <T>         数组类型
     * @return 结果生成的新数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] insert(T[] src, int index, T[] newElements) {

        if (isEmpty(newElements)) {
            return src;
        }
        if (isEmpty(src)) {
            return newElements;
        }
        int length = src.length + newElements.length;
        int emptyLen = Math.max(Math.abs(index) - src.length, 0);
        length += emptyLen;
        T[] result = (T[]) newArray(src.getClass().getComponentType(), length);

        if (emptyLen > 0) {
            if (index < 0) {
                System.arraycopy(newElements, 0, result, 0, newElements.length);
                System.arraycopy(src, 0, result, newElements.length + emptyLen, src.length);
            } else {
                System.arraycopy(src, 0, result, 0, src.length);
                System.arraycopy(newElements, 0, result, src.length + emptyLen, newElements.length);
            }
        } else {
            System.arraycopy(src, 0, result, 0, src.length);
            if (index < 0) {
                System.arraycopy(newElements, 0, result, src.length + index, newElements.length);
                System.arraycopy(src, src.length + index, result, src.length + index + newElements.length, Math.abs(index));
            } else {
                System.arraycopy(newElements, 0, result, index, newElements.length);
                System.arraycopy(src, index, result, index + newElements.length, src.length - index);
            }
        }
        return result;
    }

    /**
     * 通过matcher匹配数组中第一个匹配的元素并返回它
     *
     * @param matcher 数组元素匹配函数
     * @param array   等待匹配的数组列表
     * @param <T>     数组元素类型
     * @return 匹配到的数组元素, 未匹配到时返回null
     */
    public static <T> T firstMatch(Matcher<T> matcher, T[] array) {
        if (!isEmpty(array)) {
            for (final T val : array) {
                if (matcher.match(val)) {
                    return val;
                }
            }
        }
        return null;
    }
}
