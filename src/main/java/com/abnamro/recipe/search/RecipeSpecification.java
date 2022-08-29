package com.abnamro.recipe.search;

import com.abnamro.recipe.models.Recipe;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class RecipeSpecification implements Specification<Recipe> {

    private final SearchCriteria searchCriteria;

    public RecipeSpecification(SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();
        String filterKey = searchCriteria.getFilterKey();
        Join<Object, Object> subRoot = root.join("recipeIngredients", JoinType.INNER);
        query.distinct(true);

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case CONTAINS:
                if (filterKey.equals("ingredientName")) {
                    return cb.like(cb.lower(subRoot.get(filterKey).as(String.class)), "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(filterKey).as(String.class)), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if (filterKey.equals("ingredientName")) {
                    return cb.notLike(cb.lower(subRoot.get(filterKey).as(String.class)), "%" + strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(filterKey).as(String.class)), "%" + strToSearch + "%");
            case EQUAL:
                if (searchCriteria.getFilterKey().equals("ingredientName")) {
                    return cb.equal(subRoot.get(searchCriteria.getFilterKey()).as(String.class), searchCriteria.getValue());
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()).as(String.class), searchCriteria.getValue());

            case NOT_EQUAL:
                if (searchCriteria.getFilterKey().equals("ingredientName")) {
                    return cb.notEqual(subRoot.get(searchCriteria.getFilterKey()).as(String.class), searchCriteria.getValue());
                }
                return cb.notEqual(root.get(searchCriteria.getFilterKey()).as(String.class), searchCriteria.getValue());
        }


        return null;
    }
}
