package com.sson.qna;

import java.util.List;

import org.springframework.ui.Model;

import com.sson.board.BoardDTO;
import com.sson.board.BoardService;
import com.sson.util.ListData;
import com.sson.util.Pager;
import com.sson.util.RowNum;

import oracle.net.aso.p;

public class QnaService implements BoardService{
	
	
	private QnaDAO qnaDAO;
	
	public void setQnaDAO(QnaDAO qnaDAO) {
		this.qnaDAO = qnaDAO;
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		
		return qnaDAO.insert(boardDTO);
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
	
		qnaDAO.hit(num);
		
		return qnaDAO.selectOne(num);
	}

	@Override
	public List<BoardDTO> selectList(ListData listData,Model model) throws Exception {
		RowNum rowNum = listData.makeRow();
		int totalCount = qnaDAO.getTotalCount(rowNum);
		Pager pager = listData.makePage(totalCount);
		
		model.addAttribute("pager", pager);
		model.addAttribute("list", qnaDAO.selectList(rowNum));
		
		
		return qnaDAO.selectList(rowNum);
	}

	
}
