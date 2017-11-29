package com.sson.s2;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sson.board.BoardDTO;
import com.sson.qna.QnaService;

@Controller
@RequestMapping(value="/qna/*")
public class QnaController {

	@Inject
	private QnaService qnaService;
	
	@RequestMapping(value="qnaList")
	public String selectList(Model model) {
		
		List<BoardDTO> ar = null;
		
		try {
			ar = qnaService.selectList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("dto", ar);
		model.addAttribute("board" , "qna");
		
		return "board/boardList";
	}

}
