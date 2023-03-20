package com.guardian.myhome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guardian.myhome.mapper.HomeMapper;
import com.guardian.myhome.mapper.LikeMapper;
import com.guardian.myhome.vo.HomeDetailVO;
import com.guardian.myhome.vo.HomeVO;
import com.guardian.myhome.vo.LikeVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	LikeMapper likeMapper;
	
	HomeMapper homeMapper;
	
	
	
	@Override
	public void insetLike(LikeVO vo) {
		
		 likeMapper.insertLike(vo);
	}
	
	@Transactional
	@Override
	public List<LikeVO> getLikeByImchaId(String imchaId) {
		return likeMapper.getLikeByImchaId(imchaId);
	}

	@Override
	public void remove(int homeNum, String imchaId) {
		likeMapper.delete(homeNum, imchaId);
	}
	
	@Override
	public int checkLike(LikeVO vo){
		return likeMapper.checkLike(vo);
	}

	@Override
	public HomeDetailVO getLikeDetail(LikeVO vo) {
		return likeMapper.getLikeDetail(vo);
	}

//	@Override
//	public LikeVO getByLikeNumWithImchaId(LikeVO vo) {
//		return null;
//	}

}
