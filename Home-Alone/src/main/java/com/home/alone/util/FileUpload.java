package com.home.alone.util;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUpload {
	// private String uploadPath;
	
	// upload 폴더 생성
	public File createUploadFolder(String uploadPath) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);
		File uploadFolder = new File(uploadPath, datePath);
		
		if(!uploadFolder.exists())	// 위 경로가 존재하지 않으면
			uploadFolder.mkdirs();
		
		return uploadFolder;
	}
	
	// 업로드된 사진 삭제
	public int deleteUploadFile(String uploadPath, String fileName) {
		try {
			File file = new File(uploadPath +  URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			// 원본 파일 삭제
			String originImgName = file.getAbsolutePath().replace("t_", "");	// 썸네일 파일 "t_" 붙은 것을 ""로
			System.out.println(originImgName);
			
			file = new File(originImgName);
			file.delete();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;	
		}
		
		return 1;
	}
	
//	public showPreview(String uploadPath, String fileName) {
//		File file = new File(uploadPath, fileName);
//	}
	
	
	
	
}
