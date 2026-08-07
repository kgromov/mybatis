package org.kgromov.testcontainers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.kgromov.mappers.java.CityJavaMapper;
import org.kgromov.mappers.CountryMapper;
import org.kgromov.model.City;
import org.kgromov.model.Country;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityMapperCrudTest extends MysqlTestContainersTest {
    @Autowired
    private CityJavaMapper cityJavaMapper;
    @Autowired
    private CountryMapper countryMapper;

    @Test
    @Order(0)
    void insert_whenParentCountryExists_thenInsertNewCity() {
        Country ukraine = countryMapper.findById("UKR");
        City newCity = City.builder()
                .name("Pity Pen")
                .district("Pity District")
                .population(10L)
                .country(ukraine)
                .build();

        cityJavaMapper.insert(newCity);

        assertThat(newCity.getId()).isEqualTo(58);
    }

    @Test
    @Order(1)
    void update_whenCityExists_thenUpdateExistingCity() {
        Country ukraine = countryMapper.findById("UKR");
        City odesa = cityJavaMapper.findByName("Odesa");
        odesa.setDistrict("Old city");
        odesa.setPopulation(12L);
        Long odesaId = odesa.getId();

        cityJavaMapper.update(odesa);

        assertThat(odesa.getId()).isEqualTo(odesaId);
        assertThat(odesa.getName()).isEqualTo("Odesa");
        assertThat(odesa.getPopulation()).isEqualTo(12L);
        assertThat(odesa.getDistrict()).isEqualTo("Old city");
        assertThat(odesa.getCountry().getName()).isEqualTo(ukraine.getName());
    }

    @Test
    @Order(2)
    void delete_whenAgainstTestContainers_thenInsertNewCity() {
        cityJavaMapper.delete(58L);

        City pityPen = cityJavaMapper.findById(58L);

        assertThat(pityPen).isNull();
    }
}