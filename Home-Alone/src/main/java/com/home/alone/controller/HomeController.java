package com.home.alone.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.home.alone.service.HomeService;
import com.home.alone.service.LikeService;
import com.home.alone.vo.HomeAddInfoVO;
import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.ImchaVO;
import com.home.alone.vo.LikeVO;

/*
	매물 관련 - 상세보기, 검색 등.
*/

@Controller
@RequestMapping("/home")
/* @SessionAttributes("home") */
public class HomeController {
	@Autowired
	private HomeService homeService;
	@Autowired
	private LikeService likeService;
	
	
	// 매물 상세보기
	@RequestMapping("/detail")	
	public String detailHome(@RequestParam("homeNum") int homeNum, HttpServletRequest request, Model model) {
		System.out.println(homeNum);
		Map<String, Object> home = homeService.getHomeDetail(homeNum);
		
		home.put("deposit", homeService.convertMoneyUnit((int)home.get("deposit")));
		home.put("monthly", homeService.convertMoneyUnit((int)home.get("monthly")));
		home.put("adminCost", homeService.convertMoneyUnit((int)home.get("adminCost")));
		
		System.out.println("detailHome: " + home);
		
		model.addAttribute("home", home);
		
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");
		System.out.println("imcha " + imcha);
		
		int homeLike = 0;	
		if(imcha != null) {	// 좋아요 표시 했는지 확인
			LikeVO likeVO = new LikeVO();
			likeVO.setHomeNum(homeNum);
			likeVO.setImchaId(imcha.getImchaId());
			
			homeLike = likeService.checkLike(likeVO);
			
			System.out.println("homeLike: " + homeLike);
			
		}
		
		model.addAttribute("homeLike", homeLike);

		return "home/detailHome";
	}
	
	// 필터 검색
	@RequestMapping(value="/homeFilter")	
	@ResponseBody
	public List<HomePreviewVO> homeFilter(@RequestBody HashMap<String, Object> filterData) {
		System.out.println("homeFilter controller");
		System.out.println(filterData);
		
		Map<String, Object> filterMap = new HashMap<>();
		List<HomePreviewVO> homeList = null;
		
		System.out.println("filterData.homeType : " + (List<String>)filterData.get("homeType"));
		
		if(filterData.containsKey("homeType")) {
			List<String> homeTypeList = new ArrayList<String>();
			for(String str : (List<String>)filterData.get("homeType")) {
				switch(str) {
				case "O" : homeTypeList.add("원룸"); break;
				case "T" : homeTypeList.add("투룸"); break;
				case "H" : homeTypeList.add("쓰리룸이상"); break;
				case "F" : homeTypeList.add("오피스텔"); break;
				case "V" : homeTypeList.add("빌라"); break;
				case "S" : homeTypeList.add("쉐어하우스"); break;
				}
			}
			filterData.put("homeType", homeTypeList);
		}
		
		if(filterData.containsKey("rentType")) {
			List<String> rentTypeList = new ArrayList<String>();
			for(String str :  (List<String>)filterData.get("rentType")) {
				switch(str) {
				case "M" : rentTypeList.add("월세"); break;
				case "J" : rentTypeList.add("전세"); break;
				}
			}
			filterData.put("rentType", rentTypeList);
		}
		
		if(filterData.containsKey("addInfo")) {
			HomeAddInfoVO homeAddInfoVO = new HomeAddInfoVO();
			for(String str : (List<String>)filterData.get("addInfo")) {
				switch(str) {
				case "parking": homeAddInfoVO.setParking(1); break;
				case "pet" : homeAddInfoVO.setPet("Y"); break;
				case "elevator" : homeAddInfoVO.setElevator("Y"); break;
				case "balcony" : homeAddInfoVO.setBalcony("Y"); break;
				}
			}
			filterData.put("addInfo", homeAddInfoVO);	// addInfo 값 변경
		}
		
		
		System.out.println();
		System.out.println(filterData);
		
		homeList = homeService.homeListByFilter(filterData);
		return homeList;
	}
	 
	// 지도 경계 내의 매물 위치 정보 
	@RequestMapping(value="/homeInBounds" , method = RequestMethod.POST)
	@ResponseBody
	public List<HomePreviewVO> homeInBounds(@RequestParam Map<String, Object> mapBounds) {
		System.out.println(mapBounds);
		List<HomePreviewVO> homeInBoundsList = null;
		homeInBoundsList = homeService.homeInBoundsList(mapBounds);
		System.out.println(homeInBoundsList);
		
		return homeInBoundsList;
	}
	
	
	// 집찾기 페이지 이동
	@RequestMapping(value="/searchHome" , method = RequestMethod.GET)
	public String searchHome(@RequestParam(required = false) String searchKeyword, Model model) {
		System.out.println(searchKeyword);
		model.addAttribute("searchKeyword", searchKeyword);
		return "home/searchHome";
	}
	
	
	// 매물이미지 
	@RequestMapping(value = "/getHomeImg", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPreviewImg(@RequestParam String homeImgFile) {
		System.out.println("homeImgFile : " + homeImgFile);
		
		ResponseEntity<byte[]> result = null;
		File imgFile = new File("C:\\homeUpload", homeImgFile);
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(imgFile.toPath()));
		
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(imgFile), header, HttpStatus.OK);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@RequestMapping(value="/report", method=RequestMethod.POST) 	// 매물 신고
	@ResponseBody
	public int reportHome(@RequestParam int homeNum, @RequestParam int reportType, @RequestParam String reportContent, HttpServletRequest request) {
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");
		
		System.out.println(imcha);
		
		if(imcha == null) {
			return 0;
		}
		
		HomeReportVO homeReportVO = new HomeReportVO();
		
		homeReportVO.setHomeNum(homeNum);
		homeReportVO.setImchaId(imcha.getImchaId());
		homeReportVO.setReportType(reportType);
		homeReportVO.setReportContent(reportContent);
		
		return homeService.reportHome(homeReportVO);
	}
	
	
}
