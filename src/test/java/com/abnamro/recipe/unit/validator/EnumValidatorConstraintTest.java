package com.abnamro.recipe.unit.validator;

import com.abnamro.recipe.api.request.CreateIngredientRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class EnumValidatorConstraintTest {
    private static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Test
    public void whenNotBlankName_thenNoConstraintViolations() {
        CreateIngredientRequest request = new CreateIngredientRequest("pasta");

        Set<ConstraintViolation<CreateIngredientRequest>> violations = validator.validate(request);

        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void whenBlankName_thenOneConstraintViolation() {
        CreateIngredientRequest request = new CreateIngredientRequest(null);

        Set<ConstraintViolation<CreateIngredientRequest>> violations = validator.validate(request);
        String collect = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        assertEquals(collect, "{ingredient.notBlank}");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyName_thenOneConstraintViolation() {
        CreateIngredientRequest request = new CreateIngredientRequest(null);

        Set<ConstraintViolation<CreateIngredientRequest>> violations = validator.validate(request);
        String collect = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        assertEquals(collect, "{ingredient.notBlank}");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenNameDoesNotFitPattern_thenOneConstraintViolation() {
        CreateIngredientRequest request = new CreateIngredientRequest("-.1!@$!#@");

        Set<ConstraintViolation<CreateIngredientRequest>> violations = validator.validate(request);
        String collect = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        assertEquals(collect, "{ingredient.pattern}");
        assertThat(violations.size()).isEqualTo(1);
    }
}
