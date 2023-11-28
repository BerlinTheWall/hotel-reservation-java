package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bihe.bean.Hotel;

public class HotelDaoImpl implements Dao<Hotel> , Extractor<Hotel>{

	private static HotelDaoImpl instance;

	private HotelDaoImpl() {
	}

	public static HotelDaoImpl getInstance() {
		if (instance == null) {
			instance = new HotelDaoImpl();
		}
		return instance;
	}
	@Override
	public Set<Hotel> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM hotel");
			Set<Hotel> hotels = new HashSet<Hotel>();
			
			while(rs.next()) {
				Hotel hotel = extract(rs);
				hotels.add(hotel);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return hotels;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public Hotel getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM hotel WHERE ID=" + id);
			if (rs.next()) {
				Hotel ad = extract(rs);
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
	public boolean addElement(Hotel hotel) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO hotel VALUES (?, ?, ?, ?, ?, ?)");
			ps.setInt(1, hotel.getId());
			ps.setString(2, hotel.getHotelName());
			ps.setInt(3, hotel.getAddress().getId());
			ps.setInt(4, hotel.getStar());
			ps.setInt(5, hotel.getRoomNo());
			ps.setString(6, hotel.getPhoneNo());
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
	public boolean deleteElement(Hotel hotel) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
		    int i = st.executeUpdate("DELETE FROM hotel WHERE ID=" + hotel.getId());
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
	public boolean updateElement(Hotel hotel) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE hotel SET Hotel_name=?, Address_ID=?, Star=?, Room_Number=?, Phone_Number=? WHERE ID=?");
			ps.setString(1, hotel.getHotelName());
			ps.setInt(2, hotel.getAddress().getId());
			ps.setInt(3, hotel.getStar());
			ps.setInt(4, hotel.getRoomNo());
			ps.setString(5, hotel.getPhoneNo());
			ps.setInt(6, hotel.getId());
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
	public Hotel extract(java.sql.ResultSet rs) throws SQLException {
		Hotel hotel = new Hotel();
		hotel.setId(rs.getInt("ID"));
		hotel.setHotelName(rs.getString("Hotel_name"));
		hotel.setAddress(AddressDaoImpl.getInstance().getElement(rs.getInt("Address_ID")));
		hotel.setStar(rs.getInt("Star"));
		hotel.setRoomNo(rs.getInt("Room_Number"));
		hotel.setPhoneNo(rs.getString("Phone_Number"));
		return hotel;
	}

	
}