package com.abnamro.recipe.unit.controllers;

import com.abnamro.recipe.api.request.CreateRecipeRequest;
import com.abnamro.recipe.api.request.UpdateRecipeRequest;
import com.abnamro.recipe.api.response.CreateEntityResponse;
import com.abnamro.recipe.api.response.RecipeResponse;
import com.abnamro.recipe.controllers.RecipeController;
import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.services.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {
    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    public void test_createRecipe_successfully() {
        CreateRecipeRequest request = new CreateRecipeRequest("pasta", "OTHER", 4, null, "instructions");

        when(recipeService.createRecipe(any(CreateRecipeRequest.class))).thenReturn(1);

        CreateEntityResponse response = recipeController.createRecipe(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isSameAs(1);
    }

    @Test
    public void test_listRecipe_successfully() {
        Recipe Recipe = new Recipe();
        Recipe.setId(5);
        Recipe.setName("name");

        when(recipeService.getRecipeById(anyInt())).thenReturn(Recipe);

        RecipeResponse response = recipeController.getRecipe(5);

        assertThat(response.getId()).isSameAs(Recipe.getId());
        assertThat(response.getName()).isSameAs(Recipe.getName());
    }

    @Test
    public void test_listRecipes_successfully() {
        Recipe recipe = new Recipe();
        recipe.setId(5);
        recipe.setName("name1");

        Recipe recipe1 = new Recipe();
        recipe1.setId(6);
        recipe1.setName("name2");

        List<Recipe> storedRecipeList = new ArrayList<>();
        storedRecipeList.add(recipe);
        storedRecipeList.add(recipe1);

        when(recipeService.getRecipeList(anyInt(), anyInt())).thenReturn(storedRecipeList);

        List<RecipeResponse> recipeList = recipeController.getRecipeList(anyInt(), anyInt());

        assertThat(storedRecipeList.size()).isSameAs(recipeList.size());
        assertThat(storedRecipeList.get(0).getId()).isSameAs(recipeList.get(0).getId());
        assertThat(storedRecipeList.get(1).getId()).isSameAs(recipeList.get(1).getId());
    }

    @Test
    public void test_updateRecipe_successfully() {
        Recipe recipe = new Recipe();
        recipe.setName("name1");
        recipe.setType("OTHER");
        recipe.setInstructions("ins");

        doNothing().when(recipeService).updateRecipe(any());
        recipe.setName("name2");

        UpdateRecipeRequest request = new UpdateRecipeRequest(1, "pasta", "OTHER", 4, null, "instructions");

        recipeController.updateRecipe(request);
    }

    @Test
    public void test_deleteRecipe_successfully() {
        doNothing().when(recipeService).deleteRecipe(anyInt());
        recipeController.deleteRecipe(5);
    }
}