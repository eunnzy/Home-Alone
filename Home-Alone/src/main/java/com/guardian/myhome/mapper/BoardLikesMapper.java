package com.guardian.myhome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guardian.myhome.vo.BoardLikesVO;

public interface BoardLikesMapper {
	
	// 좋아요 눌렀는지 체크
	public int likeCheck(@Param("bno") Long bno, @Param("userid") String userid);
	
	// 좋아요 ON
	public void likesOn(@Param("bno") Long bno, @Param("userid") String userid);
	
	// 좋아요 OFF
	public void likesOff(@Param("bno") Long bno, @Param("userid") String userid);
}
