package com.naamad.enums;

import lombok.Getter;

@Getter
public enum Languages {
    ENGLISH("en"),
    ITALIAN("it"),
    SPANISH("es"),
    GERMAN("de");

    private final String languageCode;
    Languages(String languageCode) {
        this.languageCode = languageCode;
    }
}
