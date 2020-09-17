package com.chahar.indent.config;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	@Value("${db.driver}")
	private String DRIVER;

	@Value("${db.password}")
	private String PASSWORD;

	@Value("${db.url}")
	private String URL;

	@Value("${db.username}")
	private String USERNAME;

	@Value("${hibernate.dialect}")
	private String DIALECT;

	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;

	@Value("${hibernate.current_session_context_class}")
	private String CURRENT_SESSION_CONTEXT_CLASS;

	@Value("${hibernate.enable_lazy_load_no_trans}")
	private String ENABLE_LAZY_LOAD_NO_TRANS;

	@Bean(name = "dataSource")
	@Profile("dev")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

	@Bean(name = "dataSource")
	@Profile("prod")
	public DataSource jndiDataSource() {
		DataSource dataSource = null;
		try {

			Context initialContex = new InitialContext();
			System.out.println("value of datasource" + dataSource);

			dataSource = (DataSource) (initialContex.lookup("java:/ndmc_db"));

			System.out.println("value of datasource" + dataSource);

			if (dataSource != null) {
				dataSource.getConnection();

			} else {
				dataSource = (DataSource) (initialContex.lookup("java:/ndmc_db"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		if (PACKAGES_TO_SCAN != null && PACKAGES_TO_SCAN.contains(",")) {
			sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN.split(","));
		} else {
			sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		}

		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", DIALECT);
		hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		hibernateProperties.put("hibernate.enable_lazy_load_no_trans", ENABLE_LAZY_LOAD_NO_TRANS);
		hibernateProperties.put("hibernate.current_session_context_class", CURRENT_SESSION_CONTEXT_CLASS);
		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}