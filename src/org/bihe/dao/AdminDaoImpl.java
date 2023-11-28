package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bihe.bean.Admin;

public class AdminDaoImpl implements Dao<Admin> , Extractor<Admin>{

	private static AdminDaoImpl instance;

	private AdminDaoImpl() {
	}

	public static AdminDaoImpl getInstance() {
		if (instance == null) {
			instance = new AdminDaoImpl();
		}
		return instance;
	}
	@Override
	public Set<Admin> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM admin");
			Set<Admin> admins = new HashSet<Admin>();
			
			while(rs.next()) {
				Admin admin = extract(rs);
				admins.add(admin);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return admins;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public Admin getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM admin WHERE ID=" + id);
			if (rs.next()) {
				Admin ad = extract(rs);
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
	public boolean addElement(Admin admin) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO admin VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, admin.getId());
			ps.setString(2, admin.getFirstName());
			ps.setString(3, admin.getLastName());
			ps.setString(4, admin.getNationalId());
			ps.setString(5, admin.getPhoneNo());
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
	public boolean deleteElement(Admin admin) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
		    int i = st.executeUpdate("DELETE FROM admin WHERE ID=" + admin.getId());
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
	public boolean updateElement(Admin admin) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE admin SET First_name=?, Last_name=?, National_ID=?, Phone_Number=? WHERE ID=?");
			ps.setString(1, admin.getFirstName());
			ps.setString(2, admin.getLastName());
			ps.setString(3, admin.getNationalId());
			ps.setString(4, admin.getPhoneNo());
			ps.setInt(5, admin.getId());
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
	public Admin extract(java.sql.ResultSet rs) throws SQLException {
		Admin admin = new Admin();
		admin.setId(rs.getInt("Id"));
		admin.setFirstName(rs.getString("First_name"));
		admin.setLastName(rs.getString("Last_name"));
		admin.setNationalId(rs.getString("National_ID"));
		admin.setPhoneNo(rs.getString("Phone_Number"));
		return admin;
	}

	
}