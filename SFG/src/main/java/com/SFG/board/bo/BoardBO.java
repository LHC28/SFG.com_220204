package com.SFG.board.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SFG.board.dao.BoardDAO;
import com.SFG.board.model.Board;
import com.SFG.board.model.File;
import com.SFG.common.FileManagerService;
import com.SFG.recommend.dao.RecommendDAO;

@Service
public class BoardBO {

	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private RecommendDAO recommendDAO;
	
	// 게시글 등록
	public void postCreate(int userId, String userName, int boardKind, String title, String content, List<MultipartFile> files) {
		
		// 이미지 1개
		String imagePath = null;
		// 이미지 2개 이상
		List<String> imagePaths = null;
		
		if(files != null) {
			// 게시글 파일이 1개일 때
			if(files.size()==1) {
				try {
					imagePath = fileManagerService.saveFile(userName, files.get(0));
//					board DB에 넣기
					boardDAO.insertPost(userId, userName, boardKind, title, content);
					List<Board> posts = boardDAO.selectPostListByUserId(userId);
					
//					file DB에 넣기
					boardDAO.insertFile(posts.get(0).getId(), imagePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
//			게시글 파일이 2개일 때
			}else if(files.size()>1) {
				try {
					imagePaths = fileManagerService.saveFiles(userName, files);
					
					boardDAO.insertPost(userId, userName, boardKind, title, content);
					List<Board> posts = boardDAO.selectPostListByUserId(userId);
					//file DB에 넣기
					for(int i=0; i<imagePaths.size(); i++) {
						boardDAO.insertFile(posts.get(0).getId(), imagePaths.get(i));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else if(files == null) {
			boardDAO.insertPost(userId, userName, boardKind, title, content);
		}
	}
	
//	게시물 가져오기 (페이징 용)
	public List<Board> getBoardByBoardKindForPaging(int boardKind, String direction, Integer standardId, int limit) {
		return boardDAO.selectBoardByBoardKindForPaging(boardKind, direction, standardId, limit);
	}
	
//	게시물 가져오기 (게시판 종류에 따라)
	public List<Board> getBoardByBoardKind(int boardKind, int limit){
		return boardDAO.selectBoardByBoardKind(boardKind, limit);
	}
	
//	게시물 이미지 가져오기
	public List<File> getFileByBoardId(int boardId){
		return boardDAO.selectFileByBoardId(boardId);
	}
	
//	게시물 있는지 확인
	public Board getBoardByBoardId(int boardId) {
		return boardDAO.selectBoardByBoardId(boardId);
	}
	
//	조회수 증가
	public void addViews(int boardId) {
		boardDAO.updateViews(boardId);
	}
	
	public int selectPostIdByBoardKindAndSort(int boardKind, String sort) {
		return boardDAO.selectPostIdByBoardKindAndSort(boardKind, sort);
	}
	
//	게시물 삭제
	public void deleteBoardByBoardId(int boardId) {
		boardDAO.deleteBoardByBoardId(boardId);
	}
}
