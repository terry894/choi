package com.newlecture.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

//@Repository
public class JdbcNoticeDao implements NoticeDao {

	@Override
	public int insert(Notice notice) {
		int result = 0;
		
		String sql = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID, FILES) "
				+ "VALUES(?,?,?,?)";
		
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getWriterId());
			st.setString(4, notice.getFiles());
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<NoticeView> getList() {
		// TODO Auto-generated method stub
		return getList(1, "title", "");
	}

	@Override
	public List<NoticeView> getList(int page) {
		// TODO Auto-generated method stub
		return getList(page, "title", "");
	}

	@Override
	public List<NoticeView> getList(int page, String field, String query) {
		
		List<NoticeView> list = new ArrayList<>();
		
		String sql = "SELECT * FROM(" + 
				"    SELECT ROWNUM NUM, N.*" + 
				"    FROM(" + 
				"        select * " + 
				"        from NOTICE_VIEW " + 
				"        where "+field+" like ? " + 
				"        order by regdate desc" + 
				"    ) N" + 
				") WHERE NUM BETWEEN ? AND ?";
		
		System.out.println(sql);
		System.out.println(page);
		
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10); // 1, 11, 21, 31, -> an = 1+(page-1)*10 -> page +-*/
			st.setInt(3, page*10); // 10, 20, 30, 40, ...
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				NoticeView notice = new NoticeView(
								  rs.getInt("ID")
								, rs.getString("TITLE")
								, rs.getString("CONTENT")
								, rs.getString("WRITER_ID")
								, rs.getDate("REGDATE")
								, rs.getString("FILES")
								, rs.getInt("HIT")
								, rs.getBoolean("OPEN")
								, rs.getInt("CMT_COUNT"));
				
				list.add(notice);
				
				System.out.println(notice);
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public Notice get(int id) {
		
		Notice notice = null;
		String sql = "SELECT * FROM NOTICE WHERE ID=?";		
		
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);			
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				notice = new Notice(
								  rs.getInt("ID")
								, rs.getString("TITLE")
								, rs.getString("CONTENT")
								, rs.getString("WRITER_ID")
								, rs.getDate("REGDATE")
								, rs.getString("FILES")
								, rs.getInt("HIT")
								, rs.getBoolean("OPEN"));
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}

	@Override
	public int update(Notice notice) {
		int result = 0;
		
		String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=?, WRITER_ID=?, FILES=?, HIT=? WHERE ID=?";
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getWriterId());
			st.setString(4, notice.getFiles());
			st.setInt(5, notice.getHit());
			st.setInt(6, notice.getId());
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete(int id) {
		int result = 0;
		
		String sql = "DELETE NOTICE WHERE ID=?";
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int openList(int[] ids) {
		int result = 0;
		
		String values = "";
		for(int i=0; i<ids.length; i++) {
			values += ids[i];
			
			if(i < ids.length - 1)
				values += ",";
		}
		
		System.out.println(values);
		
		String sql = "UPDATE NOTICE SET OPEN=1 WHERE ID in ("+values+")";
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
						
			result = st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Notice getPrevById(int id) {
		Notice notice = null;
		
		String sql = "SELECT * FROM (SELECT * FROM NOTICE_VIEW ORDER BY REGDATE DESC)" + 
				"WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID=?)" + 
				"    AND ROWNUM = 1";		
		
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);			
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				notice = new Notice(
								  rs.getInt("ID")
								, rs.getString("TITLE")
								, rs.getString("CONTENT")
								, rs.getString("WRITER_ID")
								, rs.getDate("REGDATE")
								, rs.getString("FILES")
								, rs.getInt("HIT")
								, rs.getBoolean("OPEN"));
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
	@Override
	public int getListCount(String field, String query) {
		int count  = 0;
		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE WHERE "+field+" LIKE ?";
		
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");			
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
				count = rs.getInt("COUNT");
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

}
