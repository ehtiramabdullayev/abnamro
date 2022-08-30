package com.abnamro.recipe.services;

import com.abnamro.recipe.api.request.CreateRecipeRequest;
import com.abnamro.recipe.api.request.RecipeSearchRequest;
import com.abnamro.recipe.api.request.SearchCriteriaRequest;
import com.abnamro.recipe.api.request.UpdateRecipeRequest;
import com.abnamro.recipe.config.MessageProvider;
import com.abnamro.recipe.exceptions.NotFoundException;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.repositories.RecipeRepository;
import com.abnamro.recipe.search.RecipeSpecificationBuilder;
import com.abnamro.recipe.search.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;
    private final MessageProvider messageProvider;


    @Autowired
    public RecipeService(RecipeRepository recipeRepository,
                         IngredientService ingredientService,
                         MessageProvider messageProvider) {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
        this.messageProvider = messageProvider;
    }

    public Integer createRecipe(CreateRecipeRequest createRecipeRequest) {
        Set<Ingredient> ingredients = Optional.ofNullable(createRecipeRequest.getIngredientIds())
                .map(ingredientService::getIngredientsByIds)
                .orElse(null);

        Recipe recipe = new Recipe();
        recipe.setName(createRecipeRequest.getName());
        recipe.setInstructions(createRecipeRequest.getInstructions());
        recipe.setType(createRecipeRequest.getType());
        recipe.setNumberOfServings(createRecipeRequest.getNumberOfServings());
        recipe.setRecipeIngredients(ingredients);

        Recipe createdRecipe = recipeRepository.save(recipe);

        return createdRecipe.getId();
    }

    public List<Recipe> getRecipeList() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(int id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("recipe.notFound")));
    }

    public void updateRecipe(UpdateRecipeRequest updateRecipeRequest) {
        Recipe recipe = recipeRepository.findById(updateRecipeRequest.getId())
                .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("recipe.notFound")));

        Set<Ingredient> ingredients = Optional.ofNullable(updateRecipeRequest.getIngredientIds())
                .map(ingredientService::getIngredientsByIds)
                .orElse(null);

        recipe.setName(updateRecipeRequest.getName());
        recipe.setType(updateRecipeRequest.getType());
        recipe.setNumberOfServings(updateRecipeRequest.getNumberOfServings());
        recipe.setInstructions(updateRecipeRequest.getInstructions());

        if (ingredients != null) {
            recipe.setRecipeIngredients(ingredients);
        }

        recipeRepository.save(recipe);
    }

    public void deleteRecipe(int id) {
        if (!recipeRepository.existsById(id)) {
            throw new NotFoundException(messageProvider.getMessage("recipe.notFound"));
        }

        recipeRepository.deleteById(id);
    }

    public Page<Recipe> findBySearchCriteria(RecipeSearchRequest recipeSearchRequest,
                                             RecipeSpecificationBuilder builder,
                                             Pageable page) {
        List<SearchCriteriaRequest> requestList = recipeSearchRequest.getSearchCriteriaRequests();

        if (Optional.ofNullable(requestList).isPresent()) {
            List<SearchCriteria> searchCriteria = requestList.stream()
                    .map(SearchCriteria::new)
                    .collect(Collectors.toList());


            if (!searchCriteria.isEmpty()) {
                searchCriteria.forEach(criteria -> {
                    criteria.setDataOption(recipeSearchRequest.getDataOption());
                    builder.with(criteria);
                });
            }

            Specification<Recipe> recipeSpecification = builder.build().orElseThrow(() -> new NotFoundException(messageProvider.getMessage("criteria.notFound")));
            return recipeRepository.findAll(recipeSpecification, page);
        }
        throw new NotFoundException(messageProvider.getMessage("criteria.notFound"));
    }
}
