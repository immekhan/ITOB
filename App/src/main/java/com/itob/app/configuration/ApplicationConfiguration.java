package com.itob.app.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.itob.app.business.BusinessImpl;
import com.itob.app.converters.Converter;
import com.itob.app.daos.AppDataService;
import com.itob.app.managers.ManagerImpl;

@Configuration
@EnableJpaRepositories(basePackages = {"com.itob.app.repositories"})
@EnableTransactionManagement
public class ApplicationConfiguration {
	
	private DataSource dataSource;

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.itob.app.info");
	
		Properties jpaProperties = new Properties();
	
		//Configures the used database dialect. This allows Hibernate to create SQL
		//that is optimized for the used database.
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

		//Specifies the action that is invoked to the database when the Hibernate
		//SessionFactory is created or closed.
		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

		//Configures the naming strategy that is used when Hibernate creates
		//new database objects and schema elements
		jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

		//If the value of this property is true, Hibernate writes all SQL
		//statements to the console.
		jpaProperties.put("hibernate.show_sql", "false");

		//If the value of this property is true, Hibernate will format the SQL
		//that is written to the console.
		jpaProperties.put("hibernate.format_sql", "true");

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}
	
	
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		try {
			transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return transactionManager;
	}
	
	

	@Bean
	public DataSource dataSource() {
		if (dataSource == null) {
			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			basicDataSource.setUsername("hr");
			basicDataSource.setPassword("oracle");
			basicDataSource.setInitialSize(2);
			basicDataSource.setMaxTotal(10);
			dataSource = basicDataSource;
		} 
		return dataSource;
	}

	@Bean
	public AppDataService appDataService() {
		return new AppDataService();
	}

	@Bean
	public BusinessImpl businessImpl() {
		return new BusinessImpl();
	}

	@Bean
	public ManagerImpl managerImpl() {
		return new ManagerImpl();
	}
	
	@Bean
	public Converter convert() {
		return new Converter();
	}

}
