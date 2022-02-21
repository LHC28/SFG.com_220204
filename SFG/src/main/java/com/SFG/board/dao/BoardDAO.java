package com.SFG.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.SFG.board.model.Board;

@Repository
public interface BoardDAO {

	public void insertPost(
			@Param("userId") int userId
			,@Param("userName") String userName
			,@Param("boardKind") int boardKind
			,@Param("content") String content);
	
	public List<Board> selectPostListByUserId(int userId);
	
	public void insertFile(
			@Param("boardId") int boardId
			,@Param("imagePath") String imagePath);
}
