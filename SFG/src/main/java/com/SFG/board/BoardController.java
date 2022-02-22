package com.SFG.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SFG.board.bo.BoardBO;
import com.SFG.board.model.Board;
import com.SFG.board.model.File;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardBO boardBO;
	
	@RequestMapping("/notice_view")
	public String noticeView(
			Model model
			,HttpServletRequest request
			,@RequestParam("boardId") int boardId
			) {
		HttpSession session = request.getSession();
		// null인지 아닌지 여부에 따른 로그인 유무 확인
		String loginId = (String)session.getAttribute("loginId");
		
		// 공지사항 관련 게시물 가져오기 - 1번
		int boardKind = 1;
		Board board = boardBO.getBoardByBoardKind(boardKind);
//		File file = boardBO.getFileByBoardId(board.getId());
		
		// 게시물과 일치하는 이미지 가져오기
		
//		게시글 등록시 id값을 활용하여 게시글의 게시판 위치 구분용
		model.addAttribute("boardId", boardId);
//		로그인 유무 확인을 위한 값 넘기기
		model.addAttribute("loginId", loginId);
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
			,@RequestParam("boardId") int boardId
			) {
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		
		// 게시판 번호를 활용하여 DB에 저장하기 위한 값 넘기기
		model.addAttribute("boardId", boardId);
		
		model.addAttribute("viewName", "board/boardCreate");
		model.addAttribute("name", name);
		return "template/layout";
	}
}
