package com.abnamro.recipe.search;

public enum DataOption {
    ANY, ALL;

    public static DataOption getDataOption(final String dataOption) {
        String lowerDataOption = dataOption.toLowerCase();
        switch (lowerDataOption) {
            case "all":
                return ALL;
            case "any":
                return ANY;
            default:
                return null;
        }
    }
}
