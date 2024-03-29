package com.SFG.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	
	// 로그 찍기용
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 이미지 저장 경로
	//public final static String FILE_UPLOAD_PATH="C:\\Users\\zzang\\Desktop\\sfg\\sfg.com\\workspace\\SFG\\images/";
	// aws로 옮길 때...
	public final static String FILE_UPLOAD_PATH= "/home/ec2-user/upload_images/";

	// 이미지 저장 (1개)
	public String saveFile(String userLoginId, MultipartFile file) throws IOException {
		
		// 1. 파일 경로 만들기(폴더) - 안 겹치게. 이름이 겹치는 것도 있을 수 있기 때문
		// 폴더 이름 만들기
		String directoryName = userLoginId+"_"+System.currentTimeMillis()+"/";
		String filePath = FILE_UPLOAD_PATH + directoryName; // 파일 저장 폴더 위치
		
		// 폴더 생성하기
		File directory = new File(filePath);
		
		if(directory.mkdir()==false) {
			logger.error("######## 폴더 생성 에러 발생!!!");
			return null; // 디렉토리 생성 X
		}
		
		// 2. 생성된 폴더에 파일 만들기
		byte[] bytes = file.getBytes();
		Path path = Paths.get(filePath + file.getOriginalFilename());
		Files.write(path, bytes); // 파일 생성
		
		// 파일 URI를 리턴
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
	// 이미지 파일 여러 개 저장할 때
	public List<String> saveFiles(String userLoginId, List<MultipartFile> fileList) throws IOException {
		
		// 1. 파일 경로 만들기(폴더) - 안 겹치게. 이름이 겹치는 것도 있을 수 있기 때문
		// 폴더 이름 만들기
		String directoryName = userLoginId+"_"+System.currentTimeMillis()+"/";
		String filePath = FILE_UPLOAD_PATH + directoryName; // 파일 저장 폴더 위치
		
		// 폴더 생성하기
		File directory = new File(filePath);
		
		if(directory.mkdir()==false) {
			logger.error("######## 폴더 생성 에러 발생!!!");
			return null; // 디렉토리 생성 X
		}
		List<String> imagePaths = new ArrayList<>();
		// 2. 생성된 폴더에 파일 만들기
		for(int i=0; i<fileList.size(); i++) {
			byte[] bytes = fileList.get(i).getBytes();
			Path path = Paths.get(filePath + fileList.get(i).getOriginalFilename());
			Files.write(path, bytes); // 파일 생성
			imagePaths.add("/images/" + directoryName + fileList.get(i).getOriginalFilename());
		}
		
		// 파일 URI를 리턴
		return imagePaths;
	}
	
//	이미지 파일 삭제
	public void deleteFile(String imagePath) throws IOException {
//		C:\Users\zzang\Desktop\sfg\sfg.com\workspace\SFG\images/
//		/images\\usqq_1629444033939\.jpg
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		if(Files.exists(path)) {
			Files.delete(path); // 이미지 삭제
		}
		
		// 디렉토리 삭제
		path = path.getParent();
		if(Files.exists(path)) {
			Files.delete(path);
		}
	}
	
}
