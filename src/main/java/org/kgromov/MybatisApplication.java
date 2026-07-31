package org.kgromov;

import org.kgromov.mappers.CityMapper;
import org.kgromov.mappers.CountryLanguageMapper;
import org.kgromov.mappers.ICountryLanguageMapper;
import org.kgromov.model.City;
import org.kgromov.model.LanguageCode;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@MapperScan("org.kgromov.mappers")
@SpringBootApplication
public class MybatisApplication {
    private static final Logger log = LoggerFactory.getLogger(MybatisApplication.class);

    static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(CountryLanguageMapper countryLanguageMapper, CityMapper cityMapper) {
        return _ -> {
            var allCountryLanguageCodes = countryLanguageMapper.findAll();
            log.info("All country language codes: {}", allCountryLanguageCodes);
            var countryLanguageCode = countryLanguageMapper.findById("UKR", "Ukrainian");
//            var countryLanguageCode = countryLanguageMapper.findById(new LanguageCode("UKR", "Ukrainian"));
            log.info("Country language code: {}", countryLanguageCode);

            var cities = cityMapper.findAll();
            log.info("All cities: {}", cities);
            var city = cityMapper.findById(1L);
            log.info("City: {}", city);
            int a = 1;
        };
    }

}
