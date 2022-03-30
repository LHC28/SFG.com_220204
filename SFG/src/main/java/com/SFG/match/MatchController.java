package com.SFG.match;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/match")
@Controller
public class MatchController {

	@RequestMapping("/match_result_view")
	public String matchResultView(Model model) {
//		일자를 맞춰 가져오도록 할 예정(LocalDate 활용)
		model.addAttribute("viewName", "match/resultView");
		return "template/layout";
	}
}
