package com.guardian.myhome.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.guardian.myhome.service.HomeDAO;
import com.guardian.myhome.vo.HomePriceVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations ={ 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class HomeOptionTest {
	@Autowired
	private HomeMapper homeOptionMapper;
	
	
	public void price() throws Exception {
		HomePriceVO priceVO = new HomePriceVO();
		
		priceVO.setAdminCost(100);
		priceVO.setDeposit(100);
		priceVO.setHomeNum(1);
		priceVO.setMonthly(100);
		
//		homeOptionMapper.insertHomePrice(priceVO);
	}
	
//	@Test
//	public void addOption() throws Exception {
//		int homeNum = 1;
//		String[] optionList = {"냉장고", "TV", "세탁기"};
//		
//		List<HomeOptionVO> list = new ArrayList<>();
//		for(String op: optionList) {
//			HomeOptionVO option = new HomeOptionVO();
//			option.setHomeNum(homeNum);
//			option.setOptionName(op);
//			list.add(option);
//		}
//		homeOptionMapper.insertHomeOptionList(list);
//		
////		System.out.println(option);
//
//		
//			
	
//	}
	
	@Test
	public void selectOption() throws Exception {
		List<String> optionList = new ArrayList<>(); 
		optionList.add("냉장고");
		optionList.add("옷장");
		optionList.add("비데");
		optionList.add("에어컨");
		
	}
}
