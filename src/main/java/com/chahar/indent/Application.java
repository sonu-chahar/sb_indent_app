package com.chahar.indent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;

import com.chahar.indent.config.WebMvcConfig;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class,
		DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@Import({ WebMvcConfig.class })
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		super.onStartup(servletContext);
//
//		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
//
//		PageFilter pageFilter = new PageFilter();
//		FilterRegistration.Dynamic sitemeshFilter = servletContext.addFilter("pageFilter", pageFilter);
//		sitemeshFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
//
//	}

//	@Bean
//	public FilterRegistrationBean<PageFilter> pageFilter() {
//		FilterRegistrationBean<PageFilter> filter = new FilterRegistrationBean<>();
////		SiteMeshFilter pageFilter = new SiteMeshFilter();
//		PageFilter pageFilter = new PageFilter();
//		filter.addUrlPatterns("/*");
//		filter.setFilter(pageFilter);
//		filter.setOrder(Integer.MAX_VALUE);
//		return filter;
//	}

	@Bean
	public FilterRegistrationBean<OpenSessionInViewFilter> lazyLoadingFilter() {
		FilterRegistrationBean<OpenSessionInViewFilter> filter = new FilterRegistrationBean<>();
		OpenSessionInViewFilter openSessionInViewFilter = new OpenSessionInViewFilter();
		filter.addUrlPatterns("/*");
		filter.addInitParameter("sessionFactoryBeanName", "sessionFactory");
		filter.setFilter(openSessionInViewFilter);
		return filter;
	}
}
