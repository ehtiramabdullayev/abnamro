package com.abnamro.recipe.search;

import com.abnamro.recipe.models.Recipe;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class RecipeSpecification implements Specification<Recipe> {
    private final SearchCriteria criteria;

    public RecipeSpecification(SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        String strToSearch = criteria.getValue().toString().toLowerCase();
        String filterKey = criteria.getFilterKey();

        Join<Object, Object> subRoot = root.join("recipeIngredients", JoinType.INNER);
        query.distinct(true);

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(criteria.getOperation()))) {
            case CONTAINS:
                if (filterKey.equals("ingredient")) {
                    return cb.like(cb.lower(subRoot.get(filterKey).as(String.class)), "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(filterKey).as(String.class)), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if (filterKey.equals("ingredient")) {
                    return cb.notLike(cb.lower(subRoot.get(filterKey).as(String.class)), "%" + strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(filterKey).as(String.class)), "%" + strToSearch + "%");
            case EQUAL:
                if (filterKey.equals("ingredient")) {
                    return cb.equal(subRoot.get(criteria.getFilterKey()).as(String.class), criteria.getValue());
                }
                return cb.equal(root.get(criteria.getFilterKey()).as(String.class), criteria.getValue());

            case NOT_EQUAL:
                if (filterKey.equals("ingredient")) {
                    return cb.notEqual(subRoot.get(criteria.getFilterKey()).as(String.class), criteria.getValue());
                }
                return cb.notEqual(root.get(criteria.getFilterKey()).as(String.class), criteria.getValue());
        }


        return null;
    }
}
