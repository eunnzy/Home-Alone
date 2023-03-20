//package com.guardian.myhome.task;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.guardian.myhome.mapper.BoardAttachMapper;
//import com.guardian.myhome.vo.BoardAttachVO;
//
//import lombok.Setter;
//import lombok.extern.log4j.Log4j;
//
//@Log4j
//@Component
//public class BoardFileCheckTask {
//	
//	@Setter(onMethod_ = { @Autowired })
//	private BoardAttachMapper attachMapper;
//	
//	// 어제 사용된 파일의 목록 얻어오는 메소드 
//	private String getFolderYesterDay() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -1);
//		String str = sdf.format(cal.getTime());
//		return str.replace("-", File.separator);
//	}
//	
//	// 매일 새벽 2시 잘못된 파일 삭제하는 클래스 
//	@Scheduled(cron="0 0 2 * * *")
//	public void checkFiles() throws Exception{
//		log.warn("File Check Task run.................");
//		log.warn(new Date());
//		// 데이터베이스의 어제 목록 가져오기 
//		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
//		// 파일 경로 fileListPaths 에 추가
//		List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("/Users/songs/upload", vo.getUploadPath(), vo.getUuid() + "_" + 
//														vo.getFileName())).collect(Collectors.toList());
//		// 이미지인 경우 썸네일 경로 fileListPaths 에 추가
//		fileList.stream().filter(vo -> vo.isFileType() == true).map(vo -> Paths.get("/Users/songs/upload", vo.getUploadPath(), "s_" + 
//																	vo.getUuid() + "_" + vo.getFileName())).forEach(p -> fileListPaths.add(p));
//		log.warn("====================================");
//		fileListPaths.forEach(p -> log.warn(p));
//		// 어제 날짜 폴더
//		File targetDir = Paths.get("/Users/songs/upload", getFolderYesterDay()).toFile();
//		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
//		log.warn("====================================");
//		// 데이터베이스에 없는 파일들을 삭제 
//		for(File file : removeFiles) {
//			log.warn(file.getAbsolutePath());
//			file.delete();
//		}
//	}
//}
