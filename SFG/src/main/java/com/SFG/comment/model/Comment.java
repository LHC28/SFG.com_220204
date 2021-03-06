package com.SFG.comment.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {

	private int id;
	private int postId;
	private int userId;
	private String userName;
	private String content;
	private Date createdAt;
	private Date updatedAt;
}
