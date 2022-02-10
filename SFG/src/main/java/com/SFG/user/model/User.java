package com.SFG.user.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private String loginId;
	private String password;
	private String name;
	private String email;
	private Date createdAt;
	private Date updatedAt;
	
}
