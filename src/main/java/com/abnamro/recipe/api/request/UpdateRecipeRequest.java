package com.abnamro.recipe.api.request;

import com.abnamro.recipe.config.ValidationConfig;
import com.abnamro.recipe.models.RecipeType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.List;

public class UpdateRecipeRequest extends BasicRequest{
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

    public UpdateRecipeRequest() {
    }

    public UpdateRecipeRequest(Integer id, String name, RecipeType type, int numberOfServings, List<Integer> ingredientIds, String instructions) {
        super(id);
        this.name = name;
        this.type = type;
        this.numberOfServings = numberOfServings;
        this.ingredientIds = ingredientIds;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public RecipeType getType() {
        return type;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public List<Integer> getIngredientIds() {
        return ingredientIds;
    }

    public String getInstructions() {
        return instructions;
    }
}
