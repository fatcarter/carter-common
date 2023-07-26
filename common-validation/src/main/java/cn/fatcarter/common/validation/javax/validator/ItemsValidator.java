package cn.fatcarter.common.validation.javax.validator;

import cn.fatcarter.common.validation.ValidatorSupport;
import cn.fatcarter.common.validation.javax.Items;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemsValidator extends ValidatorSupport implements ConstraintValidator<Items, Object> {
    private final List<String> accepted = new ArrayList<>();

    @Override
    public void initialize(Items constraintAnnotation) {
        accepted.addAll(Arrays.stream(constraintAnnotation.accept()).collect(Collectors.toList()));
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return this.accept(value, accepted);
    }
}
