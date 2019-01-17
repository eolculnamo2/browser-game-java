package com.game.configs;


import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.game")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig implements WebMvcConfigurer {
	
	//spring variable to hold properties
	@Autowired
	private Environment env;
	
	//logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	 public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/assets/dist/**").addResourceLocations("/WEB-INF");
	}
	
	// equivalent for <mvc:default-servlet-handler/> tag

	
	@Bean
	@Description("Thymeleaf Template Resolver")
	public ServletContextTemplateResolver templateResolver(ServletContext servletContext) {
	    ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
	    templateResolver.setPrefix("/WEB-INF/public/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	 
	    return templateResolver;
	}
	 
	@Bean
	@Description("Thymeleaf Template Engine")
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver(null));
	    //templateEngine.setTemplateEngineMessageSource(messageSource());
	    return templateEngine;
	}

	@Bean
	@Description("Thymeleaf View Resolver")
	public ThymeleafViewResolver viewResolver() {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setOrder(1);
	    return viewResolver;
	}
	
	private int getIntProperty(String propName, Environment env) {
		return Integer.parseInt(env.getProperty(propName));
	}
	
	@Bean
	public DataSource securityDataSource() {
		//create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		//set the jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		//log the connection props
		logger.info(">>> jdbc.url="+ env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user="+ env.getProperty("jdbc.user"));
		//set database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		securityDataSource.setInitialPoolSize(
				getIntProperty("connection.pool.initialPoolSize",env));
		securityDataSource.setMinPoolSize(
				getIntProperty("connection.pool.minPoolSize",env));
		securityDataSource.setMaxPoolSize(
				getIntProperty("connection.pool.maxPoolSize",env));
		securityDataSource.setMaxIdleTime(
				getIntProperty("connection.pool.maxIdleTime",env));
		
		
		//set connection pool props
		return securityDataSource;
	}
}
