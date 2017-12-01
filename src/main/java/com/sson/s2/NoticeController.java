package com.sson.s2;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sson.board.BoardDTO;
import com.sson.notice.NoticeService;
import com.sson.util.ListData;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	
	@Inject
	private NoticeService noticeService;

	
	@RequestMapping(value="noticeList")
	public String selectList(Model model,ListData listData){
		try {
			noticeService.selectList(listData,model);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("board", "notice");
		
		return "board/boardList";
	}
	
	
	@RequestMapping(value="noticeView")
	public String selectOne(Model model,@RequestParam(defaultValue="0",required=false) int num){
		
		
		 try {
			model.addAttribute("one", noticeService.selectOne(num));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 model.addAttribute("board", "notice");
		
		return "board/boardView";
	}
	
	
	@RequestMapping(value="noticeWrite" ,method = RequestMethod.GET )
	public String insert1(Model model){
			model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	
	
	@RequestMapping(value="noticeWrite" ,method = RequestMethod.POST)
	public String insert2(Model model,BoardDTO boardDTO,RedirectAttributes rd){
			int result = 0;
		try {
			result = noticeService.insert(boardDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message = "Fail";
		
		if(result > 0){
			message = "Success";
		}
		rd.addFlashAttribute("message", message);
		
		return "redirect:./noticeList";
	}
	
}
