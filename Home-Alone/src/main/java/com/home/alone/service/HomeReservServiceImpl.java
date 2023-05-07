package com.home.alone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.dao.HomeDAO;
import com.home.alone.dao.HomeReservDAO;
import com.home.alone.vo.HomeReservPreviewVO;
import com.home.alone.vo.HomeReservVO;

@Service
public class HomeReservServiceImpl implements HomeReservService {
	@Autowired
	private HomeReservDAO homeReservDAO;

	@Autowired
	private HomeDAO homeDAO;
	
	@Override
	public int reservHome(HomeReservVO homeReservVO) {	// 방문 예약
		return homeReservDAO.insertHomeReserv(homeReservVO);
	}

	@Override
	public List<String> getInvalidTimeList(HomeReservVO homeReservVO) {
		return homeReservDAO.selectHomeReservInvalidTimeList(homeReservVO);
	}

	@Override
	public List<HomeReservPreviewVO> getReservListByImcha(String imchaId) {
		List<HomeReservPreviewVO> homeList = homeReservDAO.selectHomeReservListByImchaId(imchaId);
		
		for(int i=0; i<homeList.size(); i++) {
			int homeNum = homeList.get(i).getHomeNum();
			homeList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
		}
		
		return homeList;
	}

	@Override
	public List<HomeReservPreviewVO> getReservListByLessor(String lessorId) {
		List<HomeReservPreviewVO> homeList = homeReservDAO.selectHomeReservListByLessorId(lessorId);
		
		for(int i=0; i<homeList.size(); i++) {
			int homeNum = homeList.get(i).getHomeNum();
			homeList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
		}
		
		return homeList;
	}

	public int getHomeReservCount(String imchaId) {
		return homeReservDAO.selectHomeReservCountImcha(imchaId);
	}
	
	@Override
	public int homeReservCancel(int revNum) {
		return homeReservDAO.updateHomeReservCancel(revNum);
	}

	@Override
	public int homeReservAccept(int revNum) {
		return homeReservDAO.updateHomeReservAccept(revNum);
	}

	
	@Override
	public int homeReservReject(int revNum) {
		return homeReservDAO.updateHomeReservReject(revNum);
	}

	@Override
	public int deleteReserv(int homeNum) {
		return homeReservDAO.deleteHomeReserv(homeNum);
	}

	
}
