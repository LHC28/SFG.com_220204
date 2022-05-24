package com.SFG.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SFG.post.bo.PostBO;
import com.SFG.post.model.Post;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	@RequestMapping("/get_post_boardAndImage")
	public List<Post> getPostBoardAndImage(
			@RequestParam("limit") int limit
			,@RequestParam("boardKind") int boardKind
			){
		
		List<Post> postList = postBO.getPostListByBoardKind(boardKind, limit);
		
		return postList;
	}
	
//	게시물 삭제
	@PostMapping("/delete_post")
	public Map<String, String> deletePost(
			@RequestParam("boardId") int boardId
			,@RequestParam("userId") int userId
			){
		Map<String, String> result = new HashMap<>();
		
		// 게시판 관련 data 삭제 후 board data 삭제 진행
		postBO.deletePostByBoardId(boardId, userId);
		
		result.put("result", "success");
		return result;
	}
}
