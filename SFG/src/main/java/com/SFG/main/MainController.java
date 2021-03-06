package com.SFG.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SFG.board.bo.BoardBO;
import com.SFG.crawling.CrawlingTeamRank;
import com.SFG.crawling.model.TeamRank;
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
	public String mainPage(
			Model model
			,HttpServletRequest request
			) {
		
		// 로그인이 된 경우
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("loginId");
		
		// 구단 다음 경기 가져오기
		MatchSchedule match = new MatchSchedule();
		
		// 오늘 날짜 가져오기 (월, 일 별개로)
		LocalDate curDate = LocalDate.now();
		DateTimeFormatter MonthFormatter = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
		int month = Integer.valueOf(curDate.format(MonthFormatter));
		int day = Integer.valueOf(curDate.format(dayFormatter));
		System.out.println(day);
		System.out.println(month);
		// 경기 일정 가져오기 (int 이기 때문에 null이 아닌 0이 비어있는 값으로)
		List<MatchSchedule> matchSchedule = matchBO.getMatchSchedule(month);
		for(int i=0; i<=matchSchedule.size(); i++) {
			if(matchSchedule.get(i)!=null && matchSchedule.get(i).getDay()==day) {
				match = matchSchedule.get(i);
				break;
			}
		}
		
		if(match.getId()==0) {
			while(true) {

				// 가까운 일자의 경기 일정 가져오기
				matchSchedule = matchBO.getMatchSchedule(month);
				for(int i=1; i<=matchSchedule.size(); i++) {
					if(matchSchedule.get(i)!=null && matchSchedule.get(i).getDay()>day) {
						match = matchSchedule.get(i);
						break;
					}
				}
				// 다음 경기가 있으면 중단.
				if(match.getId()!=0) {
					break;
				}
				// 오늘 기준 다음 월로...
				month++;

				// 12월 이후로 넘어가면 반복 중지(경기 일정이 없는 경우도 고려)
				if(month>12) {
					break;
				}
			}
		}
		
		// 구단 순위 가져오기
		List<TeamRank> teamRanks = CrawlingTeamRank.getTeamRank();
		
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
		
		// 오늘 날짜를 활용해 경기일정 글자 변경하는데 활용하기 위함.
		model.addAttribute("loginId", loginId);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("teamRanks", teamRanks);
		model.addAttribute("newsList", postList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("fanList", fanList);
		// 경기 일정이 없는 경우도 고려해야 함.
		model.addAttribute("match", match);
		model.addAttribute("viewName", "include/mainPage");
		return "template/layout";
	}
}
