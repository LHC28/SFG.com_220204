package com.SFG.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class MainController {

	@RequestMapping("/entrance")
	public String entrance() {
		return "main/entrance";
	}
}
