package com.abnamro.recipe.services;

import com.abnamro.recipe.api.request.CreateIngredientRequest;
import com.abnamro.recipe.config.MessageProvider;
import com.abnamro.recipe.exceptions.NotFoundException;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.repositories.IngredientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    private final MessageProvider messageProvider;

    public IngredientService(IngredientRepository ingredientRepository, MessageProvider messageProvider) {
        this.ingredientRepository = ingredientRepository;
        this.messageProvider = messageProvider;
    }

    public Integer create(CreateIngredientRequest request) {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredient(request.getName());

        Ingredient createdIngredient = ingredientRepository.save(ingredient);
        return createdIngredient.getId();
    }


    public Set<Ingredient> getIngredientsByIds(List<Integer> ingredientIds) {
        return ingredientIds.stream()
                .map(this::findById)
                .collect(Collectors.toSet());
    }

    public Ingredient findById(int id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("ingredient.notFound")));
    }

    public List<Ingredient> list(int page, int size) {
        Pageable pageRequest
                = PageRequest.of(page, size);
        return ingredientRepository.findAll(pageRequest).getContent();
    }

    public void delete(int id) {
        if (!ingredientRepository.existsById(id)) {
            throw new NotFoundException(messageProvider.getMessage("ingredient.notFound"));
        }
        ingredientRepository.deleteById(id);
    }
}
