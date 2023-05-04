package com.home.alone.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.dao.HomeDAO;
import com.home.alone.dao.HomeInquryDAO;
import com.home.alone.vo.HomeAddInfoVO;
import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeImgVO;
import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;
import com.home.alone.vo.HomeOptionVO;
import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomePriceVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeReservPreviewVO;
import com.home.alone.vo.HomeReservVO;
import com.home.alone.vo.HomeVO;
import com.home.alone.vo.LessorVO;

@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	private HomeDAO homeDAO;
	
	@Autowired
	private HomeInquryDAO homeInquryDAO;
	
	@Override
	public int registerHome(Map<String, Object> insertMap) {	// 매물 등록
		System.out.println("\n============ HomeService insertHome 실행 =============\n");
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
	public List<HomePreviewVO> homeInBoundsList(Map<String, Object> mapBounds) {
		List<HomePreviewVO> homeInBoundsList = null;
		homeInBoundsList = homeDAO.selectHomeInBoundsList(mapBounds);
		
		for(int i=0; i<homeInBoundsList.size(); i++) {
			int homeNum = homeInBoundsList.get(i).getHomeNum();
			HomeOptionVO homeOptionVO = homeDAO.selectHomeOption(homeNum);	// 매물 옵션 정보 저장
			homeInBoundsList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));	// 매물 사진 미리보기 저장
		}
		return homeInBoundsList;
	}
	
	// 매물 필터 검색
	@Override
	public List<HomePreviewVO> homeListByFilter(Map<String, Object> filterData) {	// 매물 필터 검색
		List<HomePreviewVO> homeList = null;
		homeList = homeDAO.selectHomeListByFilter(filterData);
		
		
		for(int i=0; i<homeList.size(); i++) {
			int homeNum = homeList.get(i).getHomeNum();
			
			HomeOptionVO homeOptionVO = homeDAO.selectHomeOption(homeNum);
			homeList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
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
//		
//		for(HomeImgVO homeImgVO: homeDetailVO.getHomeImgList()) 
//		{
//			String changePath = changeHomeImgPath(homeImgVO.getHomeImgPath());
//			homeImgVO.setHomeImgPath(changePath);
//		}
		
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
	public List<HomePreviewVO> getListByLessorId(LessorVO vo) {
		System.out.println(vo);
		List<HomePreviewVO> homeList = null;
		homeList = homeDAO.getListByLessorId(vo);
		
		// 미리보기 사진 가져오기
		for(int i=0; i<homeList.size(); i++) {
			int homeNum = homeList.get(i).getHomeNum();
			homeList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
		}
		
		return homeList;
	}

	@Override
	public int deleteHome(int homeNum) {	// 매물 삭제
		return homeDAO.deleteHome(homeNum);
	}

	@Override
	public int reservHome(HomeReservVO homeReservVO) {	// 매물 예약
		return homeDAO.insertHomeReserv(homeReservVO);
	}

	@Override
	public List<String> getValidTimeList(HomeReservVO homeReservVO) {	// 이미 예약된 매물 시간대 리스트 
		return homeDAO.selectHomeReservValidTimeList(homeReservVO);
	}

	@Override
	public int inquryHome(HomeInquryVO homeInquryVO) {	// 매물 문의
		return homeInquryDAO.insertHomeInqury(homeInquryVO);
	}

	@Override
	public List<HomeInquryVO> getHomeInqListByImcha(String imchaId) {	// 매물 문의 목록(일반회원)
		return homeInquryDAO.selectInquryListByImcha(imchaId);
	}

	@Override
	public List<HomeInquryVO> getHomeInqListByLessor(String lessorId) {	// 매물 문의 목록(중개인)
		return homeInquryDAO.selectInquryListByLeessor(lessorId);
	}

	@Override
	public HomeInquryDetailVO getHomeInqDetail(int iqNum) {	// 문의 상세보기
		HomeInquryDetailVO homeInqDetail = homeInquryDAO.selectHomeInquryDetail(iqNum);
		homeInqDetail.setHomeImg(homeDAO.selectPreviewHomeImg(homeInqDetail.getHomeNum()));
		return homeInqDetail;
	}

	@Override
	public List<HomeInqAnswerVO> getHomeInqAnswerList(int iqNum) {	// 문의 답변 목록
		return homeInquryDAO.selectInqAnswerList(iqNum);
	}

	@Override
	public int registerHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO) {		// 문의 답변 등록
		int result = homeInquryDAO.insertHomeInqAnswer(homeInqAnswerVO);
		System.out.println(result);
		
		if(result == 1) {
			return homeInquryDAO.updateHomeInqAnsCom(homeInqAnswerVO.getIqNum());	// 답변 상태 변경
		}
		return 0;
	}
	
	@Override
	public int homeAnsIdCheck(String lessorId) {	// 문의 답변 상태
		return homeInquryDAO.selectInqAnswerIdCheck(lessorId);
	}

	@Override
	public int modifyHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO) {	
		return homeInquryDAO.updateHomeInqAnswer(homeInqAnswerVO);
	}

	// 일반 회원이 예약한 매물 목록
	@Override
	public List<HomeReservPreviewVO> getReservListByImcha(String imchaId) {	// 예약 목록 (일반회원)
		List<HomeReservPreviewVO> reservList = null;
		reservList = homeDAO.selectHomeReservListByImchaId(imchaId);
		
		for(int i=0; i<reservList.size(); i++) {
			int homeNum = reservList.get(i).getHomeNum();
			reservList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
		}
		
		return reservList;
	}

	// 예약 신청 목록
	@Override
	public List<HomeReservPreviewVO> getReservListByLessor(String lessorId) {	// 예약 목록(임대인)
		List<HomeReservPreviewVO> reservList = null;
		reservList = homeDAO.selectHomeReservListByLessorId(lessorId);
		
		
		for(int i=0; i<reservList.size(); i++) {
			int homeNum = reservList.get(i).getHomeNum();
			reservList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
		}
		
		return reservList;
	}

	// 문의 답변 내용
	@Override
	public HomeInqAnswerVO getHomeInqAnswer(int ansNum) {
		return homeInquryDAO.selectHomeInqAnswer(ansNum);
	}

	// 답변 삭제
	@Override
	public int deleteHomeInqAnswer(int ansNum) {
		HomeInqAnswerVO homeInqAnsVO = homeInquryDAO.selectHomeInqAnswer(ansNum);	// 문의 답변 번호에 해당하는 정보 가져오기
		homeInquryDAO.updateHomeInqAnsWait(homeInqAnsVO.getIqNum());	// 답변 상태를 대기중으로 수정하고
		return homeInquryDAO.deleteHomeInqAnswer(ansNum); // 답변 삭제
		
	}

	
	@Override
	public int modifyHomeInqAnsStatusCom(int iqNum) {
		return homeInquryDAO.updateHomeInqAnsCom(iqNum);
	}
	
	@Override
	public int modifyHomeInqAnsStatusWait(int iqNum) {
		return homeInquryDAO.updateHomeInqAnsWait(iqNum);
	}

	@Override
	public int homeReservReject(int revNum) {
		return homeDAO.updateHomeReservReject(revNum);
	}

//	@Override
//	public String changeHomeImgPath(String homeImgPath) {
//		return homeImgPath.replace("\\", "//");
//	}


	
}