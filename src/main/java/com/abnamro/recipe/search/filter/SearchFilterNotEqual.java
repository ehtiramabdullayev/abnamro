package com.abnamro.recipe.search.filter;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.abnamro.recipe.config.DatabaseAttributes.INGREDIENT_KEY;

public class SearchFilterNotEqual implements SearchFilter {

    @Override
    public boolean couldBeApplied(SearchOperation opt) {
        return opt == SearchOperation.NOT_EQUAL;
    }

    @Override
    public Predicate apply(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root, Join<Object, Object> subRoot) {
        if (filterKey.equals(INGREDIENT_KEY))
            return cb.notEqual(subRoot.get(filterKey).as(String.class), filterValue);

        return cb.notEqual(root.get(filterKey).as(String.class), filterValue);
    }
}
