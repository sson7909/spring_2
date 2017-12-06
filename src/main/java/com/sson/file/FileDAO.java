package com.sson.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Repository;

import com.sson.util.DBConnector;

@Repository
public class FileDAO {

	//selectOne
		public FileDTO selectOne(int num,String filename) throws Exception {
			Connection con = DBConnector.getDBConnect();
			
			String sql = "select * from upload where num=? and filename=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			
			
			st.setInt(1, num);
			st.setString(2, filename);
			
			ResultSet rs = st.executeQuery();
			
			FileDTO fileDTO = null;
			
			if (rs.next()) {
				fileDTO = new FileDTO();
				fileDTO.setNum(rs.getInt("fnum"));
				fileDTO.setFnum(rs.getInt("num"));
				fileDTO.setFilename(rs.getString("filename"));
				fileDTO.setOriname(rs.getString("oriname"));
		
			}
			
			DBConnector.disConnect(con, st, rs);
			
			return fileDTO;
			
		}
	
	//selectList
	public List<FileDTO> selectList(int num) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql = "select * from upload where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		
		List<FileDTO> ar = new ArrayList<FileDTO>();
		FileDTO fileDTO = null;
		
		while (rs.next()) {
			fileDTO = new FileDTO();
			fileDTO.setNum(rs.getInt("fnum"));
			fileDTO.setFnum(rs.getInt("num"));
			fileDTO.setFilename(rs.getString("filename"));
			fileDTO.setOriname(rs.getString("oriname"));
		
			ar.add(fileDTO);
		}
		
		DBConnector.disConnect(con, st, rs);
		
		return ar;
		
	}
	
	
	//insert
	
	public int insert (FileDTO fileDTO) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql = "insert into UPLOAD values (board_seq.nextval,?,?,?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, fileDTO.getNum());
		st.setString(2, fileDTO.getFilename());
		st.setString(3, fileDTO.getOriname());
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		
		return result;
	}
	
	//delete
	public int delete(int num) throws Exception {
		Connection con = DBConnector.getDBConnect();
		
		String sql = "delete upload where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		DBConnector.disConnect(con, st);
		
		return result;
	}
	
	//delete2
	
		public int delete2(int num,String filename) throws Exception {
			Connection con = DBConnector.getDBConnect();
			
			String sql = "delete upload where num=? and filename=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, num);
			st.setString(2, filename);
			
			int result = st.executeUpdate();
			DBConnector.disConnect(con, st);
			
			return result;
		}

}
