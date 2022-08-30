package com.abnamro.recipe.search;

import com.abnamro.recipe.models.Recipe;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class RecipeSpecification implements Specification<Recipe> {
    private static final String JOINED_TABLE_NAME = "recipeIngredients";
    private static final String INGREDIENT_KEY = "ingredient";
    private final SearchCriteria criteria;

    public RecipeSpecification(SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        String filterValue = criteria.getValue().toString().toLowerCase();
        String filterKey = criteria.getFilterKey();

        Join<Object, Object> subRoot = root.join(JOINED_TABLE_NAME, JoinType.INNER);
        query.distinct(true);

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(criteria.getOperation()))) {
            case CONTAINS:
                if (filterKey.equals(INGREDIENT_KEY)) {
                    return cb.like(cb.lower(subRoot.get(filterKey).as(String.class)), "%" + filterValue + "%");
                }
                return cb.like(cb.lower(root.get(filterKey).as(String.class)), "%" + filterValue + "%");

            case DOES_NOT_CONTAIN:
                if (filterKey.equals(INGREDIENT_KEY)) {
                    return cb.notLike(cb.lower(subRoot.get(filterKey).as(String.class)), "%" + filterValue + "%");
                }
                return cb.notLike(cb.lower(root.get(filterKey).as(String.class)), "%" + filterValue + "%");
            case EQUAL:
                if (filterKey.equals(INGREDIENT_KEY)) {
                    return cb.equal(subRoot.get(criteria.getFilterKey()).as(String.class), criteria.getValue());
                }
                return cb.equal(root.get(criteria.getFilterKey()).as(String.class), criteria.getValue());

            case NOT_EQUAL:
                if (filterKey.equals(INGREDIENT_KEY)) {
                    return cb.notEqual(subRoot.get(criteria.getFilterKey()).as(String.class), criteria.getValue());
                }
                return cb.notEqual(root.get(criteria.getFilterKey()).as(String.class), criteria.getValue());
        }


        return null;
    }
}
