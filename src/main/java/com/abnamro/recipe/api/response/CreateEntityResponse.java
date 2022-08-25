package com.abnamro.recipe.api.response;

public class CreateEntityResponse {

    private int id;

    public CreateEntityResponse(int id) {
        this.id = id;
    }

    public CreateEntityResponse() {
    }

    public int getId() {
        return id;
    }
}
