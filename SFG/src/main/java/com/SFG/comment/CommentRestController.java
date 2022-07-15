package com.SFG.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SFG.comment.bo.CommentBO;

@RestController
@RequestMapping("/comment")
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;
	
	@RequestMapping("/create_comment")
	public Map<String, String> createComment(
			@RequestParam("userId") int userId
			,@RequestParam("postId") int postId
			,@RequestParam("content") String content
			){
		System.out.println(userId);
		System.out.println(postId);
		System.out.println(content);
		commentBO.createComment(userId, postId, content);
		
		Map<String, String> result = new HashMap<>();
		
		result.put("result", "success");
		return result;
	}
}
