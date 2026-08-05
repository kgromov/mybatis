package org.kgromov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CountryLanguage {
    private LanguageCode id;
    private Boolean official;
    private BigDecimal usage;
}
