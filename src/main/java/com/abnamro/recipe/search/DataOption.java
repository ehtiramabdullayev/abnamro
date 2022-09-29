package com.abnamro.recipe.search;

import java.util.Optional;

public enum DataOption {
    ANY, ALL;

    public static Optional<DataOption> getDataOption(final String dataOption) {
        String lowerDataOption = dataOption.toLowerCase();
        switch (lowerDataOption) {
            case "all":
                return Optional.of(ALL);
            case "any":
                return Optional.of(ANY);
        }
        return Optional.empty();
    }
}
