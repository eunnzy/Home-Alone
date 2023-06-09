package com.home.alone.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.home.alone.board.service.BoardService;
import com.home.alone.board.vo.AlarmBoardVO;
import com.home.alone.board.vo.BoardAttachFileDTO;
import com.home.alone.board.vo.BoardAttachVO;
import com.home.alone.board.vo.BoardLikesVO;
import com.home.alone.board.vo.BoardVO;
import com.home.alone.member.vo.ImchaVO;
import com.home.alone.service.AlarmBoardService;
import com.home.alone.util.Criteria;
import com.home.alone.util.PageDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping("/community")
public class BoardController {
	
	@Autowired
	private BoardService service;
	@Autowired
	private AlarmBoardService abservice;
	
	
	@GetMapping("/list")	// 커뮤니티 리스트
	public String list(Model model, HttpServletRequest request, Criteria cri) {
		HttpSession session = request.getSession();
		ImchaVO imcha = (ImchaVO) session.getAttribute("imcha");
		if(cri.getTypeArr() == null) {
			cri.setType("");
		}
		if(cri.getKeyword() == null) {
			cri.setKeyword("");
		}
		
		log.info("before Board" + cri);
		model.addAttribute("list", service.boardList(cri));
		
		int total = service.boardCount(cri);
		log.info("before total : " + total);
		PageDTO dto = new PageDTO(cri, total);
		model.addAttribute("pageMaker", dto);
		log.info("total : " + dto);
		
		// 공지사항 리스트 
		List<AlarmBoardVO> ablist = abservice.getAlarmBoardList();
		model.addAttribute("ablist", ablist);
		
		return "/community/list";
	}
	
	// 등록 페이지 불러오는 매핑
	@GetMapping("/register")
	public String register() {
		return "/community/register";
	}
	
