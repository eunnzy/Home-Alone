package com.home.alone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.alone.dao.HomeDAO;
import com.home.alone.dao.HomeLikeDAO;
import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeLikeVO;
import com.home.alone.vo.HomeOverviewVO;

import lombok.RequiredArgsConstructor;

@Service
public class HomeLikeServiceImpl implements HomeLikeService {
	@Autowired 
	private HomeLikeDAO homeLikeDAO;
	@Autowired
	private HomeDAO homeDAO;
	@Autowired
	private HomeService homeService;
	
	@Override	
	public int insetLike(HomeLikeVO homeLikeVO) {	// 찜 목록에 추가 
		return homeLikeDAO.insertHomeLike(homeLikeVO);
	}
	
	@Transactional
	@Override
	public List<HomeOverviewVO> getLikeByImchaId(String imchaId) {	// 찜 목록 리스트
		List<HomeOverviewVO> likeList = homeLikeDAO.selectHomeLikeList(imchaId);
		
		for(int i=0; i<likeList.size(); i++) {
			int homeNum = likeList.get(i).getHomeNum();
			int deposit = likeList.get(i).getDeposit();
			int monthly = likeList.get(i).getMonthly();
					
			likeList.get(i).setHomeImg(homeDAO.selectPreviewHomeImg(homeNum));
			likeList.get(i).setDepositUnit(homeService.convertMoneyUnit(deposit));
			likeList.get(i).setMonthlyUnit(homeService.convertMoneyUnit(monthly));
		}
		
		return likeList;
	}

	@Override
	public int remove(HomeLikeVO homeLikeVO) {
		return homeLikeDAO.deleteHomeLike(homeLikeVO);
	}
	
	@Override
	public int checkLike(HomeLikeVO homeLikeVO){
		return homeLikeDAO.selectHomeLikeCheck(homeLikeVO);
	}

	@Override
	public HomeDetailVO getLikeDetail(HomeLikeVO homeLikeVO) {
		// TODO Auto-generated method stub
		return null;
	}


}
