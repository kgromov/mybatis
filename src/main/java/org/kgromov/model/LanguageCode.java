package org.kgromov.model;


import java.io.Serializable;

public class LanguageCode implements Serializable {

    private String CountryCode;
    private String Language;

    public LanguageCode() {
    }

    public LanguageCode(String countryCode, String language) {
        CountryCode = countryCode;
        Language = language;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}
