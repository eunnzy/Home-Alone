package com.guardian.myhome.mapper;

import java.util.List;

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
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	// 전국 목록 리스트
	// @Test
//	public void testGetList() {
//		Criteria cri = new Criteria();
//		mapper.getListWithPaging(cri).forEach(board -> log.info(board));
//	}
//	
//	// 페이징 테스트 
//	 @Test
//	public void testPaging() {
//		Criteria cri = new Criteria();
//		List<BoardVO> list = mapper.getListWithPaging(cri);
//		list.forEach(board -> log.info(board));
//	}
	
	// INSERT처리도 되고 생성된 PK값을 알아야 하는 경우
	// @Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 추가하는 제목 SelectKey");
		board.setContent("새로 작성하는 내용 SelectKey");
		board.setCategory("동네맛집");
		board.setImchaid("cha_user2");
			
		mapper.insertSelectKey(board);
			
		// Lombok이 만들어주는 toString()를 이용해 bno멤버변수의 값을 알아보기 위함 -> bno값이 알맞게 생성(지정)되어 처리됨 
		log.info(board);
		}
	
	// 조회
	// @Test
	public void testRead() {
		BoardVO board = mapper.read(10L);
			
		log.info(board);
	}
	
	// 삭제 
	// @Test
	public void testDelete() {
		log.info("DELECT COUNT : " + mapper.delete(9L));
	}
	
	// 수정
	// @Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		
		vo.setBno(10L);
		vo.setTitle("수정된 제목");
		vo.setContent("수정된 내용");
		vo.setCategory("도와줘요");
		
		mapper.update(vo);
		log.info(vo);
	}
	
	// 조회수 증가 
	// @Test
	public void testViewsUp() {
		int count = mapper.viewsUp(1L);
		log.info("VIEW COUNT : " + count);
	}


}
