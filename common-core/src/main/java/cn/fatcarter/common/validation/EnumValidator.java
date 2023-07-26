package cn.fatcarter.common.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.Validator.class)
@Deprecated
public @interface EnumValidator {
    String message() default "值无效";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enumerable> enumClass();

    boolean allowNull() default true;

    class Validator implements ConstraintValidator<EnumValidator, Object> {
        private Class<? extends Enumerable> enumClass = null;
        private boolean allowNull = true;

        @Override
        public void initialize(EnumValidator constraintAnnotation) {
            enumClass = constraintAnnotation.enumClass();
            allowNull = constraintAnnotation.allowNull();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (value == null) return allowNull;
            Enumerable e = tryGet(enumClass, String.valueOf(value));
            return e != null;
        }

        private static <E extends Enumerable> E tryGet(Class<E> type, String value) {
            for (E e : type.getEnumConstants()) {
                if (e.getCode().equals(value)) {
                    return e;
                }
            }
            return null;
        }

    }
}
