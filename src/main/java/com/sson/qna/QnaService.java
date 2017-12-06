package com.sson.qna;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.sson.board.BoardDTO;
import com.sson.board.BoardService;
import com.sson.file.FileDAO;
import com.sson.file.FileDTO;
import com.sson.util.FileSaver;
import com.sson.util.ListData;
import com.sson.util.Pager;
import com.sson.util.RowNum;

import oracle.net.aso.p;

@Service
public class QnaService implements BoardService{
	
	@Inject
	private QnaDAO qnaDAO;
	@Inject
	private FileSaver fileSaver;
	@Inject 
	private FileDAO fileDAO;

	@Override
	public int insert(BoardDTO boardDTO,HttpSession session) throws Exception {
		int num = qnaDAO.getNum();
		String filePath = session.getServletContext().getRealPath("resources/upload");
		
		List<FileDTO> ar = new ArrayList<FileDTO>();
		FileDTO fileDTO = null;
		
		for (MultipartFile file : ((QnaDTO)boardDTO).getF1()) {
			fileDTO = new FileDTO();
			fileDTO.setFilename(fileSaver.save2(filePath, file));
			fileDTO.setOriname(file.getOriginalFilename());
			fileDTO.setNum(num);
			
			ar.add(fileDTO);
			fileDAO.insert(fileDTO);
		}
		
		boardDTO.setNum(num);
		
		return qnaDAO.insert(boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO,HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		int result = 0;
		FileDTO fileDTO = null;
		for(MultipartFile file : ((QnaDTO)boardDTO).getF1()){
			fileDTO = new FileDTO();
			fileDTO.setFilename(fileSaver.save2(filePath, file));
			fileDTO.setOriname(file.getOriginalFilename());
			fileDTO.setNum(boardDTO.getNum());
			
			fileDAO.insert(fileDTO);
		}
		System.out.println(boardDTO.getNum());
		System.out.println(boardDTO.getWriter());
		result = qnaDAO.update(boardDTO);
		
		return result;
	}

	@Override
	public int delete(int num,HttpSession session) throws Exception {
		int result = 0;
		String filePath = session.getServletContext().getRealPath("resources/upload");
		
		result = qnaDAO.delete(num);
		if(result > 0){
			fileDAO.delete(num);
		}
		
		return result;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
	
		qnaDAO.hit(num);
		List<FileDTO> ar = new ArrayList<FileDTO>();
		ar = fileDAO.selectList(num);
		QnaDTO qnaDTO = (QnaDTO) qnaDAO.selectOne(num);
		qnaDTO.setAr(ar);
		
		
		return qnaDTO;
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
