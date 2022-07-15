package com.SFG.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFG.comment.dao.CommentDAO;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	public void createComment(int userId, int postId, String content) {
		commentDAO.insertComment(userId, postId, content);
	}
}
