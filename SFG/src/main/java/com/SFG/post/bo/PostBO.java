package com.SFG.post.bo;

import java.util.ArrayList;
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
	
//	게시물 + 추천수 + 이미지 합쳐서 보내기 (리스트로 여러 개)
	public List<Post> getPostListByBoardKind(int boardKind){
		
		List<Post> postList = new ArrayList<>();
		
		// 게시물 리스트 가져오기
		List<Board> boardList = boardBO.getBoardByBoardKind(boardKind);
		
		
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
