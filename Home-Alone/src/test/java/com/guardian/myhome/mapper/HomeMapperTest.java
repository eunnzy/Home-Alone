package com.guardian.myhome.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.guardian.myhome.vo.HomeDetailVO;
import com.guardian.myhome.vo.HomePreviewVO;
import com.guardian.myhome.vo.HomeVO;

// home db 연결 테스트
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations ={ 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class HomeMapperTest {
	@Autowired
	private HomeMapper homeMapper;
	@Autowired
	private HomeImgMapper homeImgMapper;
	
	public void addHome() throws Exception {
		HomeVO homeVO = new HomeVO();
		
		homeVO.setHomeType("원룸");
		homeVO.setAddr1("12345");
		homeVO.setAddr2("서울시 강남구");
		homeVO.setAddr3("kh");
		homeVO.setLatitude(37.4989966363357);
		homeVO.setLongitude(127.032848249971);
		homeVO.setHomeArea(32);
		homeVO.setRentType("월세");
//		homeVO.setDeposit(500);
//		homeVO.setMonthly(50);
		homeVO.setRentPeriods(1);
		homeVO.setRoomCount(2);
//		homeVO.setAdminCost(0);
		homeVO.setParking(1);
		homeVO.setPet("가능");
		homeVO.setElevator("가능");
		homeVO.setBalcony("가능");
		homeVO.setMoveDate(new Date());
		homeVO.setFloor(3);
		homeVO.setHomeTitle("첫번째 테스트");
		homeVO.setHomeDetail("첫번째 테스트 상세글");
		
		int result = homeMapper.insertHome(homeVO);
		System.out.println(result );
	}

	
	
	@Test
	public void detailHome() throws Exception {
		HomeDetailVO homeDetailVO = homeMapper.selectHomeDetail(7);
		System.out.println(homeDetailVO);
	}
	
	
	
}
