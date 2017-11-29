package com.sson.s2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sson.board.BoardDTO;
import com.sson.notice.NoticeService;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	
	private NoticeService noticeService;
	
	public NoticeController() {
		noticeService = new NoticeService();
	}

	@RequestMapping(value="noticeList")
	public String selectList(Model model){
		
		List<BoardDTO> ar = null;
		try {
			ar = noticeService.selectList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("dto", ar)
		.addAttribute("board", "notice");
		
		return "board/boardList";
	}
	
}
