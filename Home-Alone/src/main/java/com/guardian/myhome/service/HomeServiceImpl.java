package com.guardian.myhome.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guardian.myhome.dao.HomeDAO;
import com.guardian.myhome.mapper.HomeMapper;
import com.guardian.myhome.vo.HomeDetailVO;
import com.guardian.myhome.vo.HomeImgVO;
import com.guardian.myhome.vo.HomeOptionVO;
import com.guardian.myhome.vo.HomePreviewVO;
import com.guardian.myhome.vo.HomePriceVO;
import com.guardian.myhome.vo.HomeReportVO;
import com.guardian.myhome.vo.HomeVO;
import com.guardian.myhome.vo.LessorVO;

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
		HomeVO homeVO = (HomeVO)insertMap.get("homeVO");
		homeDAO.insertHome(homeVO);
		int homeNum = homeVO.getHomeNum();
		System.out.println("매물번호:" + homeNum);
		
		System.out.println((List<HomeImgVO>)insertMap.get("homeImgList"));
		List<HomeImgVO> homeImgList = (List<HomeImgVO>)insertMap.get("homeImgList");
		for(int i=0; i<homeImgList.size(); i++) 
			homeImgList.get(i).setHomeNum(homeNum);
		System.out.println(homeImgList);
		
		List<HomeOptionVO> homeOptionList = new ArrayList<>();
		for(String op: (List<String>)insertMap.get("homeOptionList")) {
			HomeOptionVO homeOptionVO = new HomeOptionVO();
			homeOptionVO.setHomeNum(homeNum);
			homeOptionVO.setOptionName(op);
			homeOptionList.add(homeOptionVO);
		}
		System.out.println(homeOptionList);
		
		HomePriceVO homePriceVO = (HomePriceVO)insertMap.get("homePriceVO");
		homePriceVO.setHomeNum(homeNum);
		
		homeDAO.insertHomeImgList(homeImgList);
		homeDAO.insertHomeOptionList(homeOptionList);
		homeDAO.insertHomePrice(homePriceVO);
		
		return 1;
		
	}
	
	// 현재 위치를 기준으로 지도 경계에 존재하는 매물 리스트 반환하기 
//	@Override 
//	public List<HomePreviewVO> homeInBoundsList(Map<String, Object> mapBounds) {
//		List<HomePreviewVO> homeInBoundsList = null;
//		homeInBoundsList = homeDAO.selectHomeInBoundsList(mapBounds);
//		
//		for(int i=0; i<homeInBoundsList.size(); i++) {
//			int homeNum = homeInBoundsList.get(i).getHomeNum();
//			homeInBoundsList.get(i).setHomeImgVO(homeDAO.selectPreviewHomeImg(homeNum));
//		}
//		
//		return homeInBoundsList;
//	}
	
	@Override 
	public List<HomePreviewVO> homeInBoundsList(Map<String, Object> mapBounds) {
		List<HomePreviewVO> homeInBoundsList = null;
		homeInBoundsList = homeDAO.selectHomeInBoundsList(mapBounds);
		
		for(int i=0; i<homeInBoundsList.size(); i++) {
			int homeNum = homeInBoundsList.get(i).getHomeNum();
			homeInBoundsList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
			homeInBoundsList.get(i).setOptionList(homeDAO.selectHomeOptionList(homeNum));
		}
		
		return homeInBoundsList;
	}
	
	

	@Override
	public Map<String, Object> selectHomeDetail(int homeNum) {
		HomeDetailVO homeDetailVO = homeDAO.selectHomeDetail(homeNum);
		homeDetailVO.setOptionList(homeDAO.selectHomeOptionList(homeNum));
		homeDetailVO.setHomeImgList(homeDAO.selectHomeImgList(homeNum));
		
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
		home.put("roomCount", homeDetailVO.getRoomCount());
		home.put("parking", homeDetailVO.getParking());
		home.put("pet", homeDetailVO.getPet());
		home.put("elevator", homeDetailVO.getElevator());
		home.put("balcony", homeDetailVO.getBalcony());
		home.put("moveDate", homeDetailVO.getMoveDate());
		home.put("floor", homeDetailVO.getFloor());
		home.put("homeTitle", homeDetailVO.getHomeTitle());
		home.put("homeDetail", homeDetailVO.getHomeDetail());
		home.put("optionList", homeDetailVO.getOptionList());
		home.put("homeImgList", homeDetailVO.getHomeImgList());
		home.put("jgsName", homeDetailVO.getJgsName());
		home.put("jgsNum", homeDetailVO.getJgsNum());
		home.put("phone", homeDetailVO.getPhone());
		home.put("lessorName", homeDetailVO.getName());
		home.put("lessorAddr", homeDetailVO.getLessorAddr2() + " " + homeDetailVO.getLessorAddr3());
		// view로 반환할 때 보증금, 월세, 관리비는 돈 단위 계산해서
		home.put("deposit", homeDetailVO.getDeposit());
		home.put("monthly", homeDetailVO.getMonthly());
		home.put("adminCost",homeDetailVO.getAdminCost());
		home.put("lessorId", homeDetailVO.getLessorId());
		
		return home;
	}
	
	// 매물 상세 보기시 화면 단에서 가격 정보 단위 표시
	@Override
	public String convertMoneyUnit(long money) {
		money = money / 10000;
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
	public HomeImgVO previewHomeImg(int homeNum) {
		// TODO Auto-generated method stub
		return null;
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
		HomeVO homeVO = (HomeVO)modifyHome.get("homeVO");
		homeDAO.updateHome(homeVO);
		
		// 매물 사진 수정
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
		List<HomeOptionVO> homeOptionList = new ArrayList<>();
		if(homeOptionList != null) {
			homeDAO.deleteHomeOption(homeVO.getHomeNum());
			for(String op: (List<String>)modifyHome.get("homeOptionList")) {
				HomeOptionVO homeOptionVO = new HomeOptionVO();
				homeOptionVO.setHomeNum(homeVO.getHomeNum());
				homeOptionVO.setOptionName(op);
				homeOptionList.add(homeOptionVO);
			}
		}
		System.out.println(homeOptionList);
		homeDAO.insertHomeOptionList(homeOptionList);
		
		// 매물 가격 정보 수정
		HomePriceVO homePriceVO = (HomePriceVO)modifyHome.get("homePriceVO");
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