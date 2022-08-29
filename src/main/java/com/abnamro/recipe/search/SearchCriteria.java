package com.abnamro.recipe.search;

public class SearchCriteria {
    private String filterKey;
    private Object value;
    private String operation;
    private String dataOption;

    public SearchCriteria() {
    }

    public SearchCriteria(String filterKey, String operation, Object value){
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public Object getValue() {
        return value;
    }

    public String getOperation() {
        return operation;
    }

    public String getDataOption() {
        return dataOption;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setDataOption(String dataOption) {
        this.dataOption = dataOption;
    }
}
