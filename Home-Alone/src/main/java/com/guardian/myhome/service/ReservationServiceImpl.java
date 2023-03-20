package com.guardian.myhome.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guardian.myhome.mapper.RevMapper;
import com.guardian.myhome.vo.ReservationVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	RevMapper revMapper;
	
	
	@Override
	public void insertRev(ReservationVO vo) {
		revMapper.insertRev(vo);
	}

	@Override
	public List<ReservationVO> getRevByImchaId(String imchaId) {
		
		return revMapper.getRevByImchaId(imchaId);
	}

	@Override
	public void delete(@Param("revNum")int revNum, @Param("imchaId")String imchaId) {
		revMapper.delete(revNum, imchaId);
	}
	
	
	@Override
	public List<ReservationVO> getRevByLessor(String lessorId) {
		return revMapper.getRevByLessor(lessorId);
	}
	
	@Override
	public void changeRevState(@Param("homeNum")int homeNum, @Param("imchaId")String imchaId, @Param("revNum")int revNum) {
		revMapper.changeRevState(homeNum, imchaId, revNum);
	}
	
	
	@Override
	public void reject(@Param("revNum")int revNum, @Param("imchaId")String imchaId, @Param("homeNum")int homeNum) {
		revMapper.reject(revNum, imchaId, homeNum);
	}
	
	@Override
	public List<ReservationVO> invaildDate(String revDate) {
		// TODO Auto-generated method stub
		return revMapper.invaildDate(revDate);
	}
	
}
