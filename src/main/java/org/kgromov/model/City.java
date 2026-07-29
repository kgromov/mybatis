package org.kgromov.model;


public class City {
    public Long ID;
    public String Name;
    public String District;
    public Long Population;
    public Country country;


    public City() {
    }

    public City(Country country, String district, Long ID, String name, Long population) {
        this.country = country;
        District = district;
        this.ID = ID;
        Name = name;
        Population = population;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getPopulation() {
        return Population;
    }

    public void setPopulation(Long population) {
        Population = population;
    }
}
