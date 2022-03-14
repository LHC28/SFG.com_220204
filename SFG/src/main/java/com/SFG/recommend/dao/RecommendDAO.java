package com.SFG.recommend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.SFG.recommend.model.Recommend;

@Repository
public interface RecommendDAO {

	public List<Recommend> selectRecommendByBoardId(int boardId);
	
	public Recommend selectRecommendByUserId(
			@Param("userId") int userId
			,@Param("boardId") int boardId);
	
	public void insertRecommend(
			@Param("userId") int userId
			,@Param("boardId") int boardId);
	
	public void deleteRecommend(
			@Param("userId") int userId
			,@Param("boardId") int boardId);
}
