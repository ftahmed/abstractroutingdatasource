package me.murrer.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


/**
 * Configuration for the Distributed Datasource Demo.
 */
@Configuration
public class DatasourceConfiguration {

	@Autowired
	Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(createCustomRoutingDataSource());
		em.setPackagesToScan("me.murrer.models");
		em.setPersistenceUnitName("default");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	public CustomRoutingDataSource createCustomRoutingDataSource() {

		CustomRoutingDataSource datasource = new CustomRoutingDataSource();

		Map<Object, Object> datasourceMap = new HashMap<>();
		datasourceMap.put("CUSTOMER-A", createDatasourceCustomerA());
		datasourceMap.put("CUSTOMER-B", createDatasourceCustomerB());

		datasource.setTargetDataSources(datasourceMap);
		datasource.setDefaultTargetDataSource(createDatasourceDefault());

		datasource.afterPropertiesSet();

		return datasource;
	}

	// Those beans should be created by a factory/service
	public DataSource createDatasourceDefault() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(org.h2.Driver.class.getName());
		dataSource.setUrl(env.getProperty("customer.unknown.url"));
		dataSource.setUsername(env.getProperty("customer.unknown.username"));
		dataSource.setPassword(env.getProperty("customer.unknown.password"));
		return dataSource;
	}

	public DataSource createDatasourceCustomerA() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(org.h2.Driver.class.getName());
		dataSource.setUrl(env.getProperty("customer.a.url"));
		dataSource.setUsername(env.getProperty("customer.a.username"));
		dataSource.setPassword(env.getProperty("customer.a.password"));
		return dataSource;
	}

	public DataSource createDatasourceCustomerB() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(org.h2.Driver.class.getName());
		dataSource.setUrl(env.getProperty("customer.b.url"));
		dataSource.setUsername(env.getProperty("customer.b.username"));
		dataSource.setPassword(env.getProperty("customer.b.password"));
		return dataSource;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
		return properties;
	}
}
