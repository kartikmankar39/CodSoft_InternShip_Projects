package com.zomato.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.*")
@EnableTransactionManagement
public class SpringJpaConfiguration extends JpaRepositoryConfigExtension{

	//DB Details
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		dataSource.setUsername("u1");
		dataSource.setPassword("u1");
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		
		return dataSource;
	}
	@Bean("entityManagerFactory")
	LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		
		//1.Setting DataSource Object/Details
		factory.setDataSource(getDataSource());
		
		//2.provide package information of entity Class
         factory.setPackagesToScan("com.*");
         
       //3.setting Jpa Properties
         factory.setJpaProperties(hibernateProperties());
         
     	//4.Passing predefined Hibernate Object Em
         HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
         factory.setJpaVendorAdapter(adapter);
         
         return factory;
	}
	@Bean("transactionManager")
	public PlatformTransactionManager createTransactionManager() {
		JpaTransactionManager transaction = new JpaTransactionManager();
		transaction.setEntityManagerFactory(createEntityManagerFactoryBean().getObject());
		return transaction;
	}
	// these are all from hibernate FW , Predefined properties : Keys
		Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		return hibernateProperties;
		}
}
