package com.sson.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.chainsaw.Main;
import org.springframework.stereotype.Repository;

import com.sson.board.BoardDAO;
import com.sson.board.BoardDTO;
import com.sson.util.DBConnector;
import com.sson.util.RowNum;

import oracle.net.aso.b;

@Repository
public class NoticeDAO implements BoardDAO{
	
	public int getNum () throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql =  "select board_seq.nextval from dual";
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		rs.next();
		
		int num = rs.getInt(1);
		
		DBConnector.disConnect(con, st, rs);
		
		return num;
	}
	
	
	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		con.setAutoCommit(false);
		
		String sql="insert into NOTICE values(?,?,?,?,sysdate,0)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, boardDTO.getNum());
		st.setString(2, boardDTO.getWriter());
		st.setString(3, boardDTO.getTitle());
		st.setString(4, boardDTO.getContents());
		
		
		int result = st.executeUpdate();
		
		if(result > 0){
			con.commit();
			con.setAutoCommit(true);
		}else{
			con.rollback();
		}
		
		DBConnector.disConnect(con, st);
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		con.setAutoCommit(false);
		
		String sql = "update NOTICE set writer=?,title=?,contents=? where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setInt(4, boardDTO.getNum());
		
		int result = st.executeUpdate();
		
		if(result > 0){
			con.commit();
			con.setAutoCommit(true);
		}else{
			con.rollback();
		}
		
		DBConnector.disConnect(con, st);
		
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
		
		DBConnector.disConnect(con, st);
		
		return result;
	}

	@Override
	public List<BoardDTO> selectList(RowNum rowNum) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql = "select * from (select rowNum R,S.* from "
				+ "(select * from NOTICE where "+rowNum.getKind()+" like ? order by num desc) S) where R BETWEEN ? and ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+rowNum.getSearch()+"%");
		st.setInt(2, rowNum.getStartRow());
		st.setInt(3, rowNum.getLastRow());
		
		
		ResultSet rs = st.executeQuery();
		
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		BoardDTO boardDTO = null;
		while(rs.next()){
			boardDTO = new NoticeDTO();
			boardDTO.setNum(rs.getInt("num"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setContents(rs.getString("contents"));
			boardDTO.setReg_date(rs.getDate("reg_date"));
			boardDTO.setHit(rs.getInt("hit"));
			
			ar.add(boardDTO);
			
		}
		
		DBConnector.disConnect(con, st, rs);
		
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
		
		DBConnector.disConnect(con, st, rs);
		
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
	
	@Override
	public int getTotalCount(RowNum rowNum) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql = "select nvl(count(num),0) from NOTICE where "+rowNum.getKind()+" like ? ";
		
		PreparedStatement st = con.prepareStatement(sql	);
		
		st.setString(1, "%"+rowNum.getSearch()+"%");
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		int result = rs.getInt(1);
		
		DBConnector.disConnect(con, st, rs);
		
		return result;
	}
}
