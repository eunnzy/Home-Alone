package com.guardian.myhome.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guardian.myhome.service.LessorService;
import com.guardian.myhome.vo.LessorImgVO;
import com.guardian.myhome.vo.LessorVO;


@Controller
@SessionAttributes({"lessorId", "lessor"})
@RequestMapping(value = "/member")
public class LessorController {
	
	
	@Autowired
	private LessorService lessorservice;
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "/lessorJoin", method = RequestMethod.GET)
	public void lessorJoinGET() {
		
	}
	
	// 회원가입 기능
	@RequestMapping(value = "/lessorJoin", method = RequestMethod.POST)
	public String joinPOST(LessorVO lessor) throws Exception{
		
		System.out.print("joinPost method 실행 : ");
		
		System.out.println(lessor);
		lessorservice.lessorJoin(lessor);
		
		return "redirect:/member/lessorLogin";
	}
	
	// 아이디 중복체크
	@RequestMapping(value = "/lessorIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String lessorIdChkPOST(String lessorId) throws Exception {
		
		int result = lessorservice.idCheck(lessorId);
		
		if(result != 0) {
			return "fail";
		} else {
			return "success";
		}
	}
	
	// 닉네임 중복체크
	@RequestMapping(value = "/lessorNickNameChk", method = RequestMethod.POST)
	@ResponseBody
	public String lessorNickNameChkPOST(String lessorNickName) throws Exception {
				
	int result = lessorservice.lessorNickNameCheck(lessorNickName);
				
	if (result != 0) {
		return "fail";
	} else {
		return "success";
		}
	}
	
//	@RequestMapping(value = "/lessorNickChk", method = RequestMethod.POST)
//	@ResponseBody
//	public String lessorNickChkPOST(String lessorNickName) throws Exception {
//		
//		int result2 = lessorservice.nicknameCheck(lessorNickName);
//		
//		if(result2 != 0) {
//			return "fail";
//		} else {
//			return "success";
//		}
//	}
	
	@RequestMapping(value = "/lessorLogin", method = RequestMethod.GET)
	public String registerForm() {
		return "/member/lessorLogin";
	}
	
	// 로그인
	@RequestMapping(value = "/lessorLogin", method = RequestMethod.POST)
	public String lessorloginPOST(HttpServletRequest request, LessorVO lessor, RedirectAttributes rttr) throws Exception{
		
//		System.out.println("login 메서드 진입");
//		System.out.println("전달된 데이터 : " + lessor);
		
		HttpSession session = request.getSession();
		LessorVO lvo = lessorservice.lessorLogin(lessor);
		System.out.println(lvo);
//		System.out.println(lvo.getStatus());
		if(lvo == null){
			 return "redirect:/member/lessorLogin";
		}else if( lvo.getStatus() == 0) {
			 return "redirect:/member/lessorLogin";
		}else {
			session.setAttribute("lessor", lvo);
			return "redirect:/index";
		}
		
////		if(lvo == null) {
////			int result = 0;
////			rttr.addFlashAttribute("result", result);
////			return "redirect:/member/lessorLogin";
////		} else {
////			
////			session.setAttribute("lessor", lvo);
//			return "redirect:/index";
////		}

	}
	
