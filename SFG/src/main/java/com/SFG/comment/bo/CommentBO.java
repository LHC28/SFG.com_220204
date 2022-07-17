package com.SFG.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFG.comment.dao.CommentDAO;
import com.SFG.comment.model.Comment;
import com.SFG.user.bo.UserBO;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	
	public void createComment(int userId, int boardId, String content) {
		commentDAO.insertComment(userId, boardId, content);
	}
	
	public List<Comment> getCommentList(int boardId) {
		List<Comment> commentList = commentDAO.selectCommentList(boardId);
		for(int i=0; i<commentList.size(); i++) {
			String userName = userBO.getUserName(commentList.get(i).getUserId());
			commentList.get(i).setUserName(userName);
		}
		return commentList;
	}
}
