package com.sson.board;

import java.util.List;

public interface BoardDAO {

	public int insert() throws Exception;
	
	public int update() throws Exception;
	
	public int delete() throws Exception;
	
	public List<BoardDTO> selectList() throws Exception;
	
	public BoardDTO selectOne() throws Exception;
	
	public int hit() throws Exception;
}
