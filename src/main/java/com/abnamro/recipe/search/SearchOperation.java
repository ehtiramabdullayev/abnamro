package com.abnamro.recipe.search;

public enum SearchOperation {

    CONTAINS, DOES_NOT_CONTAIN, EQUAL, NOT_EQUAL;


    public static SearchOperation getSimpleOperation(final String input) {
        String lowerInput = input.toLowerCase();
        switch (lowerInput) {
            case "cn":
                return CONTAINS;
            case "nc":
                return DOES_NOT_CONTAIN;
            case "eq":
                return EQUAL;
            case "ne":
                return NOT_EQUAL;
            default:
                return null;
        }
    }
}
