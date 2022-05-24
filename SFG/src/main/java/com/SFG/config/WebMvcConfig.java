package com.SFG.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.SFG.intercepter.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private PermissionInterceptor permissionInterceptor;
	
	// 인터셉터 적용
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionInterceptor)
		.addPathPatterns("/**") // 모두 해당
		.excludePathPatterns("/user/sign_out","/static/**","/error"); // 로그아웃, css,이미지파일, 에러시 제외
	}
	
	// 사진을 웹주소로 가져올 수 있게 해주는 기능
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// mapping되는 주소
		registry.addResourceHandler("/images/**")
		// 실제 저장 장소
		// file 다음 /가 3개가 들어가야 에러가 발생하지 않는다.
		.addResourceLocations("file:///C:\\Users\\zzang\\Desktop\\sfg\\sfg.com\\workspace\\SFG\\images/");
		
		// aws로 옮길 때 변경
		//.addResourceLocations("file:///home/ec2-user/upload_images/");
	}

}
