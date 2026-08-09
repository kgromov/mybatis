package org.kgromov.flex;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.kgromov.mappers.flex.CityFlexMapper;
import org.kgromov.model.City;
import org.kgromov.model.Country;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CityFlexMapperTest extends MyBatisFlexMapperTest {
    @Autowired
    private CityFlexMapper cityFlexMapper;

    @Test
    void selectAll_whenNoCitiesExist_thenReturnCitiesWithoutCountry() {
        var allCities = cityFlexMapper.selectAll();

        assertThat(allCities).hasSize(57);
        assertThat(allCities).extracting(City::getId).isNotNull();
        assertThat(allCities).extracting(City::getName).isNotNull();
        assertThat(allCities).extracting(City::getPopulation).isNotNull();
        assertThat(allCities).extracting(City::getDistrict).isNotNull();
        assertThat(allCities).extracting(City::getCountry).isNull();
    }

    @Test
    void selectAllWithRelations_whenNoCitiesExist_thenReturnCitiesWithCountry() {
        var allCities = cityFlexMapper.selectAllWithRelations();

        assertThat(allCities).hasSize(57);
        assertThat(allCities).extracting(City::getId).isNotNull();
        assertThat(allCities).extracting(City::getName).isNotNull();
        assertThat(allCities).extracting(City::getPopulation).isNotNull();
        assertThat(allCities).extracting(City::getDistrict).isNotNull();
        assertThat(allCities).extracting(City::getCountry).isNotNull();
        assertThat(allCities).extracting(City::getCountry).extracting(Country::getCode).containsOnly("UKR");
        assertThat(allCities).extracting(City::getCountry).extracting(Country::getName).containsOnly("Ukraine");
    }

    @Test
    void selectOneById_whenAgainstProdDb_thenHasOdesa() {
        var odesa = cityFlexMapper.selectOneById(3430L);

        assertThat(odesa).isNotNull();
        assertThat(odesa.getName()).isEqualTo("Odesa");
        assertThat(odesa.getPopulation()).isGreaterThan(1_000_000);
        assertThat(odesa.getCountry().getName()).isEqualTo("Ukraine");
    }

    @Test
    void findAllById_whenSearchByUniqueName_thenHasOdesa() {
        var odesa = cityFlexMapper.selectOneByMap(Map.of("name", "Odesa"));

        assertThat(odesa).isNotNull();
        assertThat(odesa.getName()).isEqualTo("Odesa");
        assertThat(odesa.getPopulation()).isGreaterThan(1_000_000);
        assertThat(odesa.getCountry().getName()).isEqualTo("Ukraine");
    }

    @Ignore
    @Test
    void selectListByMap_whenNestedProperty_thenHasUkrainian57Cities() {
        List<City> ukrainianCities = cityFlexMapper.selectListByMap(Map.of("country.code", "UKR"));

        assertThat(ukrainianCities).hasSize(57);
        assertThat(ukrainianCities).extracting(City::getCountry).extracting(Country::getName).containsOnly("Ukraine");
        assertThat(ukrainianCities).extracting(City::getName).contains("Odesa");
    }
}