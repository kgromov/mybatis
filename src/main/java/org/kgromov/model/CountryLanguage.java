package org.kgromov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryLanguage {
    private LanguageCode id;
    private Boolean official;
    private BigDecimal usage;
}
