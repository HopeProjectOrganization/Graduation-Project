package com.GraduationProject.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum NewsCategory {
    ALL,
    BREAST,
    OVARIAN,
    PROSTATE,
    COLORECTAL,
    MELANOMA,
    LUNG,
    SKIN,
    PANCREATIC,
    LEUKEMIA,
    LYMPHOMA,
    BRAIN,
    LIVER,
    STOMACH,
    ESOPHAGEAL,
    BLADDER,
    KIDNEY,
    THYROID,
    BONE,
    TESTICULAR,
    ENDOMETRIAL,
    CERVICAL,
    GALLBLADDER,
    MULTIPLE_MYELOMA,
    ORAL;

    //2st5dmt de 34an mkn4 rade ehndl el uppercase fe el edit
    @JsonCreator
    public static NewsCategory fromString(String value) {
        return NewsCategory.valueOf(value.toUpperCase());
    }


}
