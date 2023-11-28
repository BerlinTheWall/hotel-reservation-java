package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bihe.Enum.RoomType;
import org.bihe.bean.Room;

public class RoomDaoImpl implements Dao<Room> , Extractor<Room>{

	private static RoomDaoImpl instance;

	private RoomDaoImpl() {
	}

	public static RoomDaoImpl getInstance() {
		if (instance == null) {
			instance = new RoomDaoImpl();
		}
		return instance;
	}
	@Override
	public Set<Room> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM room");
			Set<Room> rooms = new HashSet<Room>();
			
			while(rs.next()) {
				Room room = extract(rs);
				rooms.add(room);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return rooms;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public Room getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM room WHERE ID=" + id);
			if (rs.next()) {
				Room ad = extract(rs);
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
	public boolean addElement(Room room) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO room VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, room.getId());
			ps.setInt(2, room.getHotel().getId());
			ps.setInt(3, room.getRoomNo());
			ps.setDouble(4, room.getPrice());
			ps.setBoolean(5, room.isEmpty());
			ps.setString(6, room.getRoomType().toString());
			ps.setBoolean(7, room.isBreakfast());
			ps.setBoolean(8, room.isLunch());
			ps.setBoolean(9, room.isDinner());
			ps.setBoolean(10, room.isPool());
			ps.setBoolean(11, room.isGym());
			ps.setBoolean(12, room.isWifi());
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
	public boolean deleteElement(Room room) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
		    int i = st.executeUpdate("DELETE FROM room WHERE ID=" + room.getId());
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
	public boolean updateElement(Room room) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE room SET Hotel_ID=?, Room_No=?, Price=?, Is_Empty=?, Room_Type=?, Breakfast=?, Lunch=?, Dinner=?, Swimming_Pool=?, Gym=?, Wifi=? WHERE ID=?");
			ps.setInt(1, room.getHotel().getId());
			ps.setInt(2, room.getRoomNo());
			ps.setDouble(3, room.getPrice());
			ps.setBoolean(4, room.isEmpty());
			ps.setString(5, room.getRoomType().toString());
			ps.setBoolean(6, room.isBreakfast());
			ps.setBoolean(7, room.isLunch());
			ps.setBoolean(8, room.isDinner());
			ps.setBoolean(9, room.isPool());
			ps.setBoolean(10, room.isGym());
			ps.setBoolean(11, room.isWifi());
			ps.setInt(12, room.getId());
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
	public Room extract(java.sql.ResultSet rs) throws SQLException {
		Room room = new Room();
		room.setId(rs.getInt("ID"));
		room.setHotel(HotelDaoImpl.getInstance().getElement(rs.getInt("Hotel_ID")));
		room.setRoomNo(rs.getInt("Room_No"));
		room.setPrice(rs.getDouble("Price"));
		room.setEmpty(rs.getBoolean("Is_Empty"));
		room.setRoomType(RoomType.valueOf(rs.getString("Room_Type")));
		room.setBreakfast(rs.getBoolean("Breakfast"));
		room.setLunch(rs.getBoolean("Lunch"));
		room.setDinner(rs.getBoolean("Dinner"));
		room.setPool(rs.getBoolean("Swimming_Pool"));
		room.setGym(rs.getBoolean("Gym"));
		room.setWifi(rs.getBoolean("Wifi"));
		return room;
	}
	
	
}