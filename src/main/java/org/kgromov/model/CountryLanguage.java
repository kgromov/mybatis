package org.kgromov.model;

import java.math.BigDecimal;

public record CountryLanguage(
        String code,
        String language,
        Boolean official,
        BigDecimal usage
) {
}