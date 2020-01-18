package com.newlecture.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

public class JdbcMemberDao implements MemberDao {

	@Override
	public List<Member> getList() {
		
		List<Member> list = new ArrayList<>();
		
		String sql = "SELECT * FROM(" + 
				"    SELECT ROWNUM NUM, M.*" + 
				"    FROM(" + 
				"        select * " + 
				"        from MEMBER " + 
				"        where NAME like ? " + 
				"        order by regdate desc" + 
				"    ) M" + 
				") WHERE NUM BETWEEN ? AND ?";
		
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%%");
			int page = 1;
			st.setInt(2, 1+(page-1)*10); // 1, 11, 21, 31, -> an = 1+(page-1)*10 -> page +-*/
			st.setInt(3, page*10); // 10, 20, 30, 40, ...
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Member member = new Member(
								  rs.getString("ID")
								, rs.getString("PWD")
								, rs.getString("NAME")
								, rs.getString("GENDER")
								, rs.getString("BIRTHDAY")
								, rs.getString("PHONE")
								, rs.getDate("REGDATE")
								, rs.getString("EMAIL"));
				
				list.add(member);
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
	public Member get(String id) {
		
		Member member = null;
		
		String sql = "SELECT * FROM MEMBER WHERE ID=?";
		
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				member = new Member(
								  rs.getString("ID")
								, rs.getString("PWD")
								, rs.getString("NAME")
								, rs.getString("GENDER")
								, rs.getString("BIRTHDAY")
								, rs.getString("PHONE")
								, rs.getDate("REGDATE")
								, rs.getString("EMAIL"));				
				
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
		
		return member;
	}
	
	@Override
	public int insert(Member member) {
		int result = 0;
		
		String sql = "INSERT INTOMEMBER(id,pwd,name,gender,birthday,phone,email) "
				+ "VALUES(?,?,?,?,?,?,?)";
		
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, member.getId());
			st.setString(2, member.getPwd());
			st.setString(3, member.getName());
			st.setString(4, member.getGender());
			st.setString(5, member.getBirthday());
			st.setString(6, member.getPhone());
			st.setString(7, member.getEmail());
			
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
	public int update(Member member) {
		int result = 0;
		
		String sql = "UPDATE MEMBER SET pwd=?,name=?,gender=?,birthday=?,phone=?,email=? WHERE ID=?";
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, member.getPwd());
			st.setString(2, member.getName());
			st.setString(3, member.getGender());
			st.setString(4, member.getBirthday());
			st.setString(5, member.getPhone());
			st.setString(6, member.getEmail());
			st.setString(7, member.getId());
			
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
	public int delete(String id) {
		int result = 0;
		
		String sql = "DELETE MBMBER WHERE ID=?";
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, id);
			
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

	public static void main(String[] args) {
		JdbcMemberDao memberDao = new JdbcMemberDao();
		System.out.println(memberDao.get("1234"));
	}

}
