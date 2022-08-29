package com.abnamro.recipe.utils.builder;

import com.abnamro.recipe.api.request.CreateIngredientRequest;
import com.abnamro.recipe.models.Ingredient;

import java.time.LocalDateTime;
import java.util.List;

public class IngredientTestDataBuilder {
    public static CreateIngredientRequest createIngredientRequest() {
        return new CreateIngredientRequestBuilder()
                .withName("tomato")
                .build();
    }

    public static Ingredient createIngredient() {
        return new IngredientModelBuilder()
                .withName("tomato")
                .build();
    }

    public static List<Ingredient> createIngredientList() {
        return createIngredientList(false);
    }

    public static List<Ingredient> createIngredientList(boolean withId) {
        Ingredient i1 = new IngredientModelBuilder()
                .withId(withId ? 10 : null)
                .withName("tomato")
                .build();

        Ingredient i2 = new IngredientModelBuilder()
                .withId(withId ? 11 : null)
                .withName("cabbage")
                .build();

        return List.of(i1, i2);
    }
}
