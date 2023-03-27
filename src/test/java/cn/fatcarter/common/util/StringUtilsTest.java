package cn.fatcarter.common.util;

public class StringUtilsTest {
    public static void main(String[] args) {
        int i = 1;
        System.out.println(i << 30);
        System.out.println(i<<31);
        i++;
        System.out.println((i << 30) - 1);
        System.out.println(i << 31);
        System.out.println(Integer.MAX_VALUE);
        System.out.println((Integer.MAX_VALUE - 1) / (double) 2);
    }
}
