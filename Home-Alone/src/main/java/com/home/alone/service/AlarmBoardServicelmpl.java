package com.home.alone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.alone.dao.AlarmBoardDAO;
import com.home.alone.mapper.AlarmBoardAttachMapper;
import com.home.alone.mapper.AlarmBoardMapper;
import com.home.alone.vo.AlarmBoardAttachVO;
import com.home.alone.vo.AlarmBoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class AlarmBoardServicelmpl implements AlarmBoardService {
	
	
//	@Setter(onMethod_ = @Autowired)
//	private AlarmBoardMapper mapper;
//	
//	@Setter(onMethod_ = @Autowired)
//	private AlarmBoardAttachMapper attachMapper;

	@Autowired
	private AlarmBoardDAO alarmBoardDAO;
	
	// 목록
	@Override
	public List<AlarmBoardVO> getAlarmBoardList() {
		log.info("alarm Board");
		return alarmBoardDAO.selectAlarmBoardList();
	}
		
	// 등록 
	@Transactional
	@Override
	public void abregister(AlarmBoardVO board) {
		log.info("abregister........" + board);
		alarmBoardDAO.insertAlarmBoard(board);
//		mapper.insertalarmBoard(board);
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		board.getAttachList().forEach(attach -> {
			attach.setAno(board.getAno());
			log.info("Alarm Board Attach insert Before");
			alarmBoardDAO.insertAlarmAttach(attach);
		//	attachMapper.abinsert(attach);
			log.info("Alarm Board Attach insert After");
		});
	}

	// 조회
	@Override
	public AlarmBoardVO abget(Long ano) {
		log.info("get........" + ano);
		return alarmBoardDAO.selectAlarmBoardDetail(ano);
	//	return mapper.alarmBoardread(ano);
	}

	// 삭제
	@Transactional
	@Override
	public boolean abremove(Long ano) {
		log.info("abremove........" + ano);
		alarmBoardDAO.deleteAlarmAttachAll(ano);
//		attachMapper.abdeleteAll(ano);
//		return mapper.alarmBoarddelete(ano) == 1;
		return alarmBoardDAO.deleteAlarmBoard(ano) == 1;
	}

	// 수정
	@Transactional
	@Override
	public boolean abmodify(AlarmBoardVO board) {
		log.info("abmodify........" + board);
		// attachMapper.abdeleteAll(board.getAno());
		alarmBoardDAO.deleteAlarmAttachAll(board.getAno());
// 		boolean modifyResult = mapper.alarmBoardupdate(board) == 1;
	
		boolean modifyResult = alarmBoardDAO.updateAlarmBoard(board) == 1;

		if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setAno(board.getAno());
				alarmBoardDAO.insertAlarmBoard(board);
				// attachMapper.abinsert(attach);
			});
		}
		return modifyResult;
	}
	
	// 파일 업로드
	@Override
	public List<AlarmBoardAttachVO> getabAttachList(Long ano) {
		log.info("get ab Attach List by ano" + ano);
		return alarmBoardDAO.selectAlarmAttachList(ano);
		
//		return attachMapper.abfindByBno(ano);
	}
}
