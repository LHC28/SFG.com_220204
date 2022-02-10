package com.SFG.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

	// 사진을 웹주소로 가져올 수 있게 해주는 기능
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// mapping되는 주소
		registry.addResourceHandler("/images/**")
		// 실제 저장 장소
		.addResourceLocations("file:///C:\\Users\\zzang\\Desktop\\샌프란시스코 자이언츠\\sfg.com\\workspace\\SFG\\images/");
		// aws로 옮길 때 변경
		//.addResourceLocations("file:///home/ec2-user/upload_images/");
	}

}
