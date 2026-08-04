package org.kgromov.testcontainers;

import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@MybatisTest
@Testcontainers(disabledWithoutDocker = true)
@ImportTestcontainers(MysqlTestcontainersConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NON_TEST)
public abstract class MysqlTestContainersTest {

//    @Container
//    @ServiceConnection
//    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.29"))
//            .withInitScripts("schema.sql", "data.sql");
}
