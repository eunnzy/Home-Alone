package com.guardian.myhome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guardian.myhome.vo.ReservationVO;

public interface RevMapper {
	
	public void insertRev(ReservationVO vo);
	public List<ReservationVO> getRevByImchaId(String imchaId);
	public void delete(@Param("revNum")int revNum, @Param("imchaId")String imchaId);
	public List<ReservationVO> getRevByLessor(String lessorId);
	public void reject(@Param("revNum")int revNum, @Param("imchaId")String imchaId, @Param("homeNum")int homeNum);
	public void changeRevState(@Param("homeNum")int homeNum, @Param("imchaId")String imchaId, @Param("revNum")int revNum);
	public List<ReservationVO> invaildDate(String revDate);
	
}
