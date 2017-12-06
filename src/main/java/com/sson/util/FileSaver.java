package com.sson.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	//셋중에 하고싶은걸 사용함.
	
	
	//3.FileCopyUtils 사용해서 저장
	public String save3(String filePath, MultipartFile file) throws Exception {
	 String fileName = file.getOriginalFilename();
		 fileName = fileName.substring(fileName.lastIndexOf("."));
		 fileName = UUID.randomUUID().toString() + fileName;
		 
		 	byte [] fileData = file.getBytes();
		 
		 	File f = new File(filePath,fileName);
		
		 	FileCopyUtils.copy(fileData, f);
		 	
		 	
		return fileName;
	}
	
	//2. transfer 사용해서 저장
	public String save2(String filePath , MultipartFile file) throws Exception{
	 String fileName = file.getOriginalFilename();
		 fileName = fileName.substring(fileName.lastIndexOf("."));
		 fileName = UUID.randomUUID().toString() + fileName;
		 
		 File f = new File(filePath, fileName);
		 
		 file.transferTo(f);
		 
		 return fileName;
	}
	
	//1. outputStream 사용해서 저장
	public String save1 (String filePath , MultipartFile file) throws Exception{
	 String fileName = file.getOriginalFilename();
			 fileName = fileName.substring(fileName.lastIndexOf("."));
			 fileName = UUID.randomUUID().toString() + fileName;
			 
			 byte [] filedata = file.getBytes();
	 
			 File f = new File(filePath , fileName);
			 FileOutputStream fo = null;
			 
			 try {
				fo =  new FileOutputStream(f);
				fo.write(filedata);
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				fo.close();
			}

	return fileName;
	}
	
}
