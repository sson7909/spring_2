package com.sson.notice;

import java.io.File;
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

@Service
public class NoticeService implements BoardService {

	@Inject
	private NoticeDAO noticeDAO;
	@Inject
	private FileDAO fileDAO;
	@Inject
	private FileSaver fileSaver;
	
	@Override
	public int insert(BoardDTO boardDTO,HttpSession session) throws Exception {
		int num = noticeDAO.getNum();
		
	String filePath = session.getServletContext().getRealPath("resources/upload");
		
		List<FileDTO> ar = new ArrayList<FileDTO>();
		
		FileDTO fileDTO = null;
		for (MultipartFile multipartFile : ((NoticeDTO)boardDTO).getF1()) {
			fileDTO = new FileDTO();
			fileDTO.setFilename(fileSaver.save2(filePath, multipartFile));
			fileDTO.setOriname(multipartFile.getOriginalFilename());
			fileDTO.setNum(num);
			
			ar.add(fileDTO);
			
			fileDAO.insert(fileDTO);
		}
		
		boardDTO.setNum(num);
		
		return noticeDAO.insert(boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO,HttpSession session) throws Exception {
		
		
		String filePath = session.getServletContext().getRealPath("resources/upload");
		
		int result = 0;
		result = noticeDAO.update(boardDTO);
		
		FileDTO fileDTO = null;
		if(result > 0){
			fileDTO = new FileDTO();
			for (MultipartFile file : ((NoticeDTO)boardDTO).getF1()) {
				fileDTO.setFilename(fileSaver.save2(filePath, file));
				fileDTO.setOriname(file.getOriginalFilename());
				fileDTO.setNum(boardDTO.getNum());
				fileDAO.insert(fileDTO);
			}
		}
		
		return result;
	}

	@Override
	public int delete(int num , HttpSession session) throws Exception {
		 int result = noticeDAO.delete(num);
		 
		 List<FileDTO> ar = fileDAO.selectList(num); //파일 이름을 가져와서
		 if(result >0){
			 result = result + fileDAO.delete(num);
		 }
		 
		 String filePath = session.getServletContext().getRealPath("resources/upload");
		 
		 //포문 돌려서 꺼낸다음 실제 파일 삭제
		 for (FileDTO fileDTO : ar) {
			File file = new File(filePath, fileDTO.getFilename());
			file.delete();
		}
		 
		 
		return result;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		 noticeDAO.hit(num);
		 List<FileDTO> ar = fileDAO.selectList(num);
		 NoticeDTO noticeDTO = (NoticeDTO) noticeDAO.selectOne(num);
		 noticeDTO.setAr(ar);
		 
		return noticeDTO;
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
