package com.mkrupa.blankapp.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application class for Blankapp backoffice application
 * @author mkrupa
 *
 */
@EnableJpaRepositories(repositoryFactoryBeanClass = com.mkrupa.blankapp.backoffice.jpa.support.BackofficeRepositoryFactoryBean.class, basePackages = "com.mkrupa.blankapp.backoffice.repository")
@ComponentScan(basePackages = { "com.mkrupa.blankapp.backoffice.service", "com.mkrupa.blankapp.backoffice.controller", 
		"com.mkrupa.blankapp.backoffice.domain", "com.mkrupa.blankapp.backoffice.filter", "com.mkrupa.blankapp.backoffice.exception",
		"com.mkrupa.blankapp.backoffice.security"})
@EnableAutoConfiguration
public class BackOfficeApplication {
	
	/**
	 * Launching method af the application
	 * @param args String [] :
	 * 		the application parameters
	 */
	public static void main(String[] args) {
        SpringApplication.run(BackOfficeApplication.class, args);
    }
}
