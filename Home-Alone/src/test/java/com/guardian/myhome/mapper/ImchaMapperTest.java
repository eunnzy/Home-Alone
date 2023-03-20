package com.guardian.myhome.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.guardian.myhome.vo.ImchaVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations ={ 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class ImchaMapperTest {

	@Autowired
	private ImchaMapper imchamapper;
	
	@Test
	public void memberJoin() throws Exception{
		ImchaVO imcha = new ImchaVO();
		
		imcha.setImchaId("test");
		imcha.setImchaPw("test");
		imcha.setNickname("test");
		imcha.setPhone("010-1111-1111");
		imcha.setUserRoll("test");
		imcha.setSido1("서울시");
		imcha.setGugun1("강남구");
		
		imchamapper.imchaJoin(imcha);
	}
	
	// @Test
	public void memberLogin() throws Exception {
		ImchaVO imcha = new ImchaVO();
		
		imcha.setImchaId("hong");
		imcha.setImchaPw("1234");
		
		imchamapper.imchaLogin(imcha);
		System.out.println("결과 값 : " + imchamapper.imchaLogin(imcha));
	}
}
