package com.SFG.recommend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SFG.recommend.model.Recommend;

@Repository
public interface RecommendDAO {

	public List<Recommend> selectRecommendByBoardId(int boardId);
}
