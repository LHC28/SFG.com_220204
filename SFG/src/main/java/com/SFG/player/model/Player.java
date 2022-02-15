package com.SFG.player.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

	private int id;
	private String name;
	private int number;
	private String position;
	private Date birth;
	private Date debut;
	private String introduce;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
}
