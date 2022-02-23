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
		
		// 게시글 파일이 1개일 때
		if(files.size()==1) {
			try {
				imagePath = fileManagerService.saveFile(userName, files.get(0));
//				board DB에 넣기
				boardDAO.insertPost(userId, userName, boardKind, title, content);
				List<Board> posts = boardDAO.selectPostListByUserId(userId);
				
//				file DB에 넣기
				boardDAO.insertFile(posts.get(0).getId(), imagePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
//		게시글 파일이 2개일 때
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
	}
	
//	게시물 가져오기
	public List<Board> getBoardByBoardKind(int boardKind) {
		return boardDAO.selectBoardByBoardKind(boardKind);
	}
	
//	게시물 이미지 가져오기
	public List<File> getFileByBoardId(int boardId){
		return boardDAO.selectFileByBoardId(boardId);
	}
	

}
