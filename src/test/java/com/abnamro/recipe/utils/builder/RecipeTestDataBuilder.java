package com.abnamro.recipe.utils.builder;

import com.abnamro.recipe.models.Recipe;

public class RecipeTestDataBuilder {
    public static Recipe createRecipe() {
        return createRecipe(null);
    }

    public static Recipe createRecipe(Integer id) {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setName("pasta");
        recipe.setNumberOfServings(5);
        recipe.setType("OTHER");
        recipe.setInstructions("someInstruction");

        return recipe;
    }
}
