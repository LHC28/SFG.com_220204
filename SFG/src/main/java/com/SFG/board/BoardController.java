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
	
//	@RequestMapping("/news_view")
//	
//	@RequestMapping("/fan_view")
//	
//	@RequestMapping("/picture_view")
//	
//	@RequestMapping("/suggest_view")
}
