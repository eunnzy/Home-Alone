package com.guardian.myhome.mapper;

import java.util.List;

import com.guardian.myhome.vo.HomeOptionVO;

/*
	mybatis를 연결하기 위한 mapper
	매물 옵션 관련 mapper
*/
public interface HomeOptionMapper {
	int insertOption(HomeOptionVO homeOptionVO);
	List<String> findOptionByHomeNum(int homeNum);
}
