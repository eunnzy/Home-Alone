package com.home.alone.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.vo.HomeReservVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

//	@Autowired
//	RevMapper revMapper;
//	
//	
//	@Override
//	public void insertRev(HomeReservVO vo) {
//		revMapper.insertRev(vo);
//	}
//
//	@Override
//	public List<HomeReservVO> getRevByImchaId(String imchaId) {
//		
//		return revMapper.getRevByImchaId(imchaId);
//	}
//
//	@Override
//	public void delete(@Param("revNum")int revNum, @Param("imchaId")String imchaId) {
//		revMapper.delete(revNum, imchaId);
//	}
//	
//	
//	@Override
//	public List<HomeReservVO> getRevByLessor(String lessorId) {
//		return revMapper.getRevByLessor(lessorId);
//	}
//	
//	@Override
//	public void changeRevState(@Param("homeNum")int homeNum, @Param("imchaId")String imchaId, @Param("revNum")int revNum) {
//		revMapper.changeRevState(homeNum, imchaId, revNum);
//	}
//	
//	
//	@Override
//	public void reject(@Param("revNum")int revNum, @Param("imchaId")String imchaId, @Param("homeNum")int homeNum) {
//		revMapper.reject(revNum, imchaId, homeNum);
//	}
//	
//	@Override
//	public List<HomeReservVO> invaildDate(String revDate) {
//		// TODO Auto-generated method stub
//		return revMapper.invaildDate(revDate);
//	}
	
}
