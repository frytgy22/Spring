package org.lebedeva.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class EnumPatternValidator implements ConstraintValidator<EnumPattern, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumPattern constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null) {
            return true;
        }
        return acceptedValues.contains(charSequence.toString());
    }
}
