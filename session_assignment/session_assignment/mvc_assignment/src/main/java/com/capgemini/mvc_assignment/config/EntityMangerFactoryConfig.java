package com.capgemini.mvc_assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


	@Configuration
	public class EntityMangerFactoryConfig {
		
		@Bean
		public LocalContainerEntityManagerFactoryBean getEntityManager() {
			LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
			factoryBean.setPersistenceUnitName("emsPersistenceUnit");
			return factoryBean;
		}
	}

