package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bihe.bean.MyDate;

public class MyDateDaoImpl implements Dao<MyDate> , Extractor<MyDate>{

	private static MyDateDaoImpl instance;

	private MyDateDaoImpl() {
	}

	public static MyDateDaoImpl getInstance() {
		if (instance == null) {
			instance = new MyDateDaoImpl();
		}
		return instance;
	}
	@Override
	public Set<MyDate> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM mydate");
			Set<MyDate> myDates = new HashSet<MyDate>();
			
			while(rs.next()) {
				MyDate myDate = extract(rs);
				myDates.add(myDate);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return myDates;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public MyDate getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM mydate WHERE ID=" + id);
			if (rs.next()) {
				MyDate ad = extract(rs);
				st.close();
				con.close();
				Dao.connect().close();
				return ad;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean addElement(MyDate myDate) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO mydate VALUES (?, ?, ?, ?)");
			ps.setInt(1, myDate.getId());
			ps.setInt(2, myDate.getDay());
			ps.setInt(3, myDate.getMonth());
			ps.setInt(4, myDate.getYear());
			int i = ps.executeUpdate();
			if(i==1) {
				ps.close();
				con.close();
				Dao.connect().close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteElement(MyDate myDate) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
		    int i = st.executeUpdate("DELETE FROM mydate WHERE ID=" + myDate.getId());
		    if(i==1) {
		    	st.close();
		    	con.close();
		    	Dao.connect().close();
		    	return true;
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateElement(MyDate myDate) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE mydate SET Day=?, Month=?, Year=? WHERE ID=?");
			ps.setInt(1, myDate.getDay());
			ps.setInt(2, myDate.getMonth());
			ps.setInt(3, myDate.getYear());
			ps.setInt(4, myDate.getId());
			int i = ps.executeUpdate();
			if(i==1) {
				ps.close();
				con.close();
				Dao.connect().close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public MyDate extract(java.sql.ResultSet rs) throws SQLException {
		MyDate myDate = new MyDate();
		myDate.setId(rs.getInt("ID"));
		myDate.setDay(rs.getInt("Day"));
		myDate.setMonth(rs.getInt("Month"));
		myDate.setYear(rs.getInt("Year"));
		return myDate;
	}

	
}