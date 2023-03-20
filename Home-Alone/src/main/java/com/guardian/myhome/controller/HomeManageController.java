package com.guardian.myhome.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.guardian.myhome.dao.HomeDAO;
import com.guardian.myhome.mapper.HomeMapper;
import com.guardian.myhome.service.HomeService;
import com.guardian.myhome.vo.HomeImgVO;
import com.guardian.myhome.vo.HomePreviewVO;
import com.guardian.myhome.vo.HomePriceVO;
import com.guardian.myhome.vo.HomeVO;
import com.guardian.myhome.vo.LessorVO;

import net.coobird.thumbnailator.Thumbnailator;

/*
 	집(매물) 관리 관련 컨트롤러 
 	- 등록, 수정, 상태 변경 등 
 */

@Controller
@RequestMapping("/home/manage")
public class HomeManageController {
	@Autowired
	HomeService homeService;
	
	@Autowired
	HomeMapper homeMapper;
	
	@Autowired
	HomeDAO homeDAO;
	
	@RequestMapping("/list")
	public String getHomeList(Model model,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		LessorVO lessorVO = (LessorVO) session.getAttribute("lessor");	// 글 등록 아이디
		System.out.println(lessorVO.getLessorId());
		List<HomePreviewVO> manageList = homeService.getListByLessorId(lessorVO);
//		System.out.println(manageList);
//		for (int i=0; i<manageList.size(); i++) {
//			List<HomeImgVO> img = homeDAO.selectHomeImgList(manageList.get(i).getHomeNum());
//			manageList.get(i).setHomeImg(img.get(0));
//		}
		model.addAttribute("manageList", manageList);
		return "mypage/homeManage";
	}
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm() {
		return "home/manage/registerHome";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public int registerHome(@RequestBody HashMap<String, Object> homeData, HttpServletRequest request) {
		LessorVO lessorVO = (LessorVO) request.getSession().getAttribute("lessor");	// 글 등록 아이디
		if(lessorVO == null)
			return 0;
		
		HomeVO homeVO = new HomeVO();
		homeVO.setLessorId(lessorVO.getLessorId());
		HomePriceVO homePriceVO = new HomePriceVO();
		List<String> homeOptionList = new ArrayList<>();
		List<HomeImgVO> homeImgList = new ArrayList<>();

		Map<String, Object> insertMap = new HashMap<>();
		
		for(String key: homeData.keySet()) { 	// view에서 가져온 키 값들을 돌면서 체크
			if(!(homeData.get(key).equals(""))) {	// 공백이 아닌 것( 데이터를 갖고 있는 것)
				switch(key) {
					case "homeType": 
						homeVO.setHomeType((String)homeData.get(key)); 
						break;
					case "addr1" : 
						homeVO.setAddr1((String)homeData.get(key)); 
						break;
					case "addr2" : 
						homeVO.setAddr2((String)homeData.get(key)); 
						break;
					case "addr3" : 
						homeVO.setAddr3((String)homeData.get(key)); 
						break;
					case "latitude":
						homeVO.setLatitude(Double.parseDouble((String)homeData.get(key)));
						break;
					case "longitude":
						homeVO.setLongitude(Double.parseDouble((String)homeData.get(key)));
						break;
					case "homeArea" : 
						homeVO.setHomeArea(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "rentType" : 
						homeVO.setRentType((String)homeData.get(key)); 
						break;
					case "deposit": 
						homePriceVO.setDeposit(Integer.parseInt(String.valueOf(homeData.get(key)))); 
						break;
					case "monthly": 
						homePriceVO.setMonthly(Integer.parseInt(String.valueOf(homeData.get(key)))); 
						break;
					case "rentPeriods": 
						homeVO.setRentPeriods(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "roomCount" : 
						homeVO.setRoomCount(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "adminCost": 
						homePriceVO.setAdminCost(Integer.parseInt(String.valueOf(homeData.get(key)))); 
						break;
					case "parking": 
						homeVO.setParking(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "pet": 
						homeVO.setPet((String)homeData.get(key)); 
						break;
					case "elevator":
						homeVO.setElevator((String)homeData.get(key)); 
						break;
					case "balcony": 
						homeVO.setBalcony((String)homeData.get(key)); 
						break;
					case "moveDate": 
						try {
							String dateStr = (String)homeData.get(key);
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							Date date;
							date = format.parse(dateStr);
							System.out.println(date);
							homeVO.setMoveDate(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case "floor": 
						homeVO.setFloor(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "homeTitle": 
						homeVO.setHomeTitle((String)homeData.get(key));
						break;
					case "homeDetail": 
						homeVO.setHomeDetail((String)homeData.get(key)); 
						break;
					case "optionList":
						homeOptionList = (List<String>)homeData.get(key);
						break;
					case "homeImgList":
						for(String img : (List<String>)homeData.get(key)) {
							HomeImgVO homeImgVO = new HomeImgVO();
							String[] imgStr = img.split(" ");
							System.out.println(imgStr);
							
							homeImgVO.setHomeImgPath(imgStr[0]);
							homeImgVO.setHomeImgName(imgStr[1]);
							homeImgList.add(homeImgVO);
						}
						break;
				}
			}
			
		}
		System.out.println("----- HomeManageController ----");
		System.out.println("homeVO: " + homeVO);
		System.out.println("homePriceVO: " + homePriceVO);
		System.out.println("homeImgList: " + homeImgList);
		System.out.println("homeOptionList: " + homeOptionList);
		
		insertMap.put("homeVO", homeVO);
		insertMap.put("homePriceVO", homePriceVO);
		insertMap.put("homeImgList", homeImgList);
		insertMap.put("homeOptionList", homeOptionList);
		
		int result = homeService.insertHome(insertMap);	// 매물 정보 삽입.
		
		return result;
	}
	
	
	// 매물 수정 페이지
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyHome(@RequestParam("homeNum")int homeNum, Model model, HttpServletRequest request) {
		LessorVO lessorVO = (LessorVO) request.getSession().getAttribute("lessor");	
		Map<String, Object> home = homeService.selectHomeDetail(homeNum);	// 원래 정보 가져오기
		System.out.println("detailHome: " + home);
		
		home.put("deposit", (int)home.get("deposit")/10000);
		home.put("monthly", (int)home.get("monthly")/10000);
		home.put("adminCost", (int)home.get("adminCost")/10000);
		model.addAttribute("home", home);
		return "home/manage/modifyHome";
	}
	
	
	// 매물 수정 처리
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public int modifyHome(@RequestBody HashMap<String, Object> homeData) {
		System.out.println("modifyHome Controller 실행");
		System.out.println(homeData);
		
		int homeNum = (int) homeData.get("homeNum");
		
		System.out.println("homeNum = " + homeNum);
		System.out.println("homeData = " + homeData);
		HomeVO homeVO = new HomeVO();
		homeVO.setHomeNum(homeNum);
		
		HomePriceVO homePriceVO = new HomePriceVO();
		List<String> homeOptionList = new ArrayList<>();
		List<HomeImgVO> homeImgList = new ArrayList<>();

		Map<String, Object> modifyMap = new HashMap<>();
		
		for(String key: homeData.keySet()) { 	// view에서 가져온 키 값들을 돌면서 체크
			if(!(homeData.get(key).equals(""))) {	// 공백이 아닌 것( 데이터를 갖고 있는 것)
				switch(key) {
					case "homeType": 
						homeVO.setHomeType((String)homeData.get(key)); 
						break;
					case "addr1" : 
						homeVO.setAddr1((String)homeData.get(key)); 
						break;
					case "addr2" : 
						homeVO.setAddr2((String)homeData.get(key)); 
						break;
					case "addr3" : 
						homeVO.setAddr3((String)homeData.get(key)); 
						break;
					case "latitude":
						homeVO.setLatitude(Double.parseDouble((String)homeData.get(key)));
						break;
					case "longitude":
						homeVO.setLongitude(Double.parseDouble((String)homeData.get(key)));
						break;
					case "homeArea" : 
						homeVO.setHomeArea(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "rentType" : 
						homeVO.setRentType((String)homeData.get(key)); 
						break;
					case "deposit": 
						homePriceVO.setDeposit(Integer.parseInt(String.valueOf(homeData.get(key)))); 
						break;
					case "monthly": 
						homePriceVO.setMonthly(Integer.parseInt(String.valueOf(homeData.get(key)))); 
						break;
					case "rentPeriods": 
						homeVO.setRentPeriods(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "roomCount" : 
						homeVO.setRoomCount(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "adminCost": 
						homePriceVO.setAdminCost(Integer.parseInt(String.valueOf(homeData.get(key)))); 
						break;
					case "parking": 
						homeVO.setParking(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "pet": 
						homeVO.setPet((String)homeData.get(key)); 
						break;
					case "elevator":
						homeVO.setElevator((String)homeData.get(key)); 
						break;
					case "balcony": 
						homeVO.setBalcony((String)homeData.get(key)); 
						break;
					case "moveDate": 
						try {
							String dateStr = (String)homeData.get(key);
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							Date date;
							date = format.parse(dateStr);
							System.out.println(date);
							homeVO.setMoveDate(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case "floor": 
						homeVO.setFloor(Integer.parseInt((String) homeData.get(key))); 
						break;
					case "homeTitle": 
						homeVO.setHomeTitle((String)homeData.get(key));
						break;
					case "homeDetail": 
						homeVO.setHomeDetail((String)homeData.get(key)); 
						break;
					case "optionList":
						homeOptionList = (List<String>)homeData.get(key);
						break;
					case "homeImgList":
						for(String img : (List<String>)homeData.get(key)) {
							HomeImgVO homeImgVO = new HomeImgVO();
							String[] imgStr = img.split(" ");
							System.out.println(imgStr);
							
							homeImgVO.setHomeImgPath(imgStr[0]);
							homeImgVO.setHomeImgName(imgStr[1]);
							homeImgList.add(homeImgVO);
						}
						break;
				}
			}
			
		}
		System.out.println("----- HomeManageController ----");
		System.out.println("homeVO: " + homeVO);
		System.out.println("homePriceVO: " + homePriceVO);
		System.out.println("homeImgList: " + homeImgList);
		System.out.println("homeOptionList: " + homeOptionList);
		
		modifyMap.put("homeVO", homeVO);
		modifyMap.put("homePriceVO", homePriceVO);
		modifyMap.put("homeImgList", homeImgList);
		modifyMap.put("homeOptionList", homeOptionList);
		
		int result = homeService.modifyHomeInfo(modifyMap);// 매물 정보 수정
		
		return result;
	}
	
	
	
	// 매물 사진 로컬 저장소에 업로드 		
	@RequestMapping(value = "/homeImgUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<HomeImgVO>> uploadImage(@RequestParam MultipartFile[] homeImg, HttpServletRequest request, 
							HttpServletResponse response) {
		
		// 파일 유형이 이미지 파일인지 확인
		for(MultipartFile multifile : homeImg) {
			File fileCheck = new File(multifile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(fileCheck.toPath());		
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			if(!type.startsWith("image")) {
				List<HomeImgVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
			}

		}
		
		String uploadPath = "C:\\homeUpload";	// 파일 저장 경로
//		String uploadPath = "/Users/sihyun/homeUpload";	// 파일 저장 경로
		String homeImgPath = "homeImg";
		File uploadFolder = new File(uploadPath, homeImgPath);
		
		if(!uploadFolder.exists())	// 위 경로가 존재하지 않으면
			uploadFolder.mkdirs();
		
		List<HomeImgVO> homeImgList = new ArrayList<>();	// 매물 이미지 담는 ArrayList 객체
		
		for(MultipartFile multifile : homeImg) {
			
			HomeImgVO homeImgVO = new HomeImgVO();	// 매물 이미지 정보 객체 생성
			
			String imgName = multifile.getOriginalFilename();	// 파일 이름
			String uuid = UUID.randomUUID().toString();	// 사진 파일 이름이 중복 되면 덮어 쓰기 때문에 이를 방지 하기 위한 UUID(식별자)를 적용.
			imgName = uuid + "_" + imgName;	// ex) uuid_fileName.jpg/png

			// 매물 객체 정보 저장	
			homeImgVO.setHomeImgName(imgName);	
			homeImgVO.setHomeImgPath(homeImgPath);	
			
			File save = new File(uploadFolder, imgName);		
			System.out.println("파일 이름 : " + imgName);		
			System.out.println("파일 타입 : " + multifile.getContentType());
			System.out.println("파일 크기 : " + multifile.getSize());
			
			try {
				multifile.transferTo(save); // 파일 저장 

				// 썸네일 이미지 저장 
				FileOutputStream thumbnail = new FileOutputStream(new File(uploadFolder, "t_" + imgName));
				Thumbnailator.createThumbnail(multifile.getInputStream(), thumbnail, 200, 200);
				thumbnail.close();
				
				homeImgList.add(homeImgVO);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			ResponseEntity<List<HomeImgVO>> result = new ResponseEntity<List<HomeImgVO>>(homeImgList, HttpStatus.OK);
			return result;
		}
		return null;
	}
	
	// 이미지 보여주기
	@RequestMapping(value = "/showHomeImg", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getHomeImg(String homeImgName) {
		System.out.println(homeImgName);
		File imgFile = new File("C:\\homeUpload", homeImgName);
//		File imgFile = new File("/Users/sihyun/homeUpload", homeImgName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(imgFile.toPath()));
		
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(imgFile), header, HttpStatus.OK);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	// 매물 사진 삭제
	@RequestMapping(value = "/removeHomeImg" , method = RequestMethod.POST)	// 매물 사진 삭제
	public ResponseEntity<String> deleteHomeImg(String homeImgName) {
		File file = null;
		
		System.out.println("deleteHome:" +homeImgName);
		try {
			// 썸네일 파일 삭제
			file = new File("C:\\homeUpload\\"+ URLDecoder.decode(homeImgName, "UTF-8"));
			file.delete();
			
			// 원본 파일 삭제
			String originImgName = file.getAbsolutePath().replace("t_", "");	// 썸네일 파일 "t_" 붙은 것을 ""로
			System.out.println(originImgName);
			
			file = new File(originImgName);
			file.delete();
		
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);	// 성공했다고 리턴.
	}
	
	@RequestMapping("/deleteHome")
	public String deleteHome(int homeNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session =  request.getSession();
		LessorVO lessorVO = (LessorVO) session.getAttribute("lessor");
		homeService.deleteHome(homeNum);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        out.println("<script>alert('삭제가 완료되었습니다.');location.href='/home/manage/list?lessorId='+lessorId;</script>");
        out.flush();
		return "redirect:/home/manage/list?lessorId="+lessorVO.getLessorId();
	}
}
