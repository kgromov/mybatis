package org.kgromov;

import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
//@Import(MyBatisEmbeddedConfiguration.class)
@MybatisTest
public abstract class EmbeddedMapperTest {
}
