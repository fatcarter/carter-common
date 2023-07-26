package cn.fatcarter.common.validation.jakarta;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @deprecated
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FixedValidator.Validator.class)
@Deprecated
public @interface FixedValidator {
    String message() default "值无效";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fixedValues();

    boolean allowNull() default true;


    class Validator implements ConstraintValidator<FixedValidator, Object> {
        private String[] fixedValues;
        private boolean allowNull = true;


        @Override
        public void initialize(FixedValidator constraintAnnotation) {
            this.fixedValues = constraintAnnotation.fixedValues();
            this.allowNull = constraintAnnotation.allowNull();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (fixedValues == null || fixedValues.length == 0) {
                return false;
            }
            if (value == null) {
                return allowNull;
            }
            for (String fixedValue : fixedValues) {
                if (String.valueOf(value).equals(fixedValue)) {
                    return true;
                }
            }
            return false;
        }
    }
}
