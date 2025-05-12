package com.GraduationProject.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DietCategory {
    RECOMMENDED_FOODS,
    RECIPES,
    HELPFUL_FOODS,
    BAD_FOODS;

    @JsonCreator
    public static DietCategory fromString(String value) {
        return DietCategory.valueOf(value.toUpperCase());
    }
}
