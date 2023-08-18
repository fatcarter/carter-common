package cn.fatcarter.common.reflect;

import cn.fatcarter.common.annotation.Alias;
import cn.fatcarter.common.cache.SimpleCache;
import cn.fatcarter.common.util.ArrayUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public class ReflectUtils {
    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();

    public static <T extends AccessibleObject> T setAccessible(T object) {
        if (object != null && !object.isAccessible()) {
            object.setAccessible(true);
        }
        return object;
    }

    public static Field[] getFieldsDirect(Class<?> clz, boolean withSuperClass) {
        Field[] allFields = null;
        Class<?> searchClz = clz;
        Field[] fields;
        while (searchClz != Object.class) {
            fields = searchClz.getDeclaredFields();
            if (allFields == null) {
                allFields = fields;
            } else {
                allFields = ArrayUtils.append(allFields, fields);
            }
            searchClz = withSuperClass ? searchClz.getSuperclass() : Object.class;
        }
        return allFields;
    }

    public static Field[] getFields(Class<?> clz) {
        Field[] fields = FIELDS_CACHE.get(clz);
        if (fields != null) return fields;
        fields = getFieldsDirect(clz, true);
        return FIELDS_CACHE.put(clz, fields);
    }

    public static Field getField(Class<?> clz, String name) {
        Field[] fields = getFields(clz);
        return ArrayUtils.firstMatch(field -> getFieldName(field).equals(name), fields);
    }

    public static String getFieldName(Field field) {
        if (field == null) return null;
        Alias alias = field.getAnnotation(Alias.class);
        if (alias != null) {
            return alias.value();
        }
        return field.getName();
    }

    public static Object getFieldValue(Object target, Field field) {
        if (field == null) {
            return null;
        }
        if (target instanceof Class) {
            return null;
        }
        return getFieldValueSafe(target, field);
    }

    private static Object getFieldValueSafe(Object target, Field field) {
        setAccessible(field);
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
