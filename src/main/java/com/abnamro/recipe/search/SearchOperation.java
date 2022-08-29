package com.abnamro.recipe.search;

public enum SearchOperation {

    CONTAINS, DOES_NOT_CONTAIN, EQUAL, NOT_EQUAL,
    ANY, ALL;


    public static SearchOperation getDataOption(final String dataOption) {
        switch (dataOption) {
            case "all":
                return ALL;
            case "any":
                return ANY;
            default:
                return null;
        }
    }

    public static SearchOperation getSimpleOperation(final String input) {
        switch (input) {
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
