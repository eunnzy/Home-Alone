package com.guardian.myhome.mapper;

import java.util.List;

import com.guardian.myhome.vo.HomeImgVO;

/*
	mybatis를 연결하기 위한 mapper
	매물 관련 mapper
*/
public interface HomeImgMapper {
	int insertHomeImg(HomeImgVO homeImgVO);	// 매물 사진 등록
	void deleteHomeImg(int homeNum);	// 매물 사진 삭제 (사진은 수정 개념 x)
	HomeImgVO previewHomeImg(int homeNum);	// 매물 미리보기시 나올 
	List<HomeImgVO> findHomeImgByHomeNum(int homeNum);	// 매물 번호로 매물에 대한 이미지 파일 정보
}
