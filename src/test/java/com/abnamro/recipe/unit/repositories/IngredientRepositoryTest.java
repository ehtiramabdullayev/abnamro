package com.abnamro.recipe.unit.repositories;

import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.repositories.IngredientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IngredientRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void test_whenTryToSaveIngredientSuccess() {
        Ingredient entity = new Ingredient();
        entity.setIngredient("Tomato");
        Ingredient savedIngredient = ingredientRepository.save(entity);
        assertNotNull(savedIngredient);

        assertEquals("Tomato", savedIngredient.getIngredient());
        assertNotNull(savedIngredient.getId());
    }

    @Test
    public void test_whenTryGetTokenListSuccess() {
        Ingredient entity1 = new Ingredient();
        entity1.setIngredient("Tomato");

        Ingredient entity2 = new Ingredient();
        entity2.setIngredient("Potato");

        Ingredient firstSavedEntity = ingredientRepository.save(entity1);
        Ingredient secondSavedEntity = ingredientRepository.save(entity2);
        assertNotNull(firstSavedEntity);
        assertNotNull(secondSavedEntity);

        assertFalse(ingredientRepository.findAll().isEmpty());
        assertEquals(2, ingredientRepository.findAll().size());
    }
}