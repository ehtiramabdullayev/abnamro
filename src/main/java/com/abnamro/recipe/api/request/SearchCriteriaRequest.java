package com.abnamro.recipe.api.request;

import com.abnamro.recipe.api.request.input.FilterKeyReqInput;
import com.abnamro.recipe.api.request.input.SearchOperationReqInput;
import com.abnamro.recipe.validator.EnumValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

@Valid
public class SearchCriteriaRequest {

    @ApiModelProperty(notes = "The name of the column you want to search on available fields are " +
            "name, " +
            "numberOfServings, " +
            "type, " +
            "instructions, " +
            "ingredientName)", example = "name")
    @EnumValidator(enumClass = FilterKeyReqInput.class, message = "{filterKey.invalid}")
    private String filterKey;


    @ApiModelProperty(notes = "The actual phrase you want to do search on", example = "Pasta")
    private Object value;

    @ApiModelProperty(notes = "The operation type you wanted to search (cn - contains, " +
            "nc - doesn't contain, " +
            "eq - equals, " +
            "ne - not equals", example = "cn")
    @EnumValidator(enumClass = SearchOperationReqInput.class, message = "{searchOperation.invalid}")
    private String operation;

    @ApiModelProperty(hidden = true)
    private String dataOption;

    public SearchCriteriaRequest() {
    }

    public SearchCriteriaRequest(String filterKey, Object value, String operation) {
        this.filterKey = filterKey;
        this.value = value;
        this.operation = operation;
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
