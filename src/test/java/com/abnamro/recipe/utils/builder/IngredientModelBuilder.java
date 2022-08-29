package com.abnamro.recipe.utils.builder;

import com.abnamro.recipe.models.Ingredient;

import java.time.LocalDateTime;

public class IngredientModelBuilder {
    private Integer id;
    private String ingredientName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Ingredient build() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setIngredient(ingredientName);
        ingredient.setCreatedAt(createdAt);
        ingredient.setUpdatedAt(updatedAt);

        return ingredient;
    }
    public IngredientModelBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public IngredientModelBuilder withName(String name) {
        this.ingredientName = name;
        return this;
    }
    public IngredientModelBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public IngredientModelBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
