package org.kgromov.testcontainers;

import org.junit.jupiter.api.Test;
import org.kgromov.mappers.CityMapper;
import org.kgromov.model.City;
import org.kgromov.model.Country;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CityTestContainersMapperTest extends MysqlTestContainersTest {
    @Autowired
    private CityMapper cityMapper;

    @Test
    void findAll_whenAgainstProdDb_thenHas4079Cities() {
        assertThat(cityMapper.findAll()).hasSize(57);
    }

    @Test
    void findAllById_whenAgainstProdDb_thenHasOdesa() {
        var odesa = cityMapper.findById(3430L);

        assertThat(odesa.getName()).isEqualTo("Odesa");
        assertThat(odesa.getPopulation()).isGreaterThan(1_000_000);
        assertThat(odesa.getCountry().getName()).isEqualTo("Ukraine");
    }

    @Test
    void findAllByCountryCode_whenAgainstProdDb_thenHasUkrainian57Cities() {
        List<City> ukrainianCities = cityMapper.findAllByCountryCode("UKR");

        assertThat(ukrainianCities).hasSize(57);
        assertThat(ukrainianCities).extracting(City::getCountry).extracting(Country::getName).containsOnly("Ukraine");
        assertThat(ukrainianCities).extracting(City::getName).contains("Odesa");
    }
}