package com.SFG;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SfgApplication extends SpringBootServletInitializer{

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getDefault());
		System.out.println("현재시각 : "+new Date());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SfgApplication.class, args);
	}
	

}
