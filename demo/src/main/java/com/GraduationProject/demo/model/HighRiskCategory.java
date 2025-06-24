package com.GraduationProject.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum HighRiskCategory {

    ELDERLY,
    PREGNANT,
    WEAK_IMMUNE_SYSTEM,
    SMOKERS,
    OBESE,
    GENETIC_MUTATION,
    INACTIVE,
    CHEMICAL_EXPOSURE,
    POLLUTED_AREAS;

    //2st5dmt de 34an mkn4 rade ehndl el uppercase fe el edit
    @JsonCreator
    public static  HighRiskCategory fromString(String value) {
        return HighRiskCategory.valueOf(value.toUpperCase());
    }


}
