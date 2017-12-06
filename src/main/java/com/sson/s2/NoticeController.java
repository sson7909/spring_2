package com.sson.s2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sson.board.BoardDTO;
import com.sson.notice.NoticeDTO;
import com.sson.notice.NoticeService;
import com.sson.util.ListData;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	
	@Inject
	private NoticeService noticeService;

	
	@RequestMapping(value="noticeUpdate" ,method = RequestMethod.GET)
	public String update(int num,Model model) {
		
			try {
				BoardDTO boardDTO = noticeService.selectOne(num);
				model.addAttribute("one", boardDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			model.addAttribute("board", "notice");
			
			
		return "board/boardUpdate";
	}
	
	@RequestMapping(value="noticeUpdate" ,method = RequestMethod.POST)
	public String update(NoticeDTO noticeDTO,HttpSession session) {
		
		int result = 0;
		try {
			result = noticeService.update(noticeDTO,session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message = "fail";
		
		if(result > 0){
			message = "success";
		}
		
		System.out.println(message);
		return "redirect:./noticeList";
	}
	
	
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
		
		BoardDTO boardDTO = null;
		 try {
			 boardDTO = noticeService.selectOne(num);
			model.addAttribute("one", boardDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 System.out.println(boardDTO.getNum());
		 
		 model.addAttribute("board", "notice");
		
		return "board/boardView";
	}
	
	
	@RequestMapping(value="noticeWrite" ,method = RequestMethod.GET )
	public String insert1(Model model){
			model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	
	
	
	@RequestMapping(value="noticeWrite" ,method = RequestMethod.POST)
	public String insert2(Model model,NoticeDTO boardDTO,RedirectAttributes rd,HttpSession session){
		int result = 0;
		
		System.out.println(boardDTO.getContents());
		System.out.println(boardDTO.getTitle());
		
		try {
			result = noticeService.insert(boardDTO,session);
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
	
	
	@RequestMapping(value="noticeDelete")
	public String delete(int num,RedirectAttributes rd ,HttpSession session){
		int result = 0;
		try {
			result = noticeService.delete(num,session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message = "Fail";
		
		if(result > 1){
			message = "Success";
		}
		rd.addFlashAttribute("message", message);
		
		return "redirect:./noticeList";
	}
}
