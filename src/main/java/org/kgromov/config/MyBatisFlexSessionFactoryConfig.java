package org.kgromov.config;

import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.FlexSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("flex")
@Configuration
@MapperScan(
        basePackages = "org.kgromov.mappers.flex",
        sqlSessionFactoryRef = "flexSqlSessionFactory"
)
public class MyBatisFlexSessionFactoryConfig {

    @Bean
    public SqlSessionFactory flexSqlSessionFactory(DataSource dataSource) throws Exception {
        FlexSqlSessionFactoryBean factoryBean = new FlexSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfiguration(new FlexConfiguration());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate flexSqlSessionTemplate(@Qualifier("flexSqlSessionFactory") SqlSessionFactory flexSqlSessionFactory) {
        return new SqlSessionTemplate(flexSqlSessionFactory);
    }
}