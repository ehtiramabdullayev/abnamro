package com.abnamro.recipe.controllers;

import com.abnamro.recipe.api.response.IngredientResponse;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.services.IngredientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "List all authors", authorizations = { @Authorization(value="JWT") })
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
}
