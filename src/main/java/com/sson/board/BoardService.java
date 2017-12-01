package com.sson.board;

import java.util.List;

import org.springframework.ui.Model;

import com.sson.util.ListData;

public interface BoardService {

	//insert
	public int insert(BoardDTO boardDTO) throws Exception ;
	
	//update
	public int update(BoardDTO boardDTO) throws Exception;
	
	//delete
	public int delete(int num) throws Exception;
		
	//selecOne 
	public BoardDTO selectOne(int num) throws Exception;
	
	//selectList
	public List<BoardDTO> selectList (ListData listData,Model model) throws Exception;
}
