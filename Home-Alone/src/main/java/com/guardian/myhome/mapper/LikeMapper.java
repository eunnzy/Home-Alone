package com.guardian.myhome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.guardian.myhome.vo.HomeDetailVO;
import com.guardian.myhome.vo.HomeVO;
import com.guardian.myhome.vo.LikeVO;

@Repository
public interface LikeMapper {

		// 찜하기 ( 추가 )
		public int insertLike(LikeVO vo);
		// 찜 목록 확인
		public List<LikeVO> getLikeByImchaId(String imchaId);
		// 찜 목록 1개 삭제
		public boolean delete(@Param("homeNum")int homeNum, @Param("imchaId")String imchaId);
		// 게시물삭제시 전체삭제
		public boolean deleteAllByHomeNum(int homeNum);
		// 이미 찜한 게시물인지 조회
//		public LikeVO getByLikeNumWithImchaId(@Param("vo")LikeVO vo);
		
		
		public int checkLike(LikeVO vo);
		
		public HomeDetailVO getLikeDetail(LikeVO vo);
}
