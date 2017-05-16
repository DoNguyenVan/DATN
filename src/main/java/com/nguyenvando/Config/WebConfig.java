package com.nguyenvando.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/assets/css/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/assets/fonts/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/assets/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/assets/img/");
		
		registry.addResourceHandler("/admin/css/**").addResourceLocations("/WEB-INF/assets/css/");
		registry.addResourceHandler("/admin/fonts/**").addResourceLocations("/WEB-INF/assets/fonts/");
		registry.addResourceHandler("/admin/js/**").addResourceLocations("/WEB-INF/assets/js/");
		registry.addResourceHandler("/admin/img/**").addResourceLocations("/WEB-INF/assets/img/");
		
		registry.addResourceHandler("/student/css/**").addResourceLocations("/WEB-INF/assets/css/");
		registry.addResourceHandler("/student/fonts/**").addResourceLocations("/WEB-INF/assets/fonts/");
		registry.addResourceHandler("/student/js/**").addResourceLocations("/WEB-INF/assets/js/");
		registry.addResourceHandler("/student/img/**").addResourceLocations("/WEB-INF/assets/img/");
		
		registry.addResourceHandler("/teacher/css/**").addResourceLocations("/WEB-INF/assets/css/");
		registry.addResourceHandler("/teacher/fonts/**").addResourceLocations("/WEB-INF/assets/fonts/");
		registry.addResourceHandler("/teacher/js/**").addResourceLocations("/WEB-INF/assets/js/");
		registry.addResourceHandler("/teacher/img/**").addResourceLocations("/WEB-INF/assets/img/");


//		registry.addResourceHandler("/css/**").addResourceLocations("/assets/css/");
//		registry.addResourceHandler("/admin/css/**").addResourceLocations("/assets/css/");
//		registry.addResourceHandler("/fonts/**").addResourceLocations("/assets/fonts/");
//		registry.addResourceHandler("/js/**").addResourceLocations("/assets/js/");
//		registry.addResourceHandler("/img/**").addResourceLocations("/assets/img/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}

}
