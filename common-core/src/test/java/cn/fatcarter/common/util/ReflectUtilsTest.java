package cn.fatcarter.common.util;

import cn.fatcarter.common.annotation.Alias;
import cn.fatcarter.common.reflect.ReflectUtils;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ReflectUtilsTest {
    @Data
    public static class Person {
        private String color;
    }

    @Data
    public static class Student extends Person {
        private String name;
        private Integer age;
        private Float height;
        private Double weight;
    }

    @Data
    public static class Teacher extends Person {
        @Alias("Subject")
        private String subject;
    }

    @Test
    public void testGetFieldDirect() {
        Field[] fields = ReflectUtils.getFieldsDirect(Student.class, true);
        Object[] names = Arrays.stream(fields).map(Field::getName).toArray();
        Assert.assertArrayEquals(new String[]{
                "name", "age", "height", "weight", "color"
        }, names);
    }

    @Test
    public void testGetFields() {
        Field[] fields = ReflectUtils.getFields(Student.class);
        Object[] names = Arrays.stream(fields).map(Field::getName).toArray();
        Assert.assertArrayEquals(new String[]{
                "name", "age", "height", "weight", "color"
        }, names);
    }

    @Test
    public void testGetField() {
        Field subject = ReflectUtils.getField(Teacher.class, "Subject");
        Assert.assertNotNull("not found field for Subject", subject);
        Assert.assertEquals("Subject", ReflectUtils.getFieldName(subject));
    }

    @Test
    public void testGetFieldValue() {
        Teacher teacher = new Teacher();
        teacher.setSubject("语文");
        Field subject = ReflectUtils.getField(Teacher.class, "Subject");
        Object fieldValue = ReflectUtils.getFieldValue(teacher, subject);
        Assert.assertEquals("语文", fieldValue);
    }
}
