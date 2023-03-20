package com.guardian.myhome.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.guardian.myhome.vo.LessorVO;
import com.guardian.myhome.service.LessorService;
import com.guardian.myhome.vo.LessorImgVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations ={ 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class LessorMapperTest {

	@Autowired
	private LessorMapper lessormapper;
	
	@Autowired
	private LessorService lessorservice;
	
	// 회원가입 테스트
	public void lessorJoin() throws Exception{
		LessorVO lessor = new LessorVO();
		
		lessor.setLessorId("test");
		lessor.setLessorPw("test");
		lessor.setLessorNickName("test");
		lessor.setPhone("010-0000-0000");
		lessor.setName("test");
		lessor.setBirthDate("1990-01-01");
		lessor.setJgsName("test");
		lessor.setJgsNum("0010011");
		lessor.setUserRoll("중개인");
		lessor.setLessorAddr1("test");
		lessor.setLessorAddr2("test");
		lessor.setLessorAddr3("test");
		
		lessormapper.lessorJoin(lessor);
	}
	
	//@Test
	public void lessorLogin() throws Exception{
		
		LessorVO lessor = new LessorVO();
		
		lessor.setLessorId("lee");
		lessor.setLessorPw("1234");
		
		lessormapper.lessorLogin(lessor);
		System.out.println("결과 값 : " + lessormapper.lessorLogin(lessor));
	}
	
	//@Test
	public void successId() throws Exception{
		
		lessormapper.successId("lee");

	}
	
	//@Test
	public void imageEnrollTest() {
		
		LessorImgVO vo = new LessorImgVO();
		
		vo.setLessorId("lee");
		vo.setFileName("test");
		vo.setUploadPath("test");
		vo.setUuid("test2");
		
		//lessormapper.imgEnroll(lessorImg);
	}
	
	@Test
	private void lessorImgEnrollTest() throws Exception {
		LessorVO lessor = new LessorVO();
		LessorImgVO vo = new LessorImgVO();
		
		lessor.setLessorId("test");
		lessor.setLessorPw("test");
		lessor.setLessorNickName("test");
		lessor.setPhone("010-0000-0000");
		lessor.setName("test");
		lessor.setBirthDate("1990-01-01");
		lessor.setJgsName("test");
		lessor.setJgsNum("0010011");
		lessor.setUserRoll("중개인");
		lessor.setLessorAddr1("test");
		lessor.setLessorAddr2("test");
		lessor.setLessorAddr3("test");
		
		List<LessorImgVO> imageList = new ArrayList<LessorImgVO>();
		
		LessorImgVO image1 = new LessorImgVO();
		LessorImgVO image2 = new LessorImgVO();
		
		image1.setFileName("test1");
		image1.setUploadPath("test");
		image1.setUuid("test1");
		
		image2.setFileName("test2");
		image2.setUploadPath("test2");
		image2.setUuid("test2");
		
		imageList.add(image1);
		imageList.add(image2);
		
//		lessor.setImageList(imageList);
		
		lessorservice.imgEnroll(vo);
	}
	
}
