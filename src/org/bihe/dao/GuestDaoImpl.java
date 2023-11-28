package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


import org.bihe.bean.Guest;

public class GuestDaoImpl implements Dao<Guest> , Extractor<Guest>{

	private static GuestDaoImpl instance;

	private GuestDaoImpl() {
	}

	public static GuestDaoImpl getInstance() {
		if (instance == null) {
			instance = new GuestDaoImpl();
		}
		return instance;
	}
	@Override
	public Set<Guest> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM guest");
			Set<Guest> guests = new HashSet<Guest>();
			
			while(rs.next()) {
				Guest guest = extract(rs);
				guests.add(guest);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return guests;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public Guest getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM guest WHERE ID=" + id);
			if (rs.next()) {
				Guest ad = extract(rs);
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
	public boolean addElement(Guest guest) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO guest VALUES (?, ?, ?, ?, ?, ?)");
			ps.setInt(1, guest.getId());
			ps.setString(2, guest.getFirstName());
			ps.setString(3, guest.getLastName());
			ps.setString(4, guest.getNationalId());
			ps.setString(5, guest.getPhoneNo());
			ps.setInt(6, guest.getAddress().getId());
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
	public boolean deleteElement(Guest guest) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
		    int i = st.executeUpdate("DELETE FROM guest WHERE ID=" + guest.getId());
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
	public boolean updateElement(Guest guest) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE guest SET First_name=?, Last_name=?, National_ID=?, Phone_Number=?, Address_ID=? WHERE ID=?");
			ps.setString(1, guest.getFirstName());
			ps.setString(2, guest.getLastName());
			ps.setString(3, guest.getNationalId());
			ps.setString(4, guest.getPhoneNo());
			ps.setInt(5, guest.getAddress().getId());
			ps.setInt(6, guest.getId());
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
	public Guest extract(java.sql.ResultSet rs) throws SQLException {
		Guest guest = new Guest();
		guest.setId(rs.getInt("ID"));
		guest.setFirstName(rs.getString("First_name"));
		guest.setLastName(rs.getString("Last_name"));
		guest.setNationalId(rs.getString("National_ID"));
		guest.setPhoneNo(rs.getString("Phone_Number"));
		guest.setAddress(AddressDaoImpl.getInstance().getElement(rs.getInt("Address_ID")));
		return guest;
	}

	
}