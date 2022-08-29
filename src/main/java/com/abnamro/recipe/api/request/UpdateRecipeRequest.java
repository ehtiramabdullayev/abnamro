package com.abnamro.recipe.api.request;

import com.abnamro.recipe.config.ValidationConfig;
import com.abnamro.recipe.models.RecipeType;
import com.abnamro.recipe.validator.EnumValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.util.List;

public class UpdateRecipeRequest extends BasicRequest{
    @NotBlank(message = "{recipeName.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "{recipeName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "{recipeName.pattern}")
    @ApiModelProperty(notes = "The name of the ingredient", example = "Potato")
    private String name;

    @EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}")
    @ApiModelProperty(notes = "The type of the recipe", example = "VEGETARIAN")
    private String type;

    @NotNull(message = "{numberOfServings.notNull}")
    @Positive(message = "{numberOfServings.positive}")
    @ApiModelProperty(notes = "The number of servings", example = "7")
    private int numberOfServings;

    @ApiModelProperty(notes = "The new ids of the ingredients needed for the update", example = "[3,4]")
    private List<Integer> ingredientIds;

    @NotBlank(message = "{instructions.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_DEFAULT, message = "{instructions.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{instructions.pattern}")
    @ApiModelProperty(notes = "The instructions to update the recipe", example = "Cut,fry,eat")

    private String instructions;

    public UpdateRecipeRequest() {
    }

    public UpdateRecipeRequest(Integer id, String name, String type, int numberOfServings, List<Integer> ingredientIds, String instructions) {
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

    public String getType() {
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
