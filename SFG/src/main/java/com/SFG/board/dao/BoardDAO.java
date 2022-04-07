package com.SFG.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.SFG.board.model.Board;
import com.SFG.board.model.File;

@Repository
public interface BoardDAO {

	public void insertPost(
			@Param("userId") int userId
			,@Param("userName") String userName
			,@Param("boardKind") int boardKind
			,@Param("title") String title
			,@Param("content") String content);
	
	public List<Board> selectPostListByUserId(int userId);
	
	public void insertFile(
			@Param("boardId") int boardId
			,@Param("imagePath") String imagePath);
	
	public List<Board> selectBoardByBoardKindForPaging(
			@Param("boardKind") int boardKind
			,@Param("direction") String direction
			,@Param("standardId") Integer standardId
			,@Param("limit") int limit);
	
	public List<Board> selectBoardByBoardKindforMain(int boardKind);
	
	public List<File> selectFileByBoardId(int boardId);
	
	public Board selectBoardByBoardId(int boardId);
	
	public void updateViews(int boardId);
	
	public int selectPostIdByBoardKindAndSort(
			@Param("boardKind") int boardKind
			,@Param("sort") String sort
			);
}
