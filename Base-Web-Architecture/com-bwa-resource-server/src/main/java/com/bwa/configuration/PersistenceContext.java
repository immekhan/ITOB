package com.bwa.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * This configuration class configures the persistence layer
 * enables annotation driven transaction management.
 *
 * This configuration is put to a single class because this way we can write integration
 * tests for our persistence layer by using the configuration used by our example
 * application. In other words, we can ensure that the persistence layer of our application
 * works as expected.
 */
@Configuration
//@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@ComponentScan(basePackages = {"com.bwa"})
@EnableJpaRepositories(basePackages = {
        "com.bwa.persistence.repository"
})
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableAspectJAutoProxy
class PersistenceContext {

    @Bean(destroyMethod = "close")
    DataSource dataSource() {
        BasicDataSource dataSource =new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("itob");
        dataSource.setPassword("system");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabase(Database.ORACLE);

        lef.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        lef.setPackagesToScan("com.bwa.persistence.model");
        lef.afterPropertiesSet();
        return lef;
    }

    @Bean
    JpaTransactionManager transactionManager(DataSource dataSource) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
        return txManager;
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}
