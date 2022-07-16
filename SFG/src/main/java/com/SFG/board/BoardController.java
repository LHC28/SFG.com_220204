package com.SFG.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SFG.board.bo.BoardBO;
import com.SFG.comment.bo.CommentBO;
import com.SFG.comment.model.Comment;
import com.SFG.post.bo.PostBO;
import com.SFG.post.model.Post;
import com.SFG.recommend.bo.RecommendBO;
import com.SFG.recommend.model.Recommend;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardBO boardBO;
	
	@Autowired
	private RecommendBO recommendBO;
	
	// 게시물 종합용
	@Autowired
	private PostBO postBO;
	
	// 댓글 가져오기용
	@Autowired
	private CommentBO commentBO;
	
	@RequestMapping("/notice_view")
	public String noticeView(
			Model model
			,HttpServletRequest request
			,@RequestParam(value="prevId", required=false) Integer prevIdParam
			,@RequestParam(value="nextId", required=false) Integer nextIdParam
			) {
		HttpSession session = request.getSession();
		// null인지 아닌지 여부에 따른 로그인 유무 확인
		String loginId = (String)session.getAttribute("loginId");

		Integer boardKind = 1;
		
		// 게시글, 추천수, 이미지 파일 가져오기
		List<Post> postList = postBO.getPostListByBoardKind(boardKind, prevIdParam, nextIdParam);
		int nextId = 0;
		int prevId = 0;
		if(postList.isEmpty()==false) {
			prevId = postList.get(0).getBoard().getId();
			nextId = postList.get(postList.size()-1).getBoard().getId();
			
			if(postBO.isLastPage(boardKind, nextId, "ASC")) {
				nextId = 0;
			}
			
			if(postBO.isFirstPage(boardKind, prevId, "DESC")) {
				prevId = 0;
			}
			
		}
		
//		boardKind 넘기기
		model.addAttribute("boardKind", boardKind);
//		로그인 유무 확인을 위한 값 넘기기
		model.addAttribute("loginId", loginId);
//		게시글 리스트 넘기기
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "board/boardListView");
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		
		return "template/layout";
	}
	
//	구단뉴스 게시판
	@RequestMapping("/news_view")
	public String newsView(
			Model model
			,HttpServletRequest request
			,@RequestParam(value="prevId", required=false) Integer prevIdParam
			,@RequestParam(value="nextId", required=false) Integer nextIdParam
			) {
		HttpSession session = request.getSession();
		// null인지 아닌지 여부에 따른 로그인 유무 확인
		String loginId = (String)session.getAttribute("loginId");

		Integer boardKind = 2;
		
		// 게시글, 추천수, 이미지 파일 가져오기
		List<Post> postList = postBO.getPostListByBoardKind(boardKind, prevIdParam, nextIdParam);
		int nextId = 0;
		int prevId = 0;
		if(postList.isEmpty()==false) {
			prevId = postList.get(0).getBoard().getId();
			nextId = postList.get(postList.size()-1).getBoard().getId();
			
			if(postBO.isLastPage(boardKind, nextId, "ASC")) {
				nextId = 0;
			}
			
			if(postBO.isFirstPage(boardKind, prevId, "DESC")) {
				prevId = 0;
			}
			
		}
		
//		boardKind 넘기기
		model.addAttribute("boardKind", boardKind);
//		로그인 유무 확인을 위한 값 넘기기
		model.addAttribute("loginId", loginId);
//		게시글 리스트 넘기기
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "board/boardListView");
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		
		return "template/layout";
	}
//	
//	팬 게시판
	@RequestMapping("/fan_view")
	public String fanView(
			Model model
			,HttpServletRequest request
			,@RequestParam(value="prevId", required=false) Integer prevIdParam
			,@RequestParam(value="nextId", required=false) Integer nextIdParam
			) {
		HttpSession session = request.getSession();
		// null인지 아닌지 여부에 따른 로그인 유무 확인
		String loginId = (String)session.getAttribute("loginId");

		Integer boardKind = 3;
		
		// 게시글, 추천수, 이미지 파일 가져오기
		List<Post> postList = postBO.getPostListByBoardKind(boardKind, prevIdParam, nextIdParam);
		int nextId = 0;
		int prevId = 0;
		if(postList.isEmpty()==false) {
			prevId = postList.get(0).getBoard().getId();
			nextId = postList.get(postList.size()-1).getBoard().getId();
			
			if(postBO.isLastPage(boardKind, nextId, "ASC")) {
				nextId = 0;
			}
			
			if(postBO.isFirstPage(boardKind, prevId, "DESC")) {
				prevId = 0;
			}
			
		}
		
//		boardKind 넘기기
		model.addAttribute("boardKind", boardKind);
//		로그인 유무 확인을 위한 값 넘기기
		model.addAttribute("loginId", loginId);
//		게시글 리스트 넘기기
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "board/boardListView");
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		
		return "template/layout";
	}
	
