package org.kgromov.testcontainers;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;


@TestConfiguration(proxyBeanMethods = false)
class MysqlTestcontainersConfiguration {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.29"))
            .withInitScripts("schema.sql", "data.sql")
            .withReuse(true);


}
