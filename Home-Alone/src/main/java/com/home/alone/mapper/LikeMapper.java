package com.home.alone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeLikeVO;
import com.home.alone.vo.HomeVO;

//@Repository
public interface LikeMapper {

		// 찜하기 ( 추가 )
		public int insertLike(HomeLikeVO vo);
		// 찜 목록 확인
		public List<HomeLikeVO> getLikeByImchaId(String imchaId);
		// 찜 목록 1개 삭제
		public boolean delete(@Param("homeNum")int homeNum, @Param("imchaId")String imchaId);
		// 게시물삭제시 전체삭제
		public boolean deleteAllByHomeNum(int homeNum);
		// 이미 찜한 게시물인지 조회
//		public LikeVO getByLikeNumWithImchaId(@Param("vo")LikeVO vo);
		
		
		public int checkLike(HomeLikeVO vo);
		
		public HomeDetailVO getLikeDetail(HomeLikeVO vo);
}
