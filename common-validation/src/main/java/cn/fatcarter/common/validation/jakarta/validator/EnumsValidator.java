package cn.fatcarter.common.validation.jakarta.validator;

import cn.fatcarter.common.validation.ValidatorSupport;
import cn.fatcarter.common.validation.jakarta.Enums;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EnumsValidator extends ValidatorSupport implements ConstraintValidator<Enums, Object> {
    private final List<String> accepted = new ArrayList<>();
    private Enums annotation;

    @Override
    public void initialize(Enums annotation) {
        this.annotation = annotation;
        Enum<?>[] enumConstants = annotation.enumClass().getEnumConstants();
        Method method = getMethod();
        accepted.addAll(getAcceptedValues(method, enumConstants));
    }


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return this.accept(value, accepted);
    }

    private List<String> getAcceptedValues(Method method, Enum<?>[] enums) {
        List<String> ret = new ArrayList<>();
        for (Enum<?> anEnum : enums) {
            try {
                Object value = method.invoke(anEnum);
                if (!(value instanceof String)) {
                    value = String.valueOf(value);
                }
                ret.add((String) value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("获取枚举可接受值失败!method=" + method.getName(), e);
            }
        }
        return ret;

    }

    private Method getMethod() {
        try {
            return annotation.enumClass().getMethod(annotation.method());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("未找到指定的方法名称: " + annotation.method(), e);
        }
    }
}
