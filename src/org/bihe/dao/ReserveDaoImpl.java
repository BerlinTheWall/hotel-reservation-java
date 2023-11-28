package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bihe.bean.Reserve;

public class ReserveDaoImpl implements Dao<Reserve> , Extractor<Reserve>{

	private static ReserveDaoImpl instance;

	private ReserveDaoImpl() {
	}

	public static ReserveDaoImpl getInstance() {
		if (instance == null) {
			instance = new ReserveDaoImpl();
		}
		return instance;
	}
	@Override
	public Set<Reserve> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM reserve");
			Set<Reserve> reserves = new HashSet<Reserve>();
			
			while(rs.next()) {
				Reserve reserve = extract(rs);
				reserves.add(reserve);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return reserves;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public Reserve getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM reserve WHERE ID=" + id);
			if (rs.next()) {
				Reserve ad = extract(rs);
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
	public boolean addElement(Reserve reserve) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO reserve VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, reserve.getId());
			ps.setInt(2, reserve.getHotel().getId());
			ps.setInt(3, reserve.getRoom().getId());
			ps.setInt(4, reserve.getGuest().getId());
			ps.setInt(5, reserve.getStartReservation().getId());
			ps.setInt(6, reserve.getEndReservation().getId());
			ps.setDouble(7, reserve.getPrice());
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
	public boolean deleteElement(Reserve reserve) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
		    int i = st.executeUpdate("DELETE FROM reserve WHERE ID=" + reserve.getId());
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
	public boolean updateElement(Reserve reserve) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE reserve SET Hotel_ID=?, Room_ID=?, Guest_ID=?, Start_Date_ID=?, End_Date_ID=?, Price=? WHERE ID=?");
			ps.setInt(1, reserve.getHotel().getId());
			ps.setInt(2, reserve.getRoom().getId());
			ps.setInt(3, reserve.getGuest().getId());
			ps.setInt(4, reserve.getStartReservation().getId());
			ps.setInt(5, reserve.getStartReservation().getId());
			ps.setDouble(6, reserve.getPrice());
			ps.setInt(7, reserve.getId());
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
	public Reserve extract(java.sql.ResultSet rs) throws SQLException {
		Reserve reserve = new Reserve();
		reserve.setId(rs.getInt("ID"));
		reserve.setHotel(HotelDaoImpl.getInstance().getElement(rs.getInt("Hotel_ID")));
		reserve.setRoom(RoomDaoImpl.getInstance().getElement(rs.getInt("Room_ID")));
		reserve.setGuest(GuestDaoImpl.getInstance().getElement(rs.getInt("Guest_ID")));
		reserve.setStartReservation(MyDateDaoImpl.getInstance().getElement(rs.getInt("Start_Date_ID")));
		reserve.setEndReservation(MyDateDaoImpl.getInstance().getElement(rs.getInt("End_Date_ID")));
		reserve.setPrice(rs.getDouble("Price"));
		return reserve;
	}

	
}