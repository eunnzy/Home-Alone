package com.guardian.myhome.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.guardian.myhome.vo.HomeImgVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations ={ 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class HomeImgMapperTest {
	@Autowired
	private HomeMapper homeMapper;
	@Autowired
	
	
	
	public void homeImg() throws Exception {
		
		List<HomeImgVO> list = new ArrayList<>();
		for(int i=0; i<3; i++) {
			HomeImgVO homeImg = new HomeImgVO();
			homeImg.setHomeImgName(i+"이름");
			homeImg.setHomeImgPath(i+"path");
			homeImg.setHomeNum(0);
			list.add(homeImg);
		}
			
		homeMapper.insertHomeImgList(list);
//		HomeImgVO homeImg = homeImgMapper.previewHomeImg(1);
//		System.out.println(homeImg);
	}
	
	@Test
	public void homePreview() throws Exception {
		int homeNum = 2;
		System.out.println(homeMapper.selectHomeImgList(homeNum));
	}
}
