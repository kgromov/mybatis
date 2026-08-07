package org.kgromov;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.kgromov.mappers.CityMapper;
import org.kgromov.mappers.java.CountryJavaMapper;
import org.kgromov.mappers.java.CountryLanguageJavaMapper;
import org.kgromov.model.City;
import org.kgromov.model.Country;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import tools.jackson.databind.json.JsonMapper;

import java.util.stream.Collectors;


@EnableAspectJAutoProxy
@MapperScan("org.kgromov.mappers")
@SpringBootApplication
public class MybatisApplication {
    private static final Logger log = LoggerFactory.getLogger(MybatisApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Bean
    JsonMapper jsonMapper() {
        return JsonMapper.builder()
                .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
                .changeDefaultPropertyInclusion(incl -> incl.withContentInclusion(JsonInclude.Include.NON_NULL))
                .build();
    }

    @Profile("!test")
    @Bean
    ApplicationRunner applicationRunner(
            CountryLanguageJavaMapper countryLanguageMapper,
            CityMapper cityMapper,
            CountryJavaMapper countryMapper
    ) {
        return _ -> {
            var allCountryLanguageCodes = countryLanguageMapper.findAll();
            log.info("All country language codes: {}", allCountryLanguageCodes);
            var countryLanguageCode = countryLanguageMapper.findById("UKR", "Ukrainian");
//            var countryLanguageCode = countryLanguageMapper.findById(new LanguageCode("UKR", "Ukrainian"));
            log.info("Country language code: {}", countryLanguageCode);

            var notUniqueCities = cityMapper.findAllByNotUniqueByName();
            log.info("Not unique city names: {}", notUniqueCities);

            var cities = cityMapper.findAll();
            log.debug("All cities: {}", cities);
            cities.stream().collect(Collectors.groupingBy(City::getName))
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue().size() > 1)
                    .forEach(entry -> log.info("Not unique city name: {} belongs to countries: {}",
                            entry.getKey(),
                            entry.getValue().stream().map(City::getCountry).map(Country::getName).collect(Collectors.joining(", ")))
                    );
            var city = cityMapper.findById(1L);
            log.info("City: {}", city);
            var citiesByCountryCode = cityMapper.findAllByCountryCode("UKR");
            log.info("Cities by country code: {}", citiesByCountryCode);
            var cityByName = cityMapper.findByName("Odesa");
            log.info("City by name: {}", cityByName);

            var countries = countryMapper.findAll();
            log.debug("All countries: {}", countries);
            var country = countryMapper.findById("UKR");
            log.info("Country: {}", country);
        };
    }

}
