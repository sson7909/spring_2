package com.sson.s2;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sson.board.BoardDTO;
import com.sson.qna.QnaService;
import com.sson.util.ListData;

@Controller
@RequestMapping(value="/qna/*")
public class QnaController {

	@Inject
	private QnaService qnaService;
	
	@RequestMapping(value="qnaList")
	public String selectList(ListData listData, Model model) {
		
		List<BoardDTO> ar = null;
		
		try {
			ar = qnaService.selectList(listData,model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("dto", ar);
		model.addAttribute("board" , "qna");
		
		return "board/boardList";
	}
	
	@RequestMapping(value="qnaView")
	public String selectOne(Model model, @RequestParam(defaultValue="0",required=false) int num){
			try {
				model.addAttribute("one" , qnaService.selectOne(num));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("board", "qna");
			
		return "board/boardView";
	}
	
	@RequestMapping(value="qnaWrite" , method = RequestMethod.GET)
	public String insert1(Model model){
		model.addAttribute("board", "qna");
		return "board/boardWrite";
	}
	
	@RequestMapping(value="qnaWrite", method = RequestMethod.POST)
	public String insert2(Model model,BoardDTO boardDTO){
		
		try {
			qnaService.insert(boardDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("board", "qna");
		return "redirect:./qnaList";
	}
	
	@RequestMapping(value="qnaReply" ,method = RequestMethod.GET)
	public String reply(Model model){
		
			model.addAttribute("board", "qna");
			
		return "board/boardWrite";
	}
}
