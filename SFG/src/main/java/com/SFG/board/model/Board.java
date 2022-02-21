package com.SFG.board.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

	private int id;
	private int userId;
	private String userName;
	private int boardKind;
	private String content;
	private Date createdAt;
	private Date updatedAt;
}
