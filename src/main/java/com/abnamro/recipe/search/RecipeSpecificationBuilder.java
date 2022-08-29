package com.abnamro.recipe.search;

import com.abnamro.recipe.models.Recipe;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class RecipeSpecificationBuilder {
    private final List<SearchCriteria> params;

    public RecipeSpecificationBuilder(List<SearchCriteria> searchCriteria) {
        this.params = searchCriteria;
    }

    public final RecipeSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Optional<Specification<Recipe>> build(){
        if(params.size() == 0){
            return Optional.empty();
        }

        Specification<Recipe> result = new RecipeSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new RecipeSpecification(criteria))
                    : Specification.where(result).or(new RecipeSpecification(criteria));
        }

        return Optional.of(result);
    }
}
