package com.guardian.myhome.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.guardian.myhome.dao.HomeDAO;
import com.guardian.myhome.service.HomeService;
import com.guardian.myhome.service.LikeService;
import com.guardian.myhome.vo.HomePreviewVO;
import com.guardian.myhome.vo.HomeReportVO;
import com.guardian.myhome.vo.ImchaVO;
import com.guardian.myhome.vo.LikeVO;

/*
	매물 관련 - 상세보기, 검색 등.
*/

@Controller
@RequestMapping("/home")
@SessionAttributes("home")
public class HomeController {
	@Autowired
	private HomeService homeService;
	
	@Autowired
	LikeService likeService;
	
	@Autowired
	private HomeDAO homedao;
	
	
	
	// 매물 상세보기
	@RequestMapping("/detail")	
	public String detailHome(@RequestParam("homeNum") int homeNum, HttpServletRequest request, Model model) {
		System.out.println(homeNum);
		Map<String, Object> home = homeService.selectHomeDetail(homeNum);
		
		home.put("deposit", homeService.convertMoneyUnit((int)home.get("deposit")));
		home.put("monthly", homeService.convertMoneyUnit((int)home.get("monthly")));
		home.put("adminCost", homeService.convertMoneyUnit((int)home.get("adminCost")));
		
		System.out.println("detailHome: " + home);
		model.addAttribute("home", home);
	
		
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");
		System.out.println("imcha " + imcha);
		
		int homeLike = 0;
		if(imcha != null) {
			LikeVO likeVO = new LikeVO();
			likeVO.setHomeNum(homeNum);
			likeVO.setImchaId(imcha.getImchaId());
			
			homeLike = likeService.checkLike(likeVO);
			
			System.out.println("homeLike: " + homeLike);
			
		}
		
		model.addAttribute("homeLike", homeLike);

		return "home/detailHome";
	}
	
	
	// 지도 경계 내의 매물 위치 정보 
	@RequestMapping(value="/homeInBounds", method = RequestMethod.POST)
	@ResponseBody
	public List<HomePreviewVO> homeInBounds(@RequestParam Map<String, Object> mapBounds) {
		List<HomePreviewVO> homeInBoundsList = null;
		homeInBoundsList = homeService.homeInBoundsList(mapBounds);
		System.out.println(homeInBoundsList);
		
		return homeInBoundsList;
	}
	
	
	/*
	 * public String searchKeyword(@RequestParam("searchKeyword") String
	 * searchKeyword, Model model) { System.out.println("searhKeyword" +
	 * searchKeyword); model.addAttribute(searchKeyword, "searchKeyword"); return
	 * "home/searchHome"; }
	 */
	
	@RequestMapping(value="/searchHome" , method = RequestMethod.GET)
	public String searchHome() {
		return "home/searchHome";
	}
	
	/*
	 * @RequestMapping(value="/searchHome" , method = RequestMethod.POST) public
	 * String searchHomePost(@RequestParam String searchKeyword, Model model) {
	 * model.addAttribute("searchKeyword", searchKeyword); return "home/searchHome";
	 * }
	 */
	
	
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
	
	@RequestMapping(value="/report", method=RequestMethod.POST) 
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
	
//	// 허위 매물 목록 리스트
//	@GetMapping("/reportHome")
//	public String HomeReport(Model model) {
//		System.out.println("/HomeList 요청");
//		List<HomeReportVO> list = homedao.selectReportHomeList();
//		model.addAttribute("list", list);
//		return "admin/reportHome";
//	}
}
