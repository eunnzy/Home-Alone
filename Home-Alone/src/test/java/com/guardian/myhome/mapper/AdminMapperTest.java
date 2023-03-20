package com.guardian.myhome.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.guardian.myhome.service.AdminService;
import com.guardian.myhome.vo.AdminVO;
import com.guardian.myhome.vo.ImchaVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations ={ 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class AdminMapperTest {

	@Autowired
	private AdminMapper adminmapper;
	
	//@Test
	public void adminJoin() throws Exception{
		AdminVO admin = new AdminVO();
		
		admin.setAdminId("test");
		admin.setAdminPw("test");
		admin.setAdminName("test");
		admin.setAdminTel("010-0000-0000");

		
		adminmapper.adminJoin(admin);
	}
	
	//@Testz
	public void adminLogin() throws Exception {
		AdminVO admin = new AdminVO();
		
		admin.setAdminId("1111");
		admin.setAdminPw("1234");
		
		adminmapper.adminLogin(admin);
		System.out.println("결과 값 : " + adminmapper.adminLogin(admin));
	}
	

	

}
