package com.home.alone.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.home.alone.board.vo.AlarmBoardAttachFileDTO;
import com.home.alone.board.vo.AlarmBoardAttachVO;
import com.home.alone.board.vo.AlarmBoardVO;
import com.home.alone.member.service.LessorService;
import com.home.alone.service.AdminService;
import com.home.alone.service.AlarmBoardService;
import com.home.alone.service.HomeService;
import com.home.alone.vo.AdminVO;
import com.home.alone.vo.HomeReportVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;


@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private LessorService lessorService;
	
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private AlarmBoardService abService;

	
	// 관리자 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinGET() {
		
	}
	
	@RequestMapping("/main")
	public String home() {
		
		return "/admin/main";
	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(AdminVO admin) throws Exception{
		
		adminService.adminJoin(admin);
		
		return "redirect:/admin/main";
	}
	
	// 아이디 중복체크
	@RequestMapping(value = "/adminIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String adminIdChkPOST(String adminId) throws Exception {
		
		int result = adminService.idCheck(adminId);
		
		if (result != 0) {
			return "fail";
		} else {
			return "success";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String registerForm() {
		return "/admin/login";
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, AdminVO admin, RedirectAttributes rttr) throws Exception {
		
//		System.out.println("login 메서드 진입");
//		System.out.println("전달된 데이터 :" + member);
//
//		return null;
		
		HttpSession session = request.getSession();
		AdminVO avo = adminService.adminLogin(admin);
		
		if(avo == null) {
			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect:/admin/adminLogin";
		} else {
			session.setAttribute("admin", avo);
			
			return "redirect:/admin/main";
		}
	}
	

	
	// 로그아웃
	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public String logoutGET(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		return "redirect:/admin/main";
	}
	

	// 회원가입 승인여부
	@ResponseBody
	@RequestMapping(value="/successId")
	public int successId(@RequestParam(value ="lessorId") String lessorId) throws Exception {
		System.out.println(lessorId);
		lessorService.successId(lessorId);
		
		int result = lessorService.successId(lessorId);
		
		if (result != 0) {
			return result;
		}
		return result;
		
	}
	
	
	// 허위 매물 목록 리스트
	@GetMapping("/reportList")
	public String HomeReport(Model model) {
		System.out.println("/HomeList 요청");
		List<HomeReportVO> list = homeService.getReportHomeList();
		System.out.println(list);
		model.addAttribute("list", list);
		return "admin/reportList";
	}
	
	// 신고 매물 처리
	@ResponseBody
	@RequestMapping(value="/report")
	public int report(@RequestParam(value ="homeNum") int homeNum) throws Exception {
		System.out.println(homeNum);
		adminService.report(homeNum);
		
		int result = adminService.report(homeNum);
		
		if (result != 1) {
			return result;
		}
		return result;
		
	}
	
	
	// 공지 리스트 
	@GetMapping("/ablist")
	public String ablist(Model model) {
		List<AlarmBoardVO> ablist = abService.getAlarmBoardList();
		model.addAttribute("ablist", ablist);
		return "/admin/ablist";
	}
	
	// 공지 등록 페이지 
	@GetMapping("/abregister")
	public String abregister() {
		return "/admin/abregister";
	}
	
	// 공지사항 조회 불러오기 
	@GetMapping("/getAB")
	public String getAB(Long ano, Model model) {
		log.info("getAB");
		model.addAttribute("Aboard", abService.abget(ano));		
		return "/admin/getAB";
	}
		
	// 수정테이블 불러오기
	@GetMapping("/abmodify")
	public String abmodify(Long ano, Model model) {
		model.addAttribute("board", abService.abget(ano));
		return "/admin/abmodify";
	}
	
	// 등록 처리
	@PostMapping("insertAlarmBoard.do")
	public String abregister(AlarmBoardVO vo, RedirectAttributes rttr) {
		log.info("=====================================");
		log.info("register : " + vo);
			
		// 파일 업로드 log
		if(vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> log.info(attach));
		}
			
		log.info("=====================================");
		abService.abregister(vo);
		rttr.addFlashAttribute("result", vo.getAno());
		return "redirect: /admin/ablist";
		}
	
	// 삭제 
	@RequestMapping("deleteAlarm.do")
		public String remove(@RequestParam("ano") Long ano, RedirectAttributes rttr) {
			log.info("remove..." + ano);
			
			List<AlarmBoardAttachVO> attachList = abService.getabAttachList(ano);	
			
			if(abService.abremove(ano)) {
				deleteFiles(attachList);
				rttr.addFlashAttribute("result", "success");
			}
			return "redirect: /admin/ablist";
		}
	
	// 수정
	@PostMapping("updateAlarm.do")
	public String modify(AlarmBoardVO vo, RedirectAttributes rttr) {
		log.info("modify : " + vo);
		abService.abmodify(vo);
		return "redirect: /admin/ablist";
	}
	
	// 파일 업로드 등록
	@PostMapping(value = "/AlarmuploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AlarmBoardAttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		List<AlarmBoardAttachFileDTO> list = new ArrayList<>();
		// folder 만들기
		String uploadFolder = "C:\\boardUplad\\";
			
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
			
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
			
		for(MultipartFile multipartFile : uploadFile) {
				
			AlarmBoardAttachFileDTO attachDTO = new AlarmBoardAttachFileDTO();
			String uploadFileName = multipartFile.getOriginalFilename();
				
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
			log.info("only file name: " + uploadFileName);
			attachDTO.setFileName(uploadFileName);
				
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
				
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
					
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
					
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				list.add(attachDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 파일 업로드 파일 조회  
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("fileName : " + fileName);
		File file = new File("C:\\boardUplad\\" + fileName);
		log.info("file: " + file);
		ResponseEntity<byte[]> result = null;
			
		try {
			HttpHeaders header = new HttpHeaders();
				
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
		
	// 파일 업로드 삭제 
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("deleteFile: " + fileName);
		File file;
			
		try {
			file = new File("C:\\boardUplad\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
				
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				log.info("largeFileName : " + largeFileName);
				file = new File(largeFileName);
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
		
	// 파일 업로드 게시물 조회 
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AlarmBoardAttachVO>> getAttachList(Long ano) {
		log.info("getAttachList" + ano);
		return new ResponseEntity<>(abService.getabAttachList(ano), HttpStatus.OK);
	}
		
	// 컨트롤러 내에서 사용하는 메소드 
	// 파일 업로드 폴더 만들기 
	public String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
			
	// 이미지 파일 판단 
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
			
	// 파일 삭제 메소드 
	private void deleteFiles(List<AlarmBoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
			
		log.info("delete attach files...........");
		log.info(attachList);
			
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\boardUplad\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\boardUplad\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_" + attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch(Exception e) {
				log.error("delete file error" + e.getMessage());
			}
		});
	}
}