	// 조회 불러오기 
	@GetMapping("/get")
	public String get(Long bno, Model model, @ModelAttribute("cri") Criteria cri, String imchaId, HttpServletRequest request, HttpServletResponse response) {
		// 조회 게시물 정보 넘기기 
		model.addAttribute("board", service.getDetail(bno));
		log.info("get");
		
		// 좋아요 처리 
		BoardLikesVO boardLikesVO = new BoardLikesVO();
		boardLikesVO.setBno(bno);
		boardLikesVO.setUserid(imchaId);
		log.info(boardLikesVO);
		model.addAttribute("like", service.likeCheck(bno, imchaId));
//		model.addAttribute("like", service.likeCheck(boardLikesVO));
		
		
		// 조회수 처리 
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("cookie" + bno + imchaId)) {
					viewCookie = cookies[i];
				}
			}
		}
		
		if (viewCookie != null) {
	        if (!viewCookie.getValue().contains("[" + bno + imchaId +"]")) {
	        	boolean result = service.viewsUp(bno);
				if(result) {
					log.info("조회수 증가");
	            }else {
	            	log.info("조회수 증가 에러");
	            }
				viewCookie.setValue(viewCookie.getValue() + "_[" + bno + imchaId + "]");
				viewCookie.setPath("/");
				viewCookie.setMaxAge(60 * 60 * 24);
	            response.addCookie(viewCookie);
	        }
	    } else {
	    	service.viewsUp(bno);
	        Cookie newCookie = new Cookie("cookie" + bno + imchaId, "[" + bno + imchaId + "]");
	        newCookie.setPath("/");
	        newCookie.setMaxAge(60 * 60 * 24);
	        response.addCookie(newCookie);
	        log.info("newCookie" + newCookie);
	    }
		return "/community/get";
	}
	
	// 공지사항 조회 불러오기 
	@GetMapping("/getAlarm")
	public String getAlarm(Long ano, Model model) {
		// 조회 게시물 정보 넘기기 
		log.info("getAlarm");
		model.addAttribute("Aboard", abservice.abget(ano));		
		return "/community/getAlarm";
	}
	
	// 수정테이블 불러오기
	@GetMapping("/modify")
	public String modify(Long bno, Model model, @ModelAttribute("cri") Criteria cri) {
		model.addAttribute("board", service.getDetail(bno));
		return "/community/modify";
	}
	
	// 등록 처리
	@PostMapping("insertBoard.do")
	public String register(BoardVO vo, HttpServletRequest request, RedirectAttributes rttr) {
//		System.out.println(vo);
		log.info("=====================================");
		HttpSession session = request.getSession();
		ImchaVO imcha = (ImchaVO) session.getAttribute("imcha");
		
		// 파일 업로드 log
		if(vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> log.info(attach));
		}
		vo.setImchaId(imcha.getImchaId());
		log.info("register : " + vo);
		log.info("=====================================");
		
		service.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		return "redirect: /community/list";
	}
	
	// 수정
	@PostMapping("updateBoard.do")
	public String modify(BoardVO vo, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		log.info("modify : " + vo);
		service.modify(vo);
		return "redirect: /community/list" + cri.getListLink();
	}
		
	// 삭제 
	@RequestMapping("delete.do")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		log.info("remove..." + bno);
		// service.remove(bno);
		
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		if(service.remove(bno)) {
			deleteFiles(attachList);
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect: /community/list" + cri.getListLink();
	}
	
	// 내가 쓴 글 
	@GetMapping("/mylist")
	public String mylist(Model model, HttpServletRequest request, Criteria cri) {
		HttpSession session = request.getSession();
		ImchaVO imcha = (ImchaVO) session.getAttribute("imcha");

		cri.setImchaId(imcha.getImchaId());
		log.info("mylist: " + cri);
		model.addAttribute("mylist", service.getMyboardList(cri));
		
		int total = service.getMyboardCount(cri.getImchaId());
		log.info("total : " + total);
		PageDTO dto = new PageDTO(cri, total);
		model.addAttribute("mypageMaker", dto);
		log.info("total : " + dto);
		
		
		return "/community/mylist";
	}
	
	// 파일 업로드 등록
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		
		List<BoardAttachFileDTO> list = new ArrayList<>();
		// folder 만들기
		String uploadFolder = "C:\\boardUpload\\";
		
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		
		for(MultipartFile multipartFile : uploadFile) {
			
			BoardAttachFileDTO attachDTO = new BoardAttachFileDTO();
			String uploadFileName = multipartFile.getOriginalFilename();
			
			// IE has file path
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
	
	// 파일 다운로드 
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
		log.info("click : " + fileName);
		
         Resource resource = new FileSystemResource("C:\\boardUpload\\" + fileName);
         if (resource.exists() == false) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         String resourceName = resource.getFilename();
         
         // uuid 제거
         String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
         log.info(resourceOriginalName);
         HttpHeaders headers = new HttpHeaders();

        try {
            String downloadName = null;
            if (userAgent.contains("Trident")) {
                log.info("IE browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("/+", " ");
            } else if (userAgent.contains("Edge")) {
                log.info("Edge browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
            } else {
            	log.info("chrome browser");
                downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
            }
            headers.add("Content-Disposition", "attachment; filename=" + downloadName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

         return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
	
	
	// 파일 업로드 파일 조회  
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("fileName : " + fileName);
		File file = new File( "C:\\boardUpload\\" + fileName);
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
			file = new File( "C:\\boardUpload\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
			
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("t_", "");
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
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno) {
		log.info("getAttachList" + bno);
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
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
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		log.info("delete attach files...........");
		log.info(attachList);
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\boardUpload\\" + attach.getUploadPath() + "/" + attach.getUuid() + "_" + attach.getFileName());
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\boardUpload\\" + attach.getUploadPath() + "/t_" + attach.getUuid() + "_" + attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch(Exception e) {
				log.error("delete file error" + e.getMessage());
			}
		});
	}
}

