package com.sson.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sson.board.BoardDAO;
import com.sson.board.BoardDTO;
import com.sson.util.DBConnector;

public class NoticeDAO implements BoardDAO{

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		con.setAutoCommit(false);
		
		String sql="insert into NOTICE values(board_seq.nextval,?,?,?,sysdate,0)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		
		
		int result = st.executeUpdate();
		
		if(result > 0){
			con.commit();
			con.setAutoCommit(true);
		}else{
			con.rollback();
		}
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		con.setAutoCommit(false);
		
		String sql = "update NOTICE set writer=?,title=?,contents=?,reg_date=? where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setDate(4, boardDTO.getReg_date());
		st.setInt(5, boardDTO.getNum());
		
		int result = st.executeUpdate();
		
		if(result > 0){
			con.commit();
			con.setAutoCommit(true);
		}else{
			con.rollback();
		}
		
		return result;
	}

	@Override
	public int delete(int num) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		con.setAutoCommit(false);
		
		String sql = "delete Notice where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		
		if(result > 0){
			con.commit();
			con.setAutoCommit(true);
		}else{
			con.rollback();
		}
		
		return result;
	}

	@Override
	public List<BoardDTO> selectList() throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql = "select * from (select rowNum R,S.* from "
				+ "(select * from NOTICE order by num desc) S) where R BETWEEN 1 and 10";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		NoticeDTO noticeDTO = null;
		while(rs.next()){
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			
			ar.add(noticeDTO);
			
		}
		
		return ar;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		
		Connection con = DBConnector.getDBConnect();
		
		String sql = "select * from NOTICE where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		
		NoticeDTO noticeDTO = null;
		if(rs.next()){
			noticeDTO = new NoticeDTO();
			
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
		}
		
		
		return noticeDTO;
	}

	@Override
	public int hit(int num) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql = "update NOTICE set hit=hit+1 where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		
		return result;
	}

	
	
}
