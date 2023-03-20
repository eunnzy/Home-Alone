package com.guardian.myhome.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guardian.myhome.service.BoardService;
import com.guardian.myhome.service.ReplyService;
import com.guardian.myhome.vo.BoardLikesVO;
import com.guardian.myhome.vo.Criteria;
import com.guardian.myhome.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	private BoardService boardservice;
	
	// 등록 
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		log.info("ReplyVO : " + vo);
		int insertCount = service.register(vo);
		log.info("result : " + insertCount);
		boardservice.replysUp(vo.getBno());
		log.info("replysUp : " + vo.getBno());
		
		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 목록 
	@GetMapping(value = "/pages/{bno}/{page}", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
		log.info("get List.........");
		Criteria cri = new Criteria(page, 10);
		log.info(cri);
		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
	}
	
	// 조회
	@GetMapping(value = "/{rno}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		log.info("get : " + rno);
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	// 삭제 
	@DeleteMapping(value="/{rno}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("remove : " + rno);
		ReplyVO vo = service.get(rno);
		boardservice.replysDown(vo.getBno());
		log.info("replysDown : " + vo.getBno());
		return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 수정
//	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value="/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
//	public ResponseEntity<String> modify (@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {
//		vo.setRno(rno);
//		log.info("rno : " + rno);
//		log.info("modify : " + vo);
//		return service.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	// 좋아요
	@PostMapping(value = "/likeUp", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> likeUp(@RequestBody BoardLikesVO likevo) {
		log.info("좋아요");
		boardservice.likesOn(likevo.getBno(), likevo.getUserid());
		return boardservice.likesUp(likevo.getBno()) ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//좋아요 취소 
	@PostMapping(value = "/likeDown", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> likeDown(@RequestBody BoardLikesVO likevo) {
		log.info("좋아요 취소");
		boardservice.likesOff(likevo.getBno(), likevo.getUserid());
		return boardservice.likesDown(likevo.getBno()) ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