//	// 로그아웃
//	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
//	public String logoutGET(HttpServletRequest request) throws Exception {
//		
//		HttpSession session = request.getSession();
//		
//		session.invalidate();
//		
//		return "redirect:/index";
//	}
	@RequestMapping(value = "lessorLoginCheck")
	@ResponseBody
	public int lessorLoginCheck(String id, String pw) throws Exception {
		LessorVO Lvo = new LessorVO();
		Lvo.setLessorId(id);
		Lvo.setLessorPw(pw);
		LessorVO newVo = lessorservice.lessorLogin(Lvo);
		System.out.println(newVo.getStatus());
		if(newVo.getStatus() == 1) {
			return 1;
		}else {
		return 0;
		}
	}
	// 아이디 찾기
		@RequestMapping(value="/findLessorId", method=RequestMethod.GET)
		public String findLessorIdGET() throws Exception {
			return "member/findLessorId";
		}
		
		@RequestMapping(value="/findLessorId", method=RequestMethod.POST)
		@ResponseBody
		public int findLessorIdPOST(LessorVO lessor, Model model) throws Exception {
			System.out.println(lessor);
			String findLessorIdVo = lessorservice.findLessorId(lessor);
			
			System.out.println(findLessorIdVo);
			
			if (findLessorIdVo == null) {
				return 0;
//				model.addAttribute("check",1);
//				return "/member/msg";
			}else {
//				model.addAttribute("check",0);
//				model.addAttribute("lessorId", findLessorIdVo.getLessorId());
				model.addAttribute("lessorId", findLessorIdVo);
				return 1;
//				return "/member/resultLessorId";
			}
		}
		
		
		// 아이디 결과
		@RequestMapping(value="/resultLessorId", method=RequestMethod.GET)
		public String resultLessorIdGET(HttpServletRequest request, Model model, @RequestParam(required=false,value="lessorNickName")String phone,String lessorNickName,LessorVO searchVO) throws Exception{
			
//			searchVO.setLessorNickName(lessorNickName);
//			searchVO.setPhone(phone);
//			LessorVO findLessorId = lessorservice.findLessorId(searchVO);
			
//			model.addAttribute("searchVO", findLessorId);
			
			return "/member/resultLessorId";
		}
		
		// 비밀번호 찾기
		@RequestMapping(value="/findLessorPw", method=RequestMethod.GET)
		public String findLessorPwGET() throws Exception {
			
			return "member/findLessorPw";
		}
		
		@RequestMapping(value="/findLessorPw", method=RequestMethod.POST)
		public String findLessorPwPOST(LessorVO lessor, HttpSession session, RedirectAttributes rttr) throws Exception{
			
			LessorVO findLessorPwVo = lessorservice.findLessorPw(lessor);
			
			if(findLessorPwVo == null) {
				rttr.addFlashAttribute("check", 1);
				return "/member/msg";
			}else {
				findLessorPwVo.setLessorId(lessor.getLessorId());
				rttr.addFlashAttribute("check",0);
				rttr.addFlashAttribute("findLessorPwVo", findLessorPwVo);
				return "redirect:/member/updateLessorPw";
			}
		}
		
		// 비밀번호 변경
		@RequestMapping(value="/updateLessorPw", method=RequestMethod.GET)
		public void updateLessorPwGET(@RequestParam(value="updateLessorPw", defaultValue="", required=false) String lessorId, LessorVO lessor) throws Exception{
			
		}
		
		@RequestMapping(value="/updateLessorPw", method=RequestMethod.POST)
		public String updateLessorPwPOST(@RequestParam(value="updatelessorPw", defaultValue="", required=false) String lessorId, LessorVO lessor) throws Exception{
			
			lessorservice.updateLessorPw(lessor);
			
			return "redirect:/member/lessorLogin";
		}
		
		// 중개인 리스트
		@GetMapping("/lessorList")
		public String lessorList(Model model) {
			System.out.println("/lessorList 요청");
			List<LessorVO> list = lessorservice.lessorList();
			model.addAttribute("list", list);
			return "member/lessorList";
		}

		
		// 이미지파일 업로드
		@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<List<LessorImgVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
			
			String uploadFolder = "C:\\lessorUpload";
			
			// 날짜 폴더 경로
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			String str  = sdf.format(date);
			String datePath = str.replace("-", File.separator);
			
			// 폴더 생성
			File uploadPath = new File(uploadFolder, datePath);
			
			for (MultipartFile multipartFile : uploadFile) {
				
				File checkfile = new File(multipartFile.getOriginalFilename());
				String type = null;
				
				try {
					type = Files.probeContentType(checkfile.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if(!type.startsWith("image")) {
					List<LessorImgVO> list = null;
					return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
				}
			}
			if(uploadPath.exists() == false) {
				uploadPath.mkdirs();
			}
			
			List<LessorImgVO> list = new ArrayList();
			
			for(MultipartFile multipartFile : uploadFile) {
				
				// 이미지 정보
				LessorImgVO imgvo = new LessorImgVO();
				
				// 파일 이름
				String uploadFileName = multipartFile.getOriginalFilename();
				imgvo.setFileName(uploadFileName);
				imgvo.setUploadPath(datePath);
				
				// uuid 적용 파일 이름
				String uuid = UUID.randomUUID().toString();
				imgvo.setUuid(uuid);
				
				uploadFileName = uuid + "_" + uploadFileName;
				
				// 파일 위치, 파일 이름을 합친 File 객체
				File saveFile = new File(uploadPath, uploadFileName);
				
				try {
					multipartFile.transferTo(saveFile);
					
					// 썸네일 생성
					File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
					
					BufferedImage bo_image = ImageIO.read(saveFile);
					
					// 비율
					double ratio = 3;
					// 넓이 높이
					int width = (int) (bo_image.getWidth() / ratio);
					int height = (int) (bo_image.getHeight() / ratio);
					
					BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
					
					Graphics2D graphic = bt_image.createGraphics();
					
					graphic.drawImage(bo_image, 0, 0, width, height, null);
					
					ImageIO.write(bt_image, "jpg", thumbnailFile);
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				list.add(imgvo);
			}
			
			ResponseEntity<List<LessorImgVO>> result = new ResponseEntity<List<LessorImgVO>>(list, HttpStatus.OK);
			
			return result;
		}
		
		// 이미지 출력
		@GetMapping("/display")
		public ResponseEntity<byte[]> getImage(String fileName) {
			// System.out.println("bg2.jpg");
			File file = new File("C:\\lessorUpload\\" + fileName);
			
			ResponseEntity<byte[]> result = null;
			
			try {
				
				HttpHeaders header = new HttpHeaders();
				
				header.add("Content-type", Files.probeContentType(file.toPath()));
				
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		// 이미지 삭제
		@PostMapping("/deleteFile")
		public ResponseEntity<String> deleteFile(String fileName) {
			
			File file = null;
			
			try {
				
				// 썸네일 파일 삭제
				file = new File("C:\\lessorUpload\\" + URLDecoder.decode(fileName, "UTF-8"));
				
				file.delete();
				
				// 원본 파일 삭제
				String originFileName = file.getAbsolutePath().replace("s_", "");
				
				file = new File(originFileName);
				
				file.delete();
				
			} catch(Exception e) {
				
				e.printStackTrace();
				
				return new ResponseEntity<String>("file", HttpStatus.NOT_IMPLEMENTED);
			}
			
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		
		@PostMapping("/goodsEnroll")
		public String goodsEnrollPOST(LessorImgVO lessorImg, RedirectAttributes rttr) throws Exception {
			lessorservice.imgEnroll(lessorImg);
			
			rttr.addFlashAttribute("enroll_esult", lessorImg.getLessorId());
			
			return "redirect:/lessor/lessorLogin";
		}
		
		
		// 뷰 봔환
		@RequestMapping(value="/lessorImg", method=RequestMethod.GET)
		public String lessorImgGET(@RequestParam String lessorId, Model model) throws Exception{
			System.out.println("lessorImg controller ");
			System.out.println(lessorId);
			LessorImgVO lessorImgVO = lessorservice.selectImg(lessorId);
			
			System.out.println(lessorImgVO);
			
			model.addAttribute("lessorImg", lessorImgVO);
			
			return "/member/lessorImg";
		}
//    @ResponseBody
//	@PostMapping("/lessorId")
//	public void successId(String lessorId,LessorVO lessor) throws Exception {
//		System.out.println(lessorId);
//		System.out.println("open! user sign success Id ajax!");
//		lessorservice.lessorId(lessor);
//		
//	}
}
