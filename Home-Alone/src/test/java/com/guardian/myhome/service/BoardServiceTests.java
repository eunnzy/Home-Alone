package com.guardian.myhome.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guardian.myhome.vo.BoardVO;
import com.guardian.myhome.vo.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	// service객체가 정상적으로 생성되고 mapper객체가 잘 주입되었는지 확인하는 테스트 
	// @Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	// 등록 
	// @Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setCategory("취미생활");
		board.setImchaid("Songs");
		
		service.register(board);
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}
	
	// 조회 
	// @Test
	public void testGet() {
		log.info(service.get(1L));
	}
	
	// 수정 
	@Test
	public void testUpdate() {
		BoardVO board = service.get(10L);
		if(board == null) {
			return;
		}
		
		board.setTitle("뭐야 ");
		log.info("MODIFY : " + service.modify(board));
	}
	
	// 삭제 
	// @Test
	public void testDelect() {
		log.info("REMOVE RESULT : " + service.remove(5L));
	}
	
	// 전국 목록 
	// @Test
//	public void testGetAllList() {
//		Criteria cri = new Criteria();
//		service.getList(cri).forEach(board -> log.info(board));
//	}
	
	// 조회수 
	// @Test
	public void testViewsUp() {
		log.info("ViewsUP RESULR : " + service.viewsUp(1L));
	}

}
