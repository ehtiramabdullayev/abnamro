package com.abnamro.recipe.search.filter;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchFilterDoesNotContain implements SearchFilter {
    private static final String INGREDIENT_KEY = "ingredient";

    @Override
    public boolean couldBeApplied(SearchOperation opt) {
        return opt == SearchOperation.DOES_NOT_CONTAIN;
    }

    @Override
    public Predicate apply(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root, Join<Object, Object> subRoot) {
        if (filterKey.equals(INGREDIENT_KEY)) {
            return cb.notLike(cb.lower(subRoot.get(filterKey).as(String.class)), "%" + filterValue + "%");
        }
        return cb.notLike(cb.lower(root.get(filterKey).as(String.class)), "%" + filterValue + "%");

    }
}
