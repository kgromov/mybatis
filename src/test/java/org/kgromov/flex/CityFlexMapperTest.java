package org.kgromov.flex;

import org.junit.jupiter.api.*;
import org.kgromov.mappers.flex.CityFlexMapper;
import org.kgromov.mappers.flex.CountryFlexMapper;
import org.kgromov.model.City;
import org.kgromov.model.Country;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class CityFlexMapperTest extends MyBatisFlexMapperTest {
    @Autowired
    private CityFlexMapper cityFlexMapper;
    @Autowired
    private CountryFlexMapper countryFlexMapper;

    @Order(0)
    @Test
    void selectAll_whenNoCitiesExist_thenReturnCitiesWithoutCountry() {
        var allCities = cityFlexMapper.selectAll();

        assertThat(allCities).hasSize(57);
        assertThat(allCities).extracting(City::getId).isNotNull();
        assertThat(allCities).extracting(City::getName).isNotNull();
        assertThat(allCities).extracting(City::getPopulation).isNotNull();
        assertThat(allCities).extracting(City::getDistrict).isNotNull();
        assertThat(allCities).extracting(City::getCountry).containsOnlyNulls();
    }

    @Order(0)
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

    @Order(0)
    @Test
    void selectOneById_whenAgainstUkraineCities_thenHasKyivWithNullCountry() {
        var kyiv = cityFlexMapper.selectOneById(1L);

        assertThat(kyiv).isNotNull();
        assertThat(kyiv.getName()).isEqualTo("Kyiv");
        assertThat(kyiv.getPopulation()).isGreaterThan(2_500_000);
        assertThat(kyiv.getCountry()).isNull();
    }

    @Order(0)
    @Test
    void selectOneById_whenAgainstUkraineCities_thenHasKyivWithCountryUkraine() {
        var kyiv = cityFlexMapper.selectOneWithRelationsById(1L);

        assertThat(kyiv).isNotNull();
        assertThat(kyiv.getName()).isEqualTo("Kyiv");
        assertThat(kyiv.getPopulation()).isGreaterThan(2_500_000);
        assertThat(kyiv.getCountry()).isNotNull();
        assertThat(kyiv.getCountry().getName()).isEqualTo("Ukraine");
        assertThat(kyiv.getCountry().getIndepYear()).isEqualTo(Short.valueOf(("1991")));
    }

    @Order(0)
    @Test
    void selectOneByMap_whenSearchByUniqueName_thenHasOdesaWithNullCountry() {
        var odesa = cityFlexMapper.selectOneByMap(Map.of("name", "Odesa"));

        assertThat(odesa).isNotNull();
        assertThat(odesa.getName()).isEqualTo("Odesa");
        assertThat(odesa.getPopulation()).isGreaterThan(1_000_000);
        assertThat(odesa.getCountry()).isNull();
    }

    @Order(0)
    @Test
    void selectOneByMap_whenSearchByUniqueName_thenHasOdesaWithCountry() {
        var odesa = cityFlexMapper.selectOneWithRelationsByMap(Map.of("name", "Odesa"));

        assertThat(odesa).isNotNull();
        assertThat(odesa.getName()).isEqualTo("Odesa");
        assertThat(odesa.getPopulation()).isGreaterThan(1_000_000);
        assertThat(odesa.getCountry().getName()).isNotNull();
        assertThat(odesa.getCountry().getName()).isEqualTo("Ukraine");
        assertThat(odesa.getCountry().getCode2()).isEqualTo("UA");
    }

    @Disabled
    @Test
    void selectListByMap_whenNestedProperty_thenHasUkrainian57Cities() {
        List<City> ukrainianCities = cityFlexMapper.selectListByMap(Map.of("country.code", "UKR"));

        assertThat(ukrainianCities).hasSize(57);
        assertThat(ukrainianCities).extracting(City::getCountry).extracting(Country::getName).containsOnly("Ukraine");
        assertThat(ukrainianCities).extracting(City::getName).contains("Odesa");
    }

    @Test
    @Order(1)
    void insert_whenParentCountryExists_thenInsertNewCity() {
        Country ukraine = countryFlexMapper.selectOneById("UKR");
        City newCity = City.builder()
                .name("Pity Pen")
                .district("Pity District")
                .population(10L)
                .country(ukraine)
                .countryCode(ukraine.getCode())
                .build();

        cityFlexMapper.insert(newCity);

        assertThat(newCity.getId()).isNotNull();
        City insertedCity = cityFlexMapper.selectOneById(newCity.getId());
        assertThat(insertedCity).isNotNull();
        assertThat(insertedCity.getName()).isEqualTo("Pity Pen");
    }

    @Test
    @Order(2)
    void update_whenCityExists_thenUpdateExistingCity() {
        Country ukraine = countryFlexMapper.selectOneById("UKR");
        City odesa = cityFlexMapper.selectOneByMap(Map.of("name", "Odesa"));
        odesa.setDistrict("Old city");
        odesa.setPopulation(12L);
        Long odesaId = odesa.getId();

        cityFlexMapper.update(odesa);

        City updatedCity = cityFlexMapper.selectOneWithRelationsById(odesa.getId());
        assertThat(updatedCity).isNotNull();
        assertThat(updatedCity.getId()).isEqualTo(odesaId);
        assertThat(updatedCity.getName()).isEqualTo("Odesa");
        assertThat(updatedCity.getPopulation()).isEqualTo(12L);
        assertThat(updatedCity.getDistrict()).isEqualTo("Old city");
        assertThat(updatedCity.getCountry().getName()).isEqualTo(ukraine.getName());
    }

    @Test
    @Order(3)
    void delete_whenAgainstTestContainers_thenInsertNewCity() {
        cityFlexMapper.deleteById(58L);

        City pityPen = cityFlexMapper.selectOneById(58L);

        assertThat(pityPen).isNull();
    }
}