package org.kgromov.flex;

import com.mybatisflex.core.table.TableInfoFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.kgromov.mappers.flex.CityFlexMapper;
import org.kgromov.mappers.flex.CountryFlexMapper;
import org.kgromov.model.City;
import org.kgromov.model.Country;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryFlexMapperTest extends MyBatisFlexMapperTest {
    @Autowired
    private CityFlexMapper cityFlexMapper;
    @Autowired
    private CountryFlexMapper countryFlexMapper;


    @Test
    @Order(0)
    void selectOneById_whenCountryCodeExists_thenReturnCountry() {
            String codeMetadata = TableInfoFactory.ofEntityClass(Country.class).getColumnByProperty("code");

        Country ukraine = countryFlexMapper.selectOneById("UKR");

        assertThat(ukraine).isNotNull();
        assertThat(ukraine.getCode()).isEqualTo("UKR");
        assertThat(ukraine.getName()).isEqualTo("Ukraine");
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
                .build();

        cityFlexMapper.insert(newCity);

        assertThat(newCity.getId()).isEqualTo(58);
    }
}