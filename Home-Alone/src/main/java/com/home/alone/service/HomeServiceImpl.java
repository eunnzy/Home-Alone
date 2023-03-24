package com.home.alone.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.dao.HomeDAO;
import com.home.alone.mapper.HomeMapper;
import com.home.alone.vo.HomeAddInfoVO;
import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeImgVO;
import com.home.alone.vo.HomeOptionVO;
import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomePriceVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeVO;
import com.home.alone.vo.LessorVO;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeDAO homeDAO;
	
	@Autowired
	HomeMapper homeMapper;

	@Override
	public int insertHome(Map<String, Object> insertMap) {
		System.out.println("\n============ HomeService insertHome 실행 =============\n");
		if(insertMap == null)
			 return 0;
		
		System.out.println(insertMap);
		HomeVO homeVO = (HomeVO)insertMap.get("home");
		homeDAO.insertHome(homeVO);
		int homeNum = homeVO.getHomeNum();
		System.out.println("매물번호:" + homeNum);
		
		
		// 사진
		System.out.println((List<HomeImgVO>)insertMap.get("homeImgList"));
		List<HomeImgVO> homeImgList = (List<HomeImgVO>)insertMap.get("homeImgList");
		for(int i=0; i<homeImgList.size(); i++) 
			homeImgList.get(i).setHomeNum(homeNum);
		System.out.println(homeImgList);
		
		
		HomeOptionVO homeOptionVO = (HomeOptionVO)insertMap.get("homeOption");
		homeOptionVO.setHomeNum(homeNum);
//		for(String op: (List<String>)insertMap.get("homeOptionList")) {
//			switch(op) {
//				case "washer": homeOptionVO.setWasher("Y"); break;
//				case "telev": homeOptionVO.setTelev("Y"); break;
//				case "microWav": homeOptionVO.setMicroWav("Y"); break;
//				case "gasRange": homeOptionVO.setGasRange("Y"); break;
//				case "induction": homeOptionVO.setInduction("Y"); break;
//				case "desk": homeOptionVO.setDesk("Y"); break;
//				case "bed": homeOptionVO.setBed("Y"); break;
//				case "aircon": homeOptionVO.setAircon("Y"); break;
//				case "fridge": homeOptionVO.setFridge("Y"); break;
//				case "shoes": homeOptionVO.setShoes("Y"); break;
//				case "closet": homeOptionVO.setCloset("Y"); break;
//				case "doorLock": homeOptionVO.setDoorLock("Y"); break;
//				case "bidet": homeOptionVO.setBidet("Y"); break;
//			}
//		}
		System.out.println(homeOptionVO);
		
		// 가격 정보
		HomePriceVO homePriceVO = (HomePriceVO)insertMap.get("homePrice");
		homePriceVO.setHomeNum(homeNum);
		
		// 추가 정보
		HomeAddInfoVO homeAddInfoVO = (HomeAddInfoVO)insertMap.get("homeAddInfo");
		homeAddInfoVO.setHomeNum(homeNum);
		System.out.println(homeAddInfoVO);
		
		homeDAO.insertHomeImgList(homeImgList);
		homeDAO.insertHomeOption(homeOptionVO);
		homeDAO.insertHomeAddInfo(homeAddInfoVO);
		homeDAO.insertHomePrice(homePriceVO);
		
		return 1;
		
	}
	
	@Override 
	public List<HomePreviewVO> homeInBoundsList(Map<String, Object> mapBounds) {
		List<HomePreviewVO> homeInBoundsList = null;
		homeInBoundsList = homeDAO.selectHomeInBoundsList(mapBounds);
		
		for(int i=0; i<homeInBoundsList.size(); i++) {
			int homeNum = homeInBoundsList.get(i).getHomeNum();
			
			HomeOptionVO homeOptionVO = homeDAO.selectHomeOption(homeNum);
			/*
			 * List<String> optionList =
			 * Arrays.asList(homeOptionVO.getOptionList().split(", "));
			 * 
			 * 
			 * homeInBoundsList.get(i).setOptionList(optionList);
			 */
			homeInBoundsList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
		}
		
		return homeInBoundsList;
	}
	
	
	@Override
	public List<HomePreviewVO> homeListByFilter(Map<String, Object> filterData) {
		List<HomePreviewVO> homeList = null;
		homeList = homeDAO.selectHomeListByFilter(filterData);
		
		
		for(int i=0; i<homeList.size(); i++) {
			int homeNum = homeList.get(i).getHomeNum();
			
			HomeOptionVO homeOptionVO = homeDAO.selectHomeOption(homeNum);
		//	List<String> optionList = Arrays.asList(homeOptionVO.getOptionList().split(", "));

			//homeList.get(i).setOptionList(optionList);
			homeList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
		}
		
		System.out.println("homeListByFilter");
		System.out.println(homeList);
		return homeList;
	}
	
	

	@Override
	public Map<String, Object> selectHomeDetail(int homeNum) {
		
		HomeDetailVO homeDetailVO = homeDAO.selectHomeDetail(homeNum);
		homeDetailVO.setHomeImgList(homeDAO.selectHomeImgList(homeNum));

		HomeAddInfoVO homeAddInfoVO = homeDAO.selectHomeAddInfo(homeNum);
		HomeOptionVO homeOptionVO = homeDAO.selectHomeOption(homeNum);
		
		List<String> optionList = Arrays.asList(homeOptionVO.getOptionList().split(", "));
		
//		if(homeOptionVO.getWasher().equals("Y")) optionList.add("washer");
//		if(homeOptionVO.getTelev().equals("Y")) optionList.add("telev");
//		if(homeOptionVO.getMicroWav().equals("Y")) optionList.add("microWav");
//		if(homeOptionVO.getGasRange().equals("Y")) optionList.add("gasRange");
//		if(homeOptionVO.getInduction().equals("Y")) optionList.add("induction");
//		if(homeOptionVO.getDesk().equals("Y")) optionList.add("desk");
//		if(homeOptionVO.getBed().equals("Y")) optionList.add("bed");
//		if(homeOptionVO.getAircon().equals("Y")) optionList.add("aircon");
//		if(homeOptionVO.getFridge().equals("Y")) optionList.add("fridge");
//		if(homeOptionVO.getShoes().equals("Y")) optionList.add("shoes");
//		if(homeOptionVO.getCloset().equals("Y")) optionList.add("closet");
//		if(homeOptionVO.getDoorLock().equals("Y")) optionList.add("doorLock");
//		if(homeOptionVO.getBidet().equals("Y")) optionList.add("bidet");

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
	
	// 매물 상세 보기시 화면 단에서 가격 정보 단위 표시
	@Override
	public String convertMoneyUnit(long money) {
		String convert = "";
		if(money == 0) {
			convert = "없음";
		}else if(money >= 10000) {
			convert += money / 10000 + "억 ";
			
			if(money % 10000 != 0) {
				money = money % 10000;
				convert += money + "만";
			}
		}else {
			convert +=  money + "만";	
		}
		
		return convert;
	}
	

	@Override
	public List<HomeVO> selectAllHomeList() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public int reportHome(HomeReportVO homeReportVO) {
		return homeDAO.insertHomeReport(homeReportVO);
	}

	@Override
	public List<HomeReportVO> selectReportHomeList() {
		return homeDAO.selectReportHomeList();
	}

	@Override
	public List<HomePreviewVO> getListByLessorId(LessorVO vo) {
		System.out.println(vo);
		List<HomePreviewVO> manageList = null;
		manageList = homeDAO.getListByLessorId(vo);
		
		for(int i=0; i<manageList.size(); i++) {
			int homeNum = manageList.get(i).getHomeNum();
			manageList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
		}
		
		return manageList;
	}

	@Override
	public int deleteHome(@Param("homeNum")int homeNum) {
		return homeDAO.deleteHome(homeNum);
	}

	
}