package com.SFG.post.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFG.board.bo.BoardBO;
import com.SFG.board.model.Board;
import com.SFG.board.model.File;
import com.SFG.post.model.Post;
import com.SFG.recommend.bo.RecommendBO;
import com.SFG.recommend.model.Recommend;

@Service
public class PostBO {

	@Autowired
	private BoardBO boardBO;
	
	@Autowired
	private RecommendBO recommendBO;
	
	private static final int POST_MAX_SIZE = 5;
	
//	게시물 + 추천수 + 이미지 합쳐서 보내기 (리스트로 여러 개)
	public List<Post> getPostListByBoardKind(int boardKind, Integer prevId, Integer nextId){
		
		String direction = null;
		Integer standardId = null;
		if(prevId != null) {
			direction = "prev";
			standardId = prevId;
			
			List<Board> boardList = boardBO.getBoardByBoardKind(boardKind, direction, standardId, POST_MAX_SIZE);
			Collections.reverse(boardList);
			
			List<Post> postList = new ArrayList<>();
			
			for(int i=0; i<boardList.size(); i++) {
				
				Post post = new Post();
				
				List<File> fileList =  boardBO.getFileByBoardId(boardList.get(i).getId());
				List<Recommend> recommendList = recommendBO.getRecommendByBoardId(boardList.get(i).getId());
				
				post.setBoard(boardList.get(i));
				post.setFileList(fileList);
				post.setRecommend(recommendList.size());
				
				postList.add(post);
			}
			
			return postList;
			
		}else if(nextId != null) {
			direction = "next";
			standardId = nextId;
		}
		// 조건식 활용으로 인해 else if에 들어가면 에러가 발생하게 됨으로 밖으로 빠짐
		List<Board> boardList = boardBO.getBoardByBoardKind(boardKind, direction, standardId, POST_MAX_SIZE);
		
		List<Post> postList = new ArrayList<>();
		
		for(int i=0; i<boardList.size(); i++) {
			
			Post post = new Post();
			
			List<File> fileList =  boardBO.getFileByBoardId(boardList.get(i).getId());
			List<Recommend> recommendList = recommendBO.getRecommendByBoardId(boardList.get(i).getId());
			
			post.setBoard(boardList.get(i));
			post.setFileList(fileList);
			post.setRecommend(recommendList.size());
			
			postList.add(post);
		}
		
		return postList;
	}
	
	public boolean isLastPage(int boardKind, int nextId, String sort) {
		return nextId == boardBO.selectPostIdByBoardKindAndSort(boardKind, sort);
	}
	
	public boolean isFirstPage(int boardKind, int prevId, String sort){
		return prevId == boardBO.selectPostIdByBoardKindAndSort(boardKind, sort);
	}
	
//	게시물 + 추천수 + 이미지 합쳐서 보내기 (1개)
	public Post getPostByBoardId(int boardId) {
		Post post = new Post();
		
		// 게시물 가져오기
		Board board = boardBO.getBoardByBoardId(boardId);
		post.setBoard(board);
		// 이미지파일 가져오기
		List<File> fileList = boardBO.getFileByBoardId(boardId);
		post.setFileList(fileList);
		// 추천수 가져오기
		List<Recommend> recommendList = recommendBO.getRecommendByBoardId(boardId);
		if(recommendList==null) {
			post.setRecommend(0);
		}else {
			post.setRecommend(recommendList.size());
		}
		
		return post;
	}
}
