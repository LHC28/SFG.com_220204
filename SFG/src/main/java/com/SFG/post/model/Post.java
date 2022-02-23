package com.SFG.post.model;

import java.util.List;

import com.SFG.board.model.Board;
import com.SFG.board.model.File;
import com.SFG.recommend.model.Recommend;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

//	게시글
	private Board board;
//	이미지 모음
	private List<File> fileList;
//	추천 모음
	private int recommend;
}
