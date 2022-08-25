package com.abnamro.recipe.api.request;

import com.abnamro.recipe.config.ValidationConfig;
import com.abnamro.recipe.models.RecipeType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.List;

public class CreateRecipeRequest {
    @NotBlank(message = "{recipeName.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "{recipeName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "{recipeName.pattern}")
    private String name;

    @Enumerated(EnumType.STRING)
    private RecipeType type;

    @NotNull(message = "{numberOfServings.notNull}")
    @Positive(message = "{numberOfServings.positive}")
    private int numberOfServings;

    private List<Integer> ingredientIds;

    @NotBlank(message = "{instructions.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_DEFAULT, message = "{instructions.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{instructions.pattern}")
    private String instructions;

    public CreateRecipeRequest() {
    }

    public CreateRecipeRequest(String name, RecipeType type, int numberOfServings, List<Integer> ingredientIds, String instructions) {
        this.name = name;
        this.type = type;
        this.numberOfServings = numberOfServings;
        this.ingredientIds = ingredientIds;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public List<Integer> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<Integer> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
