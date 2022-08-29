package com.abnamro.recipe.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class EnumValidatorConstraint implements ConstraintValidator<EnumValidator, String> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        acceptedValues = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();

        Enum[] enumValArr = enumClass.getEnumConstants();

        for (Enum enumVal : enumValArr) {
            acceptedValues.add(enumVal.toString().toUpperCase());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toUpperCase());
    }
}
