package org.kgromov.embedded;

import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
//@Import(MyBatisEmbeddedConfiguration.class)
@MybatisTest
public abstract class EmbeddedMapperTest {
}
