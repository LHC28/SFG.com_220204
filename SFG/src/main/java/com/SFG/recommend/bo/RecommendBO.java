package com.SFG.recommend.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFG.recommend.dao.RecommendDAO;
import com.SFG.recommend.model.Recommend;

@Service
public class RecommendBO {

	@Autowired
	private RecommendDAO recommendDAO;
	
	public List<Recommend> getRecommendByBoardId(int boardId){
		return recommendDAO.selectRecommendByBoardId(boardId);
	}
	
}
