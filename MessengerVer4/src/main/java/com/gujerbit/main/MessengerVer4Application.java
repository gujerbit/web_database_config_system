package com.gujerbit.main;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gujerbit.main.interceptor.SessionInterceptor;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MessengerVer4Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MessengerVer4Application.class, args);
	}
	
	@Autowired
	private SessionInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//적용 경로
		registry.addInterceptor(interceptor).addPathPatterns("/view/**").excludePathPatterns(Arrays.asList("/user/**"));
		//적용 제외 경로
		//.excludePathPatterns(Arrays.asList(""))
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*").exposedHeaders("jwt-auth-token");;
	}

}
