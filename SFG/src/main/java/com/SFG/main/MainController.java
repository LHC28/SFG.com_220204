package com.SFG.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SFG.board.bo.BoardBO;
import com.SFG.match.BO.MatchBO;
import com.SFG.match.model.MatchSchedule;
import com.SFG.post.bo.PostBO;
import com.SFG.post.model.Post;

@RequestMapping("/main")
@Controller
public class MainController {

	@Autowired
	private MatchBO matchBO;
	
	@Autowired
	private BoardBO boardBO;
	
	@Autowired
	private PostBO postBO;
	
	@RequestMapping("/entrance")
	public String entrance() {
		return "main/entrance";
	}
	
	@RequestMapping("/main_page")
	public String mainPage(Model model) {
		
		// 구단 다음 경기 가져오기
		MatchSchedule match = new MatchSchedule();
		
		// 오늘 날짜 가져오기 (월, 일 별개로)
		LocalDate curDate = LocalDate.now();
		DateTimeFormatter MonthFormatter = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
		int month = Integer.valueOf(curDate.format(MonthFormatter));
		int day = Integer.valueOf(curDate.format(dayFormatter));
		// 경기 일정 가져오기
		List<MatchSchedule> matchSchedule = matchBO.getMatchSchedule(month);
		for(int i=day; i<=matchSchedule.size(); i++) {
			if(matchSchedule.get(i).getId()!=0) {
				match = matchSchedule.get(i);
				break;
			}
		}
		if(match.getId()==0) {
			while(true) {
				// 오늘 기준 다음 월로...
				month++;
				// 12월 이후로 넘어가면 반복 중지(경기 일정이 없는 경우도 고려)
				if(month>12) {
					break;
				}
				matchSchedule = matchBO.getMatchSchedule(month);
				for(int i=1; i<=matchSchedule.size(); i++) {
					if(matchSchedule.get(i).getId()!=0) {
						match = matchSchedule.get(i);
						break;
					}
				}
				// 다음 경기가 있으면 중단.
				if(match.getId()!=0) {
					break;
				}
			}
		}
		
		// 구단 뉴스 가져오기 (boardKind = 2, limit = 5)
		int boardKind = 2;
		int limit = 5;
		List<Post> postList = postBO.getPostListByBoardKind(boardKind, limit);
		
		// 공지사항 및 팬게시판 가져오기 (1, 3)
		boardKind = 1;
		limit = 3;
		List<Post> noticeList = postBO.getPostListByBoardKind(boardKind, limit);
		boardKind = 3;
		List<Post> fanList = postBO.getPostListByBoardKind(boardKind, limit);
		
		
		model.addAttribute("newsList", postList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("fanList", fanList);
		// 경기 일정이 없는 경우도 고려해야 함.
		model.addAttribute("match", match);
		model.addAttribute("viewName", "include/mainPage");
		return "template/layout";
	}
}
