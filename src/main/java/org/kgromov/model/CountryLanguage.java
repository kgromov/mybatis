package org.kgromov.model;

import java.math.BigDecimal;

public class CountryLanguage {
    private String code;
    private String language;
    private Boolean official;
    private BigDecimal usage;


    public CountryLanguage() {
    }

    public CountryLanguage(String code, Boolean official, String language, BigDecimal usage) {
        this.code = code;
        this.official = official;
        this.language = language;
        this.usage = usage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getOfficial() {
        return official;
    }

    public void setOfficial(Boolean official) {
        this.official = official;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BigDecimal getUsage() {
        return usage;
    }

    public void setUsage(BigDecimal usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "code='" + code + '\'' +
                ", language='" + language + '\'' +
                ", official=" + official +
                ", usage=" + usage +
                '}';
    }
}
