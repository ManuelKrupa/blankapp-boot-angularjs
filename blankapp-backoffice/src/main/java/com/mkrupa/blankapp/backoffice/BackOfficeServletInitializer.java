package com.mkrupa.blankapp.backoffice;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Servlet initializer for SpringBoot to be able to deploy a WAR without web.xml file 
 * @author mkrupa
 *
 */
public class BackOfficeServletInitializer extends SpringBootServletInitializer  {
	
	/**
	 * Overriding configure method to launch the well SpringBoot Application
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackOfficeApplication.class);
	}
}
