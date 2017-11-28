package com.sson.board;

import java.util.List;

public interface BoardDAO {

	public int insert(BoardDTO boardDTO) throws Exception;
	
	public int update(BoardDTO boardDTO) throws Exception;
	
	public int delete(int num) throws Exception;
	
	public List<BoardDTO> selectList() throws Exception;
	
	public BoardDTO selectOne(int num) throws Exception;
	
	public int hit(int num) throws Exception;
}
