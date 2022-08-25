package com.abnamro.recipe.api.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BasicRequest {

    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
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
