package com.newlecture.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newlecture.web.dao.NoticeDao.NoticeDao;
import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.entitiy.NoticeView;

public class JdbcNoticeDao implements NoticeDao {

	@Override
	public int insert(Notice notice) {
		int result = 0;

		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		String sql = "INSERT INTO NOTICE" + "(TITLE,CONTENT,WRITER_ID,FILES)" + "VALUES(?,?,?,?)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, notice.getTitle());
			pst.setString(2, notice.getContent());
			pst.setString(3, notice.getWriterId());
			pst.setString(4, notice.getFiles());
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
	public List<NoticeView> getList(int page, String field, String query) {
		List<NoticeView> list = new ArrayList<>();
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";// 사용할 디비 호스트이름,포트,서비스이름
		String sql = "select * from " + "( select rownum num, N.*" + " from (select * from notice_view where " + field
				+ " like ? order by regdate desc )" + "N) where num between ? and ? ";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec"); // new 된 결과를 반환 실행하자마자 연결
			PreparedStatement st = con.prepareStatement(sql); // 커서 생성
			st.setString(1, "%" + query + "%");
			st.setInt(2, 1 + (page - 1) * 10);
			st.setInt(3, page * 10);
			ResultSet rs = st.executeQuery(); // 클라이언트에서 받을 공간 생성
			while (rs.next()) {
				System.out.println(rs.getInt("ID"));
				NoticeView notice = new NoticeView();
				notice.setId(rs.getInt("ID"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setContent(rs.getString("CONTENT"));
				notice.setHit(rs.getInt("HIT"));
				notice.setWriterId(rs.getString("WRITER_ID"));

				list.add(notice);

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
	public Notice get(int id) {
		Notice notice = null;

		String sql = "select*from notice where id=?";
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				notice = new Notice(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT"),
						rs.getString("WRITER_ID"), rs.getDate("REGDATE"), rs.getString("FILES"), rs.getInt("hit"),
						rs.getBoolean("open"));
			}
			pst.close();
			con.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return notice;
	}

	@Override
	public int update(Notice notice) {

		int result = 0;

		String sql = "update notice set title=?, content=?, files=?, id=? where id=?";
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, notice.getTitle());
			pst.setString(2, notice.getContent());
			pst.setString(3, notice.getFiles());
			pst.setInt(4, notice.getId());
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

	@Override
	public int delete(int id) {
		int result = 0;

		String sql = "delete notice where id=? ";
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);

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

	@Override
	public List<NoticeView> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeView> getList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int openList(int[] ids) {
		int result = 0;
		String values = "";
		for (int i = 0; i < ids.length; i++) {
			values += ids[i];
			if (i < ids.length - 1) {
				values += ",";
			}
		}

		System.out.println(values);

		String sql = "update notice set open=1 where id in (" + values + ")";
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			PreparedStatement pst = con.prepareStatement(sql);

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

	@Override
	public Notice getPrevById(int id) {

		Notice notice = null;

		String sql = "		select * from (select * from notice_view order by regdate desc) where regdate"
				+ "		<(select regdate from notice where id=?)" + "		and rownum=1;";
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				notice = new NoticeView(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT"),
						rs.getString("WRITER_ID"), rs.getDate("REGDATE"), rs.getString("FILES"), rs.getInt("hit"),
						rs.getBoolean("open"), rs.getInt("cmt_count"));

			}
			pst.close();
			con.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return notice;
	}

	@Override
	public Notice getNextById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getListCount(String field, String query) {

		int count = 0;

		String sql = "select count(id) COUNT from notice where " + field + " LIKE ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "%" + query + "%");
			ResultSet rs = pst.executeQuery();

			if (rs.next())
				count = rs.getInt("COUNT");

			pst.close();
			con.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return count;

	}
}
