package com.SFG.player.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerIntroduce {

	private int id;
	private int playerId;
	private String title;
	private String content;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
}
