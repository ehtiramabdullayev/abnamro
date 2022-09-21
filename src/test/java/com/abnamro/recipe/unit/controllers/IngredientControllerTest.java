package com.abnamro.recipe.unit.controllers;


import com.abnamro.recipe.api.request.CreateIngredientRequest;
import com.abnamro.recipe.api.response.CreateEntityResponse;
import com.abnamro.recipe.api.response.IngredientResponse;
import com.abnamro.recipe.controllers.IngredientController;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.services.IngredientService;
import com.abnamro.recipe.utils.builder.IngredientTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {
    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientController ingredientController;

    @Test
    public void test_createIngredient_successfully() {
        when(ingredientService.create(any(CreateIngredientRequest.class))).thenReturn(1);

        CreateIngredientRequest request = IngredientTestDataBuilder.createIngredientRequest();
        CreateEntityResponse response = ingredientController.createIngredient(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isSameAs(1);
    }

    @Test
    public void test_getIngredient_successfully() {
        Ingredient ingredient = IngredientTestDataBuilder.createIngredient();
        ingredient.setId(5);

        when(ingredientService.findById(anyInt())).thenReturn(ingredient);

        IngredientResponse response = ingredientController.getIngredient(5);
        assertThat(response.getId()).isSameAs(5);
    }

    @Test
    public void test_listIngredients_successfully() {
        List<Ingredient> storedIngredientList = IngredientTestDataBuilder.createIngredientList(true);

        when(ingredientService.list(anyInt(), anyInt())).thenReturn(storedIngredientList);

        List<IngredientResponse> ingredientList = ingredientController.getIngredientList(anyInt(), anyInt());

        assertThat(storedIngredientList.size()).isSameAs(ingredientList.size());
        assertThat(storedIngredientList.get(0).getId()).isSameAs(ingredientList.get(0).getId());
        assertThat(storedIngredientList.get(1).getId()).isSameAs(ingredientList.get(1).getId());
    }

    @Test
    public void test_deleteIngredient_successfully() {
        doNothing().when(ingredientService).delete(anyInt());

        ingredientController.deleteIngredient(5);
    }

}