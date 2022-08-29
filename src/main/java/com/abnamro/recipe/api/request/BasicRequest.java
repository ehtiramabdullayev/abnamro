package com.abnamro.recipe.api.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BasicRequest {

    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
    @ApiModelProperty(notes = "Id of the attribute", example = "1")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public BasicRequest() {
    }

    public BasicRequest(Integer id) {
        this.id = id;
    }
}
