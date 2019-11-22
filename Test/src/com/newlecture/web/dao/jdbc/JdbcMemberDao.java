package com.newlecture.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.dao.MemberDao.MemberDao;
import com.newlecture.web.entitiy.Member;
import com.newlecture.web.entitiy.Notice;

public class JdbcMemberDao implements MemberDao {

	@Override
	public Member get(String id) {
		Member member = null;
		
		String sql = "select * from member where id = ? ";
		String url ="jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";    
		  
	    	try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, id);
	            
	            ResultSet rs = pst.executeQuery();
	                       
	            if(rs.next()) {
	            	member = new Member(
	            		      rs.getString("ID")
	            			, rs.getString("PWD")
	            			, rs.getString("PHONE")
	            			, rs.getString("EMAIL")
	            			);

	            }
	            pst.close();
	            con.close();
	     } catch (ClassNotFoundException e) {

	        e.printStackTrace();
	     } catch (SQLException e) {

	        e.printStackTrace();
	     }	    	
		
		return member;
	}
	
	@Override
	public List<Member> getList() {

		List<Member> list = new ArrayList<>();
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";// 사용할 디비 호스트이름,포트,서비스이름
		String sql = "select*from member";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec"); // new 된 결과를 반환 실행하자마자 연결
			PreparedStatement st = con.prepareStatement(sql); // 커서 생성
			ResultSet rs = st.executeQuery(sql); // 클라이언트에서 받을 공간 생성

			while (rs.next()) {
				Member member = new Member();	
                member.setId(rs.getString("ID"));
                member.setPwd(rs.getString("PWD"));
                member.setPhone(rs.getString("PHONE"));
                member.setEmail(rs.getString("EMAMIL"));
			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // oracle.jdbc.driver.OracleDriver 객체를 만듬
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public int insert(Member member) {
		int result=0;
	       
	    String url ="jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";    
        String sql = "INSERT INTO MEMBER" + "(ID,PWD,NAME,GENDER,PHONE,BIRTHDAY,EMAIL)" 
        		+ "VALUES(?,?,?,?,?,?,?)";
    	
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, member.getId());
            pst.setString(2, member.getPwd());
            pst.setString(3, member.getName());
            pst.setString(4, member.getGender());
            pst.setString(5, member.getPhone());
            pst.setString(6, member.getBirthday());
            pst.setString(7, member.getEmail());
            result = pst.executeUpdate();
    		

            pst.close();
            con.close();
     } catch (ClassNotFoundException e) {

        e.printStackTrace();
     } catch (SQLException e) {

        e.printStackTrace();
     }
    	
    	System.out.println("END");
    	return result;
	}
	
	@Override
	public int update(Member member) {
		
		int result =0 ;

		String sql = "update notice set id=?, pwd=?, phone=?, email=? where id=?";
		String url ="jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";    
		  
	    	try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, member.getId());
	            pst.setString(2, member.getPwd());
	            pst.setString(3, member.getPhone());
	            pst.setString(4, member.getEmail());
	            result = pst.executeUpdate();

	            pst.close();
	            con.close();
	     } catch (ClassNotFoundException e) {

	        e.printStackTrace();
	     } catch (SQLException e) {

	        e.printStackTrace();
	     }
	    	
	     System.out.println("END");
	    	
		
		return result;
	}

	@Override
	public int delete(String id) {
		int result = 0;

		String sql = "delete member where id=? ";
		String url ="jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";    
		  
	    	try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, id);
	            
	            result = pst.executeUpdate();

	            pst.close();
	            con.close();
	     } catch (ClassNotFoundException e) {

	        e.printStackTrace();
	     } catch (SQLException e) {

	        e.printStackTrace();
	     }
		
		return result;
	}
	
	
}
