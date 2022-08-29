package com.abnamro.recipe.unit.validator;

import com.abnamro.recipe.api.request.SearchCriteriaRequest;
import com.abnamro.recipe.validator.EnumValidatorConstraint;
import org.junit.jupiter.api.Test;

import javax.xml.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EnumValidatorConstraintTest {

    private EnumValidatorConstraint enumValidatorConstraint;

//    @Test
//    public void whenAllAcceptable_thenShouldNotGiveConstraintViolations() {
//        enumValidator= ne;
//        SearchCriteriaRequest request = new SearchCriteriaRequest();
//        request.setFilterKey("name");
//        Set violations = enumValidatorConstraint.validate(request);
//        assertThat(violations).isEmpty();
//    }

    @Test
    void initialize() {
    }

    @Test
    void isValid() {
    }
}