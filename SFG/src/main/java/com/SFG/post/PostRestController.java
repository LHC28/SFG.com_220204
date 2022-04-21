package com.SFG.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
