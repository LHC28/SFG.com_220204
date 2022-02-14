package com.SFG.match;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/match")
@Controller
public class MatchController {

	@RequestMapping("match_result_view")
	public String matchResultView(Model model) {
		
		model.addAttribute("viewName", "match/resultView");
		return "template/layout";
	}
}
