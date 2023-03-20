package com.guardian.myhome.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	// 가짜 MVC에 생성해둔 WebApplicationContext를 설정하고 만들어 내는 메소드 
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	// 같은 동네 목록 
//	//@Test
//	public void testList() throws Exception {
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/community/list")).andReturn().getModelAndView().getModelMap());
//	}
	
	// 전국 목록 
	//@Test
	public void testAllList() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/community/allList")).andReturn().getModelAndView().getModelMap());
	}
	
	// 카테고리 별 
	//@Test
	public void testCategoryList() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/community/categoryList").param("category", "도와줘요")).andReturn().getModelAndView().getModelMap());
	}
	
	// 등록  
	// @Test
	public void testRegister() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/community/insertBoard.do").param("title", "컨트롤러 새글 제목")
				.param("content", "컨트롤러 새글 내용").param("category", "도와줘요").param("imchaid", "Songs"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
	
	// 조회수가 올라가는 조회처리 
	// @Test
	public void testGet() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/community/get").param("bno", "10")).andReturn().getModelAndView().getModelMap());
	}
	
	// 수정
	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/community/updateBoard.do")
				.param("bno", "24").param("title", "컨트롤러에서 수정한 제목").param("content", "컨트롤러에서 수정한 내용")
				.param("category", "Songss")).andReturn().getModelAndView().getViewName();
			
		log.info(resultPage);
	}
	
	// 삭제 
	// @Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/community/delete.do").param("bno", "13"))
				.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
		
	
}
