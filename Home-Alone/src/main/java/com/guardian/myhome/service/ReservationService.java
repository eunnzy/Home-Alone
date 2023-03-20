package com.guardian.myhome.service;

import java.util.List;

import com.guardian.myhome.vo.ReservationVO;

public interface ReservationService {
	
	public void insertRev(ReservationVO vo);
	public List<ReservationVO> getRevByImchaId(String imchaId);
	public void delete(int revNum, String imchaId);
	public List<ReservationVO> getRevByLessor(String lessorId);
	public void reject(int revNum, String imchaId, int homeNum);
	public void changeRevState(int homeNum, String imchaId, int revNum);
	public List<ReservationVO> invaildDate(String revDate);
	
}
