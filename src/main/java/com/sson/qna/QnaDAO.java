package com.sson.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sson.board.BoardDAO;
import com.sson.board.BoardDTO;
import com.sson.qna.QnaDTO;
import com.sson.util.DBConnector;
import com.sson.util.RowNum;

import oracle.net.aso.q;

public class QnaDAO implements BoardDAO{

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		con.setAutoCommit(false);
		
		String sql="insert into QNA values(board_seq.nextval,?,?,?,sysdate,0,board_seq.nextval,0,0)";
		
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
		
		DBConnector.disConnect(con, st);
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		con.setAutoCommit(false);
		
		String sql = "update QNA set writer=?,title=?,contents=? where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setInt(5, boardDTO.getNum());
		
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
		
		String sql = "delete QNA where num=?";
		
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
				+ "(select * from QNA where "+rowNum.getKind()+" like ? order by ref desc, step asc) S) where R BETWEEN ? and ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+rowNum.getSearch()+"%");
		st.setInt(2, rowNum.getStartRow());
		st.setInt(3, rowNum.getLastRow());
		
		
		ResultSet rs = st.executeQuery();
		
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		QnaDTO qnaDTO= null;
		while(rs.next()){
			qnaDTO = new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
			
			ar.add(qnaDTO);
			
		}
		
		DBConnector.disConnect(con, st, rs);
		
		return ar;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		
		Connection con = DBConnector.getDBConnect();
		
		String sql = "select * from QNA where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		
		QnaDTO qnaDTO = null;
		if(rs.next()){
			qnaDTO = new QnaDTO();
			
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
		}
		
		DBConnector.disConnect(con, st, rs);
		
		return qnaDTO;
	}

	@Override
	public int hit(int num) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql = "update QNA set hit=hit+1 where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		
		return result;
	}
	
	@Override
	public int getTotalCount(RowNum rowNum) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql = "select nvl(count(num),0) from QNA where "+rowNum.getKind()+ " like ?";
		
		PreparedStatement st = con.prepareStatement(sql	);
		st.setString(1, "%"+rowNum.getSearch()+"%");
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		int result = rs.getInt(1);
		
		DBConnector.disConnect(con, st, rs);
		
		return result;
	}
	
	
	public int replyInsert(BoardDTO boardDTO){
		int result = 0;
		return result;
	}
	
}
