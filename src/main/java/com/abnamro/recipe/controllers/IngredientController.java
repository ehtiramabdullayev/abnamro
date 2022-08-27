package com.abnamro.recipe.controllers;

import com.abnamro.recipe.api.request.CreateIngredientRequest;
import com.abnamro.recipe.api.request.UpdateRecipeRequest;
import com.abnamro.recipe.api.response.CreateEntityResponse;
import com.abnamro.recipe.api.response.IngredientResponse;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.services.IngredientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "IngredientController", tags = "Ingredient Controller", description = "Create, update, delete, list ingredients")
@RestController
@RequestMapping(value = "/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @ApiOperation(value = "List all ingredients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
    })
    @RequestMapping(method = RequestMethod.GET)
    public List<IngredientResponse> getIngredientList() {
        List<Ingredient> list = ingredientService.list();

        return list
                .stream()
                .map(IngredientResponse::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "List one ingredient by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 404, message = "Ingredient not found by the given ID")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public IngredientResponse getIngredient(@ApiParam(value = "Ingredient ID", required = true) @PathVariable(name = "id") Integer id) {
        Ingredient ingredient = ingredientService.findById(id);
        return new IngredientResponse(ingredient);
    }

    @ApiOperation(value = "Create an ingredient")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ingredient created"),
            @ApiResponse(code = 400, message = "Bad input")
    })
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEntityResponse createIngredient(
            @ApiParam(value = "Properties of the Ingredient", required = true) @Valid @RequestBody CreateIngredientRequest request) {
        Integer id = ingredientService.create(request);
        return new CreateEntityResponse(id);
    }

}
