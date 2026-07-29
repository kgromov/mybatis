package org.kgromov.model;

import java.math.BigDecimal;

public class CountryLanguage {
    private String CountryCode;
    private String Language;
    private Boolean IsOfficial;
    private BigDecimal usage;


    public CountryLanguage() {
    }

    public CountryLanguage(String countryCode, Boolean isOfficial, String language, BigDecimal usage) {
        CountryCode = countryCode;
        IsOfficial = isOfficial;
        Language = language;
        this.usage = usage;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public Boolean getOfficial() {
        return IsOfficial;
    }

    public void setOfficial(Boolean official) {
        IsOfficial = official;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public BigDecimal getUsage() {
        return usage;
    }

    public void setUsage(BigDecimal usage) {
        this.usage = usage;
    }
}
