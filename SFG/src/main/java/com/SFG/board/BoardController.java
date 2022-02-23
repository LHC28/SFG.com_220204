package com.SFG.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SFG.board.bo.BoardBO;
import com.SFG.post.bo.PostBO;
import com.SFG.post.model.Post;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardBO boardBO;
	
	// 게시물 종합용
	@Autowired
	private PostBO postBO;
	
	@RequestMapping("/notice_view")
	public String noticeView(
			Model model
			,HttpServletRequest request
			,@RequestParam("boardKind") int boardKind
			) {
		HttpSession session = request.getSession();
		// null인지 아닌지 여부에 따른 로그인 유무 확인
		String loginId = (String)session.getAttribute("loginId");
		
		// 게시글, 추천수, 이미지 파일 가져오기
		List<Post> postList = postBO.getPostListByBoardKind(boardKind);
		
//		boardKind 넘기기
		model.addAttribute("boardKind", boardKind);
//		로그인 유무 확인을 위한 값 넘기기
		model.addAttribute("loginId", loginId);
//		게시글 리스트 넘기기
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "board/noticeView");
		return "template/layout";
	}
	
//	공지사항 게시판
//	@RequestMapping("/news_view")
//	
//	팬 게시판
//	@RequestMapping("/fan_view")
//	
//	사진 게시판
//	@RequestMapping("/picture_view")
//	
//	건의사항 게시판
//	@RequestMapping("/suggest_view")
	
//	게시글 쓰기
	@RequestMapping("/create_view")
	public String boardCreate(
			HttpServletRequest request,
			Model model
			,@RequestParam("boardKind") int boardKind
			) {
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		
		// 게시판 번호를 활용하여 DB에 저장하기 위한 값 넘기기
		model.addAttribute("boardKind", boardKind);
		
		model.addAttribute("viewName", "board/boardCreate");
		model.addAttribute("name", name);
		return "template/layout";
	}
}
