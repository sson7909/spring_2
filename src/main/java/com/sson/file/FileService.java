package com.sson.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sson.util.FileSaver;

@Service
public class FileService {

	private FileSaver fileSaver;
	
	@Inject
	private FileDAO fileDAO;
	
	public List<String> fileSave(MultipartFile [] f1,HttpSession session) throws Exception{
		String filePath = session.getServletContext().getRealPath("resources/upload");
		
		List<String> ar = new ArrayList<String>();
	
		for (MultipartFile multipartFile : f1) {
			String fileNames = fileSaver.save2(filePath, multipartFile);
			ar.add(fileNames);
		}
		
		return ar;
	}
	
	public int delete(int num,String filename,HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		
		int result = 0;
		result = fileDAO.delete2(num, filename);
		
		if(result > 0){
			File file = new File(filePath, filename);
			file.delete();
		}
		
		return result;
	}
}
