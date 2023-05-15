package com.home.alone.service;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.dao.HomeDAO;
import com.home.alone.dao.HomeInquryDAO;
import com.home.alone.member.vo.LessorVO;
import com.home.alone.vo.HomeAddInfoVO;
import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeImgVO;
import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;
import com.home.alone.vo.HomeOptionVO;
import com.home.alone.vo.HomeOverviewVO;
import com.home.alone.vo.HomePriceVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeReservOverviewVO;
import com.home.alone.vo.HomeReservVO;
import com.home.alone.vo.HomeVO;

@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	private HomeDAO homeDAO;
	
	@Override
	public int registerHome(Map<String, Object> insertMap) {	// 매물 등록
		System.out.println("\n============ HomeService registerHome 실행 =============\n");
		if(insertMap == null)
			 return 0;
		
		System.out.println(insertMap);
		HomeVO homeVO = (HomeVO)insertMap.get("home");
		homeDAO.insertHome(homeVO);	// Home_tb에 매물 기본 정보 삽입
		int homeNum = homeVO.getHomeNum();
		System.out.println("매물번호:" + homeNum);
		
		
		// 사진 경로 
		System.out.println((List<HomeImgVO>)insertMap.get("homeImgList"));
		List<HomeImgVO> homeImgList = (List<HomeImgVO>)insertMap.get("homeImgList");
		for(int i=0; i<homeImgList.size(); i++) 
			homeImgList.get(i).setHomeNum(homeNum);
		System.out.println(homeImgList);
		
		// 옵션 정보 삽입
		HomeOptionVO homeOptionVO = (HomeOptionVO)insertMap.get("homeOption");
		homeOptionVO.setHomeNum(homeNum);
		System.out.println(homeOptionVO);
		
		// 가격 정보
		HomePriceVO homePriceVO = (HomePriceVO)insertMap.get("homePrice");
		homePriceVO.setHomeNum(homeNum);
		
		// 추가 정보
		HomeAddInfoVO homeAddInfoVO = (HomeAddInfoVO)insertMap.get("homeAddInfo");
		homeAddInfoVO.setHomeNum(homeNum);
		System.out.println(homeAddInfoVO);
		
		homeDAO.insertHomeImgList(homeImgList);	// Home_Img_tb에 사진 경로 저장
		homeDAO.insertHomeOption(homeOptionVO);	// Home_Option_tb에 매물 옵션 정보 저장
		homeDAO.insertHomeAddInfo(homeAddInfoVO);	// Home_AddInfo_tb에 매물 추가정보 저장
		homeDAO.insertHomePrice(homePriceVO);	//  Home_Price_tb에 매물 가격 정보 저장
	
		return 1;
	}
	
	// 지도 경계 내에 매물 리스트 가져오기
	@Override 
	public List<HomeOverviewVO> homeInBoundsList(Map<String, Object> mapBounds) {
		List<HomeOverviewVO> homeInBoundsList = null;
		homeInBoundsList = homeDAO.selectHomeInBoundsList(mapBounds);
		
		for(int i=0; i<homeInBoundsList.size(); i++) {
			int homeNum = homeInBoundsList.get(i).getHomeNum();
			int deposit = homeInBoundsList.get(i).getDeposit();
			int monthly = homeInBoundsList.get(i).getMonthly();
			
			HomeOptionVO homeOptionVO = homeDAO.selectHomeOption(homeNum);	// 매물 옵션 정보 저장
			homeInBoundsList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));	// 매물 사진 미리보기 저장
			
			homeInBoundsList.get(i).setDepositUnit(convertMoneyUnit(deposit));
			homeInBoundsList.get(i).setMonthlyUnit(convertMoneyUnit(monthly));
		}
		
		return homeInBoundsList;
	}
	
	// 매물 필터 검색
	@Override
	public List<HomeOverviewVO> homeListByFilter(Map<String, Object> filterData) {	// 매물 필터 검색
		List<HomeOverviewVO> homeList = null;
		homeList = homeDAO.selectHomeListByFilter(filterData);
		
		
		for(int i=0; i<homeList.size(); i++) {
			int homeNum = homeList.get(i).getHomeNum();
			int deposit = homeList.get(i).getDeposit();
			int monthly = homeList.get(i).getMonthly();
			HomeOptionVO homeOptionVO = homeDAO.selectHomeOption(homeNum);
			
			homeList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
			homeList.get(i).setDepositUnit(convertMoneyUnit(deposit));
			homeList.get(i).setMonthlyUnit(convertMoneyUnit(monthly));
		}
		
		System.out.println("homeListByFilter");
		System.out.println(homeList);
		return homeList;
	}
	
	

	@Override
	public Map<String, Object> getHomeDetail(int homeNum) {	// 매물상세정보
		
		HomeDetailVO homeDetailVO = homeDAO.selectHomeDetail(homeNum);
		homeDetailVO.setHomeImgList(homeDAO.selectHomeImgList(homeNum));
		HomeAddInfoVO homeAddInfoVO = homeDAO.selectHomeAddInfo(homeNum);
		HomeOptionVO homeOptionVO = homeDAO.selectHomeOption(homeNum);
		
		List<String> optionList = Arrays.asList(homeOptionVO.getOptionList().split(", "));
		
		System.out.println("optionList : ");
		System.out.println(optionList);
		
		Map<String, Object> home = new HashMap<>();
		home.put("homeNum", homeDetailVO.getHomeNum());
		home.put("homeType", homeDetailVO.getHomeType());
		home.put("addr1", homeDetailVO.getAddr1());
		home.put("addr2", homeDetailVO.getAddr2());
		home.put("addr3", homeDetailVO.getAddr3());
		home.put("latitude", homeDetailVO.getLatitude());
		home.put("longitude", homeDetailVO.getLongitude());
		home.put("homeArea", homeDetailVO.getHomeArea());
		home.put("rentType", homeDetailVO.getRentType());
		home.put("rentPeriods", homeDetailVO.getRentPeriods());
		home.put("rentUnit", homeDetailVO.getRentUnit());
		home.put("roomCount", homeDetailVO.getRoomCount());
		home.put("floor", homeDetailVO.getFloor());
		home.put("totalFloor", homeDetailVO.getTotalFloor());
		home.put("homeTitle", homeDetailVO.getHomeTitle());
		home.put("homeDetail", homeDetailVO.getHomeDetail());
		home.put("homeImgList", homeDetailVO.getHomeImgList());
		home.put("jgsName", homeDetailVO.getJgsName());
		home.put("jgsNum", homeDetailVO.getJgsNum());
		home.put("phone", homeDetailVO.getPhone());
		home.put("lessorName", homeDetailVO.getName());
		home.put("lessorAddr", homeDetailVO.getLessorAddr2() + " " + homeDetailVO.getLessorAddr3());
		home.put("deposit", homeDetailVO.getDeposit());
		home.put("monthly", homeDetailVO.getMonthly());
		home.put("adminCost",homeDetailVO.getAdminCost());
		home.put("lessorId", homeDetailVO.getLessorId());
		home.put("parking", homeAddInfoVO.getParking());
		home.put("pet", homeAddInfoVO.getPet());
		home.put("elevator", homeAddInfoVO.getElevator());
		home.put("balcony", homeAddInfoVO.getBalcony());
		home.put("moveDate", homeAddInfoVO.getMoveDate());
		home.put("optionList", optionList);
		
		return home;
	}
	

	@Override
	public int modifyHomeInfo(Map<String, Object> modifyHome) {	// 매물 정보 수정.
		System.out.println("\n============ HomeService modifyHomeInfo()실행 =============\n");
		if(modifyHome == null)
			 return 0;
		
		// home_tb(매물 기본 테이블 정보) 수정
		System.out.println(modifyHome);
		HomeVO homeVO = (HomeVO)modifyHome.get("home");
		homeDAO.updateHome(homeVO);
		
		// 매물 사진 삭제 (사진은 수정 개념이 X) 
		System.out.println((List<HomeImgVO>)modifyHome.get("homeImgList"));
		List<HomeImgVO> homeImgList = (List<HomeImgVO>)modifyHome.get("homeImgList");
		if(homeImgList != null) {
			homeDAO.deleteHomeImg(homeVO.getHomeNum());
			for(int i=0; i<homeImgList.size(); i++) 
				homeImgList.get(i).setHomeNum(homeVO.getHomeNum());
			System.out.println(homeImgList);
		}
		homeDAO.insertHomeImgList(homeImgList);
		
		// 매물 옵션 수정
		HomeOptionVO homeOptionVO = (HomeOptionVO)modifyHome.get("homeOption");
		homeOptionVO.setHomeNum(homeVO.getHomeNum());
		System.out.println(homeOptionVO);
		homeDAO.updateHomeOption(homeOptionVO);
		
		// 매물 추가 정보 수정
		HomeAddInfoVO homeAddInfoVO = (HomeAddInfoVO)modifyHome.get("homeAddInfo");
		homeAddInfoVO.setHomeNum(homeVO.getHomeNum());
		homeDAO.updateHomeAddInfo(homeAddInfoVO);
		
		// 매물 가격 정보 수정
		HomePriceVO homePriceVO = (HomePriceVO)modifyHome.get("homePrice");
		homePriceVO.setHomeNum(homeVO.getHomeNum());
		homeDAO.updateHomePrice(homePriceVO);
		
		return 1;
	}
	
	// 매물 신고 등록
	@Override
	public int reportHome(HomeReportVO homeReportVO) {
		return homeDAO.insertHomeReport(homeReportVO);
	}

	// 매물 신고 목록
	@Override
	public List<HomeReportVO> getReportHomeList() {
		return homeDAO.selectReportHomeList();
	}

	// 임대인이 등록한 매물 리스트 
	@Override
	public List<HomeOverviewVO> getListByLessorId(LessorVO vo) {
		System.out.println(vo);
		List<HomeOverviewVO> homeList = null;
		homeList = homeDAO.selectHomeListByLessorId(vo);
		
		// 미리보기 사진 가져오기
		for(int i=0; i<homeList.size(); i++) {
			int homeNum = homeList.get(i).getHomeNum();
			int deposit = homeList.get(i).getDeposit();
			int monthly = homeList.get(i).getMonthly();
			homeList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
			homeList.get(i).setDepositUnit(convertMoneyUnit(deposit));
			homeList.get(i).setMonthlyUnit(convertMoneyUnit(monthly));
		}
		
		return homeList;
	}

	@Override
	public int deleteHome(int homeNum) {	// 매물 삭제
		return homeDAO.deleteHome(homeNum);
	}

	// 매물 상세 보기시 화면 단에서 가격 정보 단위 표시하기 위해
	@Override
	public String convertMoneyUnit(long money) {
		String convert = "";
		
		if(money == 0) {
			convert = "없음";
		}else if(money >= 10000) {
			convert += money / 10000 + "억 ";
			
			if(money % 10000 != 0) {
				money = money % 10000;
				convert += money + "만원";
			}
		}else {
			convert +=  money + "만원";	
		}
		return convert;
	}

	@Override
	public int changeHomeStatusStop(int homeNum) {
		return homeDAO.updateHomeInvalid(homeNum);
	}

	@Override
	public int changeHomeStatusPost(int homeNum) {
		return homeDAO.updateHomeValid(homeNum);
	}
	
}