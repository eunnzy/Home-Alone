package com.guardian.myhome.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.guardian.myhome.vo.Criteria;
import com.guardian.myhome.vo.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	// 게시물 배열 : board테이블에 있는지 확인하고 작성 
	public Long[] bnoArr = { 110L, 111L, 112L, 113L, 114L };
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	// 객체 생성 확인
	//@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	// 등록 
	//@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(bnoArr[ i % 5 ]);
			vo.setReply("댓글테스트" + i);
			vo.setReplyer("replyser" + i);
			
			mapper.insert(vo);
		});
	}
	
	// 특정 댓글 조회 
	//@Test
	public void testRead() {
		Long targetRno = 3L;
		ReplyVO vo = mapper.read(targetRno);
		log.info(vo);
	}
	
	// 삭제
	//@Test
	public void testDelete() {
		Long targetRno = 1L;
		mapper.delete(targetRno);
	}
	
	// 수정
	//@Test
	public void testUpdate() {
		Long targetRno = 3L;
		ReplyVO vo = mapper.read(targetRno);
		vo.setReply("Update Reply");
		int count = mapper.update(vo);
		log.info("UPDATE COUNT : " + count);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
	
}
