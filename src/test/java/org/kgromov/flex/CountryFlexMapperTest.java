package org.kgromov.flex;

import com.mybatisflex.core.table.TableInfoFactory;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.kgromov.mappers.flex.CountryFlexMapper;
import org.kgromov.model.Country;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class CountryFlexMapperTest extends MyBatisFlexMapperTest {
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
}