package org.kgromov.testcontainers;

import org.kgromov.MybatisApplication;
import org.springframework.boot.SpringApplication;


public class TestSpringBootTestcontainersApplication {

    static void main(String[] args) {
        SpringApplication.from(MybatisApplication::main)
                .with(MysqlTestcontainersConfiguration.class)
                .withAdditionalProfiles("test")
                .run(args);
    }

}
