package com.abnamro.recipe.api.response;

import com.abnamro.recipe.models.Recipe;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeResponse {
    @ApiModelProperty(notes = "The id of the returned recipe", example = "1")
    private int id;

    @ApiModelProperty(notes = "The name of the returned recipe", example = "Pasta")
    private String name;

    @ApiModelProperty(notes = "The type of the returned recipe", example = "VEGETARIAN")
    private String type;

    @ApiModelProperty(notes = "Number of servings", example = "1")
    private int numberOfServings;

    @JsonIgnoreProperties("ingredients")
    private Set<IngredientResponse> ingredients;

    @ApiModelProperty(notes = "The instructions of the returned recipe", example = "Chop the onion, add to pasta and boil it")
    private String instructions;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    public RecipeResponse() {
    }

    public RecipeResponse(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.type = recipe.getType();
        this.instructions = recipe.getInstructions();
        this.createdAt = recipe.getCreatedAt();
        this.updatedAt = recipe.getUpdatedAt();
        this.numberOfServings = recipe.getNumberOfServings();

        this.ingredients = recipe.getRecipeIngredients() != null ?
                recipe.getRecipeIngredients().stream()
                        .map(IngredientResponse::new)
                        .collect(Collectors.toSet())
                : null;
    }

    public int getId() {
        return id;
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

    public Set<IngredientResponse> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
