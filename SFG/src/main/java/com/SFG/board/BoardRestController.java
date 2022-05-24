package com.SFG.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.SFG.board.bo.BoardBO;

@RequestMapping("/board")
@RestController
public class BoardRestController {

	@Autowired
	private BoardBO boardBO;
	
	@RequestMapping("/upload_post")
	public Map<String, String> uploadPost(
			@RequestParam("boardKind") int boardKind,
			@RequestParam(value="images", required=false) List<MultipartFile> files,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			HttpServletRequest request
			){
		// 로그인 정보 가져오기
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("name");
		
		boardBO.postCreate(userId, userName, boardKind, title, content, files);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		
		return result;
	}
	
	
//	조회수 증가
	@RequestMapping("/add_views")
	public Map<String, String> addViews(
			@RequestParam("boardId") int boardId
			){
		
		boardBO.addViews(boardId);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
}
