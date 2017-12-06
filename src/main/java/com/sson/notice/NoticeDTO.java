package com.sson.notice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sson.board.BoardDTO;
import com.sson.file.FileDTO;

public class NoticeDTO extends BoardDTO{

	private MultipartFile [] f1;
	private List<FileDTO> ar;
	
	
	public List<FileDTO> getAr() {
		return ar;
	}

	public void setAr(List<FileDTO> ar) {
		this.ar = ar;
	}

	public MultipartFile[] getF1() {
		return f1;
	}

	public void setF1(MultipartFile[] f1) {
		this.f1 = f1;
	}
	
	
}