//	사진 게시판
	@RequestMapping("/picture_view")
	public String pictureView(
			Model model
			,HttpServletRequest request
			,@RequestParam(value="prevId", required=false) Integer prevIdParam
			,@RequestParam(value="nextId", required=false) Integer nextIdParam
			) {
		HttpSession session = request.getSession();
		// null인지 아닌지 여부에 따른 로그인 유무 확인
		String loginId = (String)session.getAttribute("loginId");

		Integer boardKind = 4;
		
		// 게시글, 추천수, 이미지 파일 가져오기
		List<Post> postList = postBO.getPostListByBoardKind(boardKind, prevIdParam, nextIdParam);
		int nextId = 0;
		int prevId = 0;
		if(postList.isEmpty()==false) {
			prevId = postList.get(0).getBoard().getId();
			nextId = postList.get(postList.size()-1).getBoard().getId();
			
			if(postBO.isLastPage(boardKind, nextId, "ASC")) {
				nextId = 0;
			}
			
			if(postBO.isFirstPage(boardKind, prevId, "DESC")) {
				prevId = 0;
			}
			
		}
		
//		boardKind 넘기기
		model.addAttribute("boardKind", boardKind);
//		로그인 유무 확인을 위한 값 넘기기
		model.addAttribute("loginId", loginId);
//		게시글 리스트 넘기기
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "board/boardListView");
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		
		return "template/layout";
	}
	
//	건의사항 게시판
	@RequestMapping("/suggest_view")
	public String suggestView(
			Model model
			,HttpServletRequest request
			,@RequestParam(value="prevId", required=false) Integer prevIdParam
			,@RequestParam(value="nextId", required=false) Integer nextIdParam
			) {
		HttpSession session = request.getSession();
		// null인지 아닌지 여부에 따른 로그인 유무 확인
		String loginId = (String)session.getAttribute("loginId");

		Integer boardKind = 5;
		
		// 게시글, 추천수, 이미지 파일 가져오기
		List<Post> postList = postBO.getPostListByBoardKind(boardKind, prevIdParam, nextIdParam);
		int nextId = 0;
		int prevId = 0;
		if(postList.isEmpty()==false) {
			prevId = postList.get(0).getBoard().getId();
			nextId = postList.get(postList.size()-1).getBoard().getId();
			
			if(postBO.isLastPage(boardKind, nextId, "ASC")) {
				nextId = 0;
			}
			
			if(postBO.isFirstPage(boardKind, prevId, "DESC")) {
				prevId = 0;
			}
			
		}
		
//		boardKind 넘기기
		model.addAttribute("boardKind", boardKind);
//		로그인 유무 확인을 위한 값 넘기기
		model.addAttribute("loginId", loginId);
//		게시글 리스트 넘기기
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "board/boardListView");
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		
		return "template/layout";
	}
	
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
	
//	게시물 보기
	@GetMapping("/board_view")
	public String boardView(
			@RequestParam("boardId") int boardId
			,Model model
			,HttpServletRequest request
			) {
		// 로그인 정보 가져오기
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 게시글 관련 가져오기
		Post post = postBO.getPostByBoardId(boardId);
		
		// 로그인 한 사람이 해당 게시물에 로그인을 눌렀는지 여부 확인
		Recommend recommend;
		if(userId != null) {
			recommend = recommendBO.getRecommendByUserId(userId, boardId);
			boolean recommendCheck;
			if(recommend==null) {
				recommendCheck = false;
			}else {
				recommendCheck = true;
			}
			
			model.addAttribute("recommendCheck", recommendCheck);
		}
		
		// 댓글 가져오기
		List<Comment> commentList = commentBO.getCommentList(boardId);
		
		model.addAttribute("userId", userId);
		model.addAttribute("post", post);
		model.addAttribute("commentList", commentList);
		model.addAttribute("viewName", "board/boardView");
		
		return "template/layout";
	}
}
