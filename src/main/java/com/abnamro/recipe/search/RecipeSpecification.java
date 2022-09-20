package com.abnamro.recipe.search;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.search.filter.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static com.abnamro.recipe.config.DatabaseAttributes.JOINED_TABLE_NAME;

public class RecipeSpecification implements Specification<Recipe> {
    private final SearchCriteria criteria;

    private List<SearchFilter> searchFilters;

    public RecipeSpecification(SearchCriteria criteria) {
        super();
        filterList();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SearchOperation operation = SearchOperation.getSimpleOperation(criteria.getOperation());
        String filterValue = criteria.getValue().toString().toLowerCase();
        String filterKey = criteria.getFilterKey();

        Join<Object, Object> subRoot = root.join(JOINED_TABLE_NAME, JoinType.INNER);
        query.distinct(true);

        for (SearchFilter searchFilter : searchFilters) {
            if (searchFilter.couldBeApplied(operation)) {
                return searchFilter.apply(cb, filterKey, filterValue, root, subRoot);
            }
        }

        return null;
    }

    private void filterList() {
        searchFilters = new ArrayList<>();
        searchFilters.add(new SearchFilterEqual());
        searchFilters.add(new SearchFilterNotEqual());
        searchFilters.add(new SearchFilterContains());
        searchFilters.add(new SearchFilterDoesNotContain());
    }
}
