package org.kgromov.model;

import java.math.BigDecimal;

public class CountryLanguage {
    private String CountryCode;
    private String Language;
    private Boolean IsOfficial;
    private BigDecimal Percentage;


    public CountryLanguage() {
    }

    public CountryLanguage(String countryCode, Boolean isOfficial, String language, BigDecimal Percentage) {
        CountryCode = countryCode;
        IsOfficial = isOfficial;
        Language = language;
        this.Percentage = Percentage;
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

    public BigDecimal getPercentage() {
        return Percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.Percentage = percentage;
    }
}
