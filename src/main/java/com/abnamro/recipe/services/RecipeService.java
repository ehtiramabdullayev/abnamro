package com.abnamro.recipe.services;

import com.abnamro.recipe.api.request.CreateRecipeRequest;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
    }

    public Integer createRecipe(CreateRecipeRequest createRecipeRequest) {
        Set<Ingredient> ingredients = Optional.ofNullable(createRecipeRequest.getIngredientIds())
                .map(ingredientService::getIngredientsByIds)
                .orElse(null);

        Recipe recipe = new Recipe();
        recipe.setName(createRecipeRequest.getName());
        recipe.setInstruction(createRecipeRequest.getInstructions());
        recipe.setType(createRecipeRequest.getType());
        recipe.setNumberOfServings(createRecipeRequest.getNumberOfServings());
        recipe.setRecipeIngredients(ingredients);

        Recipe createdRecipe = recipeRepository.save(recipe);

        return createdRecipe.getId();
    }
}
