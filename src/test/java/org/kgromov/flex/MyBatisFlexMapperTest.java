package org.kgromov.flex;

import org.kgromov.config.MyBatisFlexSessionFactoryConfig;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "flex"})
@Import(MyBatisFlexSessionFactoryConfig.class)
@MybatisTest(excludeAutoConfiguration = MybatisAutoConfiguration.class)
abstract class MyBatisFlexMapperTest {

    /*@Test
    void whenInsertAndSelectById_thenAccountIsPersisted() {
        Account account = new Account();
        account.setUserName("olivia");
        account.setAge(28);
        account.setStatus("ACTIVE");
        account.setCreatedAt(LocalDateTime.of(2024, 5, 1, 12, 0));

        accountMapper.insert(account);
        Account persistedAccount = accountMapper.selectOneById(account.getId());

        assertNotNull(account.getId());
        assertNotNull(persistedAccount);
        assertEquals("olivia", persistedAccount.getUserName());
    }*/
}