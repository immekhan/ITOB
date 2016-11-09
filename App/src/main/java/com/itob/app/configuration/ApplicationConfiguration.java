package com.itob.app.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.itob.app.business.BusinessImpl;
import com.itob.app.converters.Converter;
import com.itob.app.daos.AppDataService;
import com.itob.app.managers.ManagerImpl;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		basicDataSource.setUsername("hr");
		basicDataSource.setPassword("oracle");
		basicDataSource.setInitialSize(2);
		basicDataSource.setMaxTotal(10);
		return basicDataSource;
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
