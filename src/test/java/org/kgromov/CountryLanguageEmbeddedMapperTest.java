package org.kgromov;

import org.junit.jupiter.api.Test;
import org.kgromov.mappers.CountryLanguageMapper;
import org.kgromov.model.CountryLanguage;
import org.kgromov.model.LanguageCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class CountryLanguageEmbeddedMapperTest extends EmbeddedMapperTest {
    @Autowired
    private CountryLanguageMapper mapper;

    @Test
    void findAll_whenNoParams_thenReturnsAll() {
        var countryLanguages = mapper.findAll();

        assertThat(countryLanguages).isNotEmpty();
        assertThat(countryLanguages).extracting(CountryLanguage::getId).extracting(LanguageCode::getCode)
                .containsOnly("UKR");
        var mostUsedIn = countryLanguages.stream().sorted(Comparator.comparing(CountryLanguage::getUsage).reversed()).findFirst();
        assertThat(mostUsedIn).hasValueSatisfying(c -> {
            assertThat(c.getId().getLanguage()).isEqualTo("Ukrainian");
            assertThat(c.getUsage()).isGreaterThan(BigDecimal.valueOf(60));
            assertThat(c.getOfficial()).isTrue();
        });
    }
}
