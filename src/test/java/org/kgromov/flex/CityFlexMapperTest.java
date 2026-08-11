package org.kgromov.flex;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryTable;
import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.kgromov.mappers.flex.CityFlexMapper;
import org.kgromov.mappers.flex.CountryFlexMapper;
import org.kgromov.model.City;
import org.kgromov.model.Country;
import org.kgromov.model.projections.CityCountryView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.mybatisflex.core.query.QueryMethods.column;
import static org.assertj.core.api.Assertions.assertThat;
import static org.kgromov.model.table.CityTableDef.CITY;
import static org.kgromov.model.table.CountryTableDef.COUNTRY;

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
        Long odesaId = odesa.getId();

        cityFlexMapper.update(odesa);

        City updatedCity = cityFlexMapper.selectOneWithRelationsById(odesa.getId());
        assertThat(updatedCity).isNotNull();
        assertThat(updatedCity.getId()).isEqualTo(odesaId);
        assertThat(updatedCity.getName()).isEqualTo("Odesa");
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

    @Test
    void selectListByQuery_whenSearchByOwnProperty_thenReturnsExpectedCities() {
        QueryWrapper selectUACitiesOver1M = QueryWrapper.create()
                .where(City::getCountryCode).eq("UKR")
                .and(City::getPopulation).ge(1_000_000)
                .orderBy(column(City::getPopulation).desc());

        List<City> ukrainianCities = cityFlexMapper.selectListByQuery(selectUACitiesOver1M);

        assertThat(ukrainianCities).hasSize(5);
        assertThat(ukrainianCities).extracting(City::getName)
                .containsExactly("Kyiv", /*Kharkiv*/ "Harkova [Harkiv]", /*Dnipro*/"Dnipropetrovsk", "Donetsk", "Odesa");
    }

    @Test
    void paginate_whenSearchByOwnProperty_thenReturnsExpectedCities() {
        QueryWrapper selectUACitiesOver1M = QueryWrapper.create()
                .where(City::getCountryCode).eq("UKR")
                .and(City::getPopulation).ge(1_000_000)
                .orderBy(column(City::getPopulation).desc());

        Page<City> secondPageRequest = new Page<>(2, 3);
        Page<City> secondPage = cityFlexMapper.paginate(secondPageRequest, selectUACitiesOver1M);

        assertThat(secondPage.getPageNumber()).isEqualTo(2);
        assertThat(secondPage.getPageSize()).isEqualTo(3);
        assertThat(secondPage.getRecords()).hasSize(2);
        assertThat(secondPage.getTotalPage()).isEqualTo(2);
        assertThat(secondPage.getTotalRow()).isEqualTo(5);

        var paginatedResult = secondPage.getRecords();
        assertThat(paginatedResult).hasSize(2);
        assertThat(paginatedResult).extracting(City::getName)
                .containsExactly("Donetsk", "Odesa");
    }

    @Test
    void selectListByQueryAs_whenSearchByNestedProperty_thenReturnsExpectedCitiesAsProjection() {
        Consumer<QueryWrapper> ukrConsumer = _ -> QueryWrapper.create().where(Country::getCode).eq("UKR");
        QueryWrapper query = QueryWrapper.create()
                .select(CITY.NAME, CITY.POPULATION, COUNTRY.NAME.as("countryName"))
                .from(new QueryTable("city"))
                .leftJoin(new QueryTable("country")).on(City::getCountryCode, Country::getCode)
                .where(ukrConsumer)
                .and(City::getPopulation).ge(1_000_000);

        List<CityCountryView> result = cityFlexMapper.selectListByQueryAs(query, CityCountryView.class);

        assertThat(result).hasSize(5);
        assertThat(result).extracting(CityCountryView::getName)
                .containsExactly("Kyiv", /*Kharkiv*/ "Harkova [Harkiv]", /*Dnipro*/"Dnipropetrovsk", "Donetsk", "Odesa");
        assertThat(result).extracting(CityCountryView::getCountryName).containsOnly("Ukraine");
    }

}