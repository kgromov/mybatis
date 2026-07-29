package org.kgromov;

import org.kgromov.mappers.CountryLanguageMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@MapperScan("org.kgromov.mappers")
@SpringBootApplication
public class MybatisApplication {

    static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(CountryLanguageMapper countryLanguageMapper) {
        return _ -> {
            var allCountryLanguageCodes = countryLanguageMapper.findAll();
            var countryLanguageCode = countryLanguageMapper.findById("UKR", "Ukrainian");
            int a = 1;
        };
    }

}
