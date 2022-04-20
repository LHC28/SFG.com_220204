package com.SFG;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgApplication {

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getDefault());
		System.out.println("현재시각 : "+new Date());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SfgApplication.class, args);
	}
	

}
