package com.sson.s2;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sson.file.FileDAO;
import com.sson.file.FileService;
import com.sson.file.PhotoDTO;
import com.sson.util.FileSaver;

@Controller
@RequestMapping(value="/util/*")
public class UtilController {

	@Inject
	private FileService fileService;
	
	@RequestMapping(value="fileDelete")
	public String fileDelete (int num , String filename,HttpSession session) {
		System.out.println(filename);
		System.out.println(num);
		int result = 0;
		try {
			result = fileService.delete(num, filename,session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);
		return null;
	}
	
	@RequestMapping(value="photoUpload" ,method = RequestMethod.POST)
	public String smartEditor(PhotoDTO photoDTO ,	MultipartHttpServletRequest request ,HttpSession session){
		
		String filePath = session.getServletContext().getRealPath("resources/upload");
		//filePath = filePath + "resources" +File.separator + "upload"; 원래 작성해야함 오류때문에 잠시 주석.
		File file = new File(filePath);
		
		System.out.println(filePath);
		
		if(!file.exists()){
			file.mkdirs();
		}
		
		FileSaver fs = new FileSaver();
		String fileName="";
		String result ="";
		
		StringBuffer sb = new StringBuffer();
	
		try {
			fileName = fs.save1(filePath, photoDTO.getFiledata());
			//result =  "&bNewLine=true&sFileName="+photoDTO.getFiledata().getOriginalFilename()
			//	+"&sFileURL="+session.getServletContext().getContextPath()+File.separator+"upload"+File.separator+fileName;
			
			sb.append("&bNewLine=true&sFileName=");
			sb.append(photoDTO.getFiledata().getOriginalFilename());
			sb.append("&sFileURL=");
			sb.append(session.getServletContext().getContextPath());
			sb.append("/resources/");
			sb.append("upload");
			sb.append("/");
			sb.append(fileName);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * 파라미터가 뭐가 넘어오는지 모를때.
		 * Enumeration<String> ar = request.getParameterNames();
		
		while (ar.hasMoreElements()) {
			System.out.println(ar.nextElement());
		}*/
		
		
		/*
		 * 넘어오는 파라미터 이름을 모를때
		 * 매개변수로 사용 MultipartHttpServletRequest request
		Iterator<String> it =request.getFileNames();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	*/
		System.out.println(photoDTO.getCallback()+photoDTO.getCallback_func()+sb.toString());
		
		return "redirect:"+photoDTO.getCallback()+photoDTO.getCallback_func()+sb.toString();
	}
}
