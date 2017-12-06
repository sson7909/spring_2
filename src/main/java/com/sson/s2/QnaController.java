package com.sson.s2;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sson.board.BoardDTO;
import com.sson.file.FileService;
import com.sson.qna.QnaDTO;
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
	public String insert2(Model model,QnaDTO boardDTO,HttpSession session,RedirectAttributes rd){
		int result = 0;
		try {
		result =	qnaService.insert(boardDTO,session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("board", "qna");
		
		String message = "fail";
		
		if(result > 0){
			message = "success";
		}
		
		rd.addFlashAttribute("message", message);
		
		return "redirect:./qnaList";
	}
	
	@RequestMapping(value="qnaReply" ,method = RequestMethod.GET)
	public String reply(Model model){
		
			model.addAttribute("board", "qna");
			
		return "board/boardWrite";
	}
	
	@RequestMapping(value="qnaDelete")
	public String delete(int num,HttpSession session,RedirectAttributes rd){
		int result = 0;
		try {
			result = qnaService.delete(num, session);
			
			String message = "삭제안됨";
			if(result > 0){
				message = "삭제됨";
			}
			
			rd.addFlashAttribute("message", message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:./qnaList";
	}
	
	@RequestMapping(value="qnaUpdate" ,method = RequestMethod.GET)
	public String update(int num,Model model) {
		model.addAttribute("board", "qna");
		
		try {
			BoardDTO boardDTO = qnaService.selectOne(num);
			
			model.addAttribute("one", boardDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "board/boardUpdate";
	}
	
	@RequestMapping(value="qnaUpdate" , method = RequestMethod.POST)
	public String update(QnaDTO boardDTO,HttpSession session,RedirectAttributes rd){
		
		try {
			int result = qnaService.update(boardDTO, session);
			
			String message = "업데이트실패";
			if(result > 0){
				message = "업데이트성공";
			}
			
			rd.addFlashAttribute("message", message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:./qnaList";
	}
}
