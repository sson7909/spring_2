package com.sson.notice;

import java.util.List;

import org.springframework.ui.Model;

import com.sson.board.BoardDTO;
import com.sson.board.BoardService;
import com.sson.util.ListData;
import com.sson.util.Pager;
import com.sson.util.RowNum;

public class NoticeService implements BoardService {

	private NoticeDAO noticeDAO;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}


	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		
		return noticeDAO.insert(boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		 noticeDAO.hit(num);
		 
		return noticeDAO.selectOne(num);
	}

	@Override
	public List<BoardDTO> selectList(ListData listData,Model model) throws Exception {
		RowNum rowNum = listData.makeRow();
		int totalCount = noticeDAO.getTotalCount(rowNum);
		Pager pager = listData.makePage(totalCount);
		
		model.addAttribute("dto", noticeDAO.selectList(rowNum));
		model.addAttribute("pager", pager);
		
		return noticeDAO.selectList(rowNum);
	}

}
