package com.abnamro.recipe.controllers;

import com.abnamro.recipe.api.request.CreateRecipeRequest;
import com.abnamro.recipe.api.request.UpdateRecipeRequest;
import com.abnamro.recipe.api.response.CreateEntityResponse;
import com.abnamro.recipe.api.response.RecipeResponse;
import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.services.RecipeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "RecipeController", tags = "Recipe Controller", description = "Create, update, delete, list recipes")
@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @ApiOperation(value = "List all recipes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
    })
    @RequestMapping(method = RequestMethod.GET)
    public List<RecipeResponse> getRecipeList() {
        List<Recipe> list = recipeService.getRecipeList();

        return list.stream()
                .map(RecipeResponse::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "List one recipe by its ID", response = RecipeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 404, message = "Recipe not found by the given ID")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RecipeResponse getRecipe(@ApiParam(value = "Recipe ID", required = true) @PathVariable(name = "id") Integer id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return new RecipeResponse(recipe);
    }

    @ApiOperation(value = "Create a recipe")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Recipe created"),
            @ApiResponse(code = 400, message = "Bad input")
    })
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEntityResponse createRecipe(
            @ApiParam(value = "Properties of the recipe", required = true) @Valid @RequestBody CreateRecipeRequest request) {
        Integer id = recipeService.createRecipe(request);
        return new CreateEntityResponse(id);
    }

    @ApiOperation(value = "Update the recipe")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ingredient created"),
            @ApiResponse(code = 400, message = "Bad input")
    })
    @RequestMapping(method = RequestMethod.PATCH)
    public void updateRecipe(
            @ApiParam(value = "Properties of the recipe", required = true) @Valid @RequestBody UpdateRecipeRequest updateRecipeRequest) {
        recipeService.updateRecipe(updateRecipeRequest);
    }

    @ApiOperation(value = "Delete the recipe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Recipe not found by the given ID")
    })
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteRecipe(
            @ApiParam(value = "Recipe ID", required = true) @NotNull(message = "{id.notNull}") @RequestParam(name = "id") Integer id) {
        recipeService.deleteRecipe(id);
    }
}
