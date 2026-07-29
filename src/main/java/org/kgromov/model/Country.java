package org.kgromov.model;

import java.math.BigDecimal;

public class Country {
    public String Code;
    public String Name;
    public BigDecimal SurfaceArea;
    public Integer Population;
    public String Capital;
    public String GovernmentForm;
    public String Continent;
    public String Region;
    public Short IndepYear;
    public BigDecimal LifeExpectancy;
    public BigDecimal GNP;
    public BigDecimal GNPOld;
    public String LocalName;
    public String HeadOfState;
    public String Code2;

    public Country() {
    }

    public Country(String capital, String code2, String code, String continent, BigDecimal GNP, BigDecimal GNPOld, String governmentForm, String headOfState, Short indepYear, BigDecimal lifeExpectancy, String localName, String name, Integer population, String region, BigDecimal surfaceArea) {
        Capital = capital;
        Code2 = code2;
        Code = code;
        Continent = continent;
        this.GNP = GNP;
        this.GNPOld = GNPOld;
        GovernmentForm = governmentForm;
        HeadOfState = headOfState;
        IndepYear = indepYear;
        LifeExpectancy = lifeExpectancy;
        LocalName = localName;
        Name = name;
        Population = population;
        Region = region;
        SurfaceArea = surfaceArea;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getCode2() {
        return Code2;
    }

    public void setCode2(String code2) {
        Code2 = code2;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public BigDecimal getGNP() {
        return GNP;
    }

    public void setGNP(BigDecimal GNP) {
        this.GNP = GNP;
    }

    public BigDecimal getGNPOld() {
        return GNPOld;
    }

    public void setGNPOld(BigDecimal GNPOld) {
        this.GNPOld = GNPOld;
    }

    public String getGovernmentForm() {
        return GovernmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        GovernmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return HeadOfState;
    }

    public void setHeadOfState(String headOfState) {
        HeadOfState = headOfState;
    }

    public Short getIndepYear() {
        return IndepYear;
    }

    public void setIndepYear(Short indepYear) {
        IndepYear = indepYear;
    }

    public BigDecimal getLifeExpectancy() {
        return LifeExpectancy;
    }

    public void setLifeExpectancy(BigDecimal lifeExpectancy) {
        LifeExpectancy = lifeExpectancy;
    }

    public String getLocalName() {
        return LocalName;
    }

    public void setLocalName(String localName) {
        LocalName = localName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPopulation() {
        return Population;
    }

    public void setPopulation(Integer population) {
        Population = population;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public BigDecimal getSurfaceArea() {
        return SurfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        SurfaceArea = surfaceArea;
    }
}
