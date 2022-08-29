package com.abnamro.recipe.api.request;

import com.abnamro.recipe.search.SearchCriteria;

import java.util.List;

public class RecipeSearchRequest {
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

    public RecipeSearchRequest() {
    }

    public RecipeSearchRequest(List<SearchCriteria> searchCriteriaList, String dataOption) {
        this.searchCriteriaList = searchCriteriaList;
        this.dataOption = dataOption;
    }

    public List<SearchCriteria> getSearchCriteriaList() {
        return searchCriteriaList;
    }

    public String getDataOption() {
        return dataOption;
    }
}
