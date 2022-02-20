package com.SFG.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardController {

	@RequestMapping("/notice_view")
	public String noticeView(Model model) {
		
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
	
}
