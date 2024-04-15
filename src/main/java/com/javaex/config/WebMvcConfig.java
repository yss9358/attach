package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**") // 경로
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins("http://localhost:8081") //vue의 ip확인
				.allowedHeaders("*") // 모든 요청해더
				.exposedHeaders("Authorization")// 노출시킬 헤더
				.allowCredentials(true); // 쿠키허용
	}

	
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//////fdfdsafasf////nnnff
		String saveDir;
		
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("linux")) {
        	System.out.println("리눅스");
        	// 파일저장디렉토리
        	saveDir = "/app/upload/"; // Linux 경로. username을 실제 사용자 이름으로 변경하세요.
        
		} else {
        	System.out.println("윈도우");
        	// 파일저장디렉토리
        	saveDir = "C:\\javaStudy\\upload\\";
        }
		
		registry.addResourceHandler("/upload/**")
		.addResourceLocations("file:"+saveDir);
		
	}

}