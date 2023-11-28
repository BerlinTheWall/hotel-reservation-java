package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bihe.bean.HotelManager;

public class HotelManagerDaoImpl implements Dao<HotelManager> , Extractor<HotelManager>{

	private static HotelManagerDaoImpl instance;

	private HotelManagerDaoImpl() {
	}

	public static HotelManagerDaoImpl getInstance() {
		if (instance == null) {
			instance = new HotelManagerDaoImpl();
		}
		return instance;
	}
	@Override
	public Set<HotelManager> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM hotelmanager");
			Set<HotelManager> hotelmanagers = new HashSet<HotelManager>();
			
			while(rs.next()) {
				HotelManager hotelmanager = extract(rs);
				hotelmanagers.add(hotelmanager);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return hotelmanagers;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public HotelManager getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM hotelmanager WHERE ID=" + id);
			if (rs.next()) {
				HotelManager ad = extract(rs);
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
	public boolean addElement(HotelManager hotelmanager) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO hotelmanager VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, hotelmanager.getId());
			ps.setString(2, hotelmanager.getFirstName());
			ps.setString(3, hotelmanager.getLastName());
			ps.setInt(4, hotelmanager.getHotel().getId());
			ps.setString(5, hotelmanager.getNationalId());
			ps.setString(6, hotelmanager.getPhoneNo());
			ps.setBoolean(7, hotelmanager.isAccepted());
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
	public boolean deleteElement(HotelManager hotelmanager) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
		    int i = st.executeUpdate("DELETE FROM hotelmanager WHERE ID=" + hotelmanager.getId());
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
	public boolean updateElement(HotelManager hotelmanager) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE hotelmanager SET First_name=?, Last_name=?, Hotel_ID=?, National_ID=?, Phone_Number=?, Is_Accepted=? WHERE ID=?");
			ps.setString(1, hotelmanager.getFirstName());
			ps.setString(2, hotelmanager.getLastName());
			ps.setInt(3, hotelmanager.getHotel().getId());
			ps.setString(4, hotelmanager.getNationalId());
			ps.setString(5, hotelmanager.getPhoneNo());
			ps.setBoolean(6, hotelmanager.isAccepted());
			ps.setInt(7, hotelmanager.getId());
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
	public HotelManager extract(java.sql.ResultSet rs) throws SQLException {
		HotelManager hotelmanager = new HotelManager();
		hotelmanager.setId(rs.getInt("ID"));
		hotelmanager.setFirstName(rs.getString("First_name"));
		hotelmanager.setLastName(rs.getString("Last_name"));
		hotelmanager.setHotel(HotelDaoImpl.getInstance().getElement(rs.getInt("Hotel_ID")));
		hotelmanager.setNationalId(rs.getString("National_ID"));
		hotelmanager.setPhoneNo(rs.getString("Phone_Number"));
		hotelmanager.setAccepted(rs.getBoolean("Is_Accepted"));
		return hotelmanager;
	}

	
}