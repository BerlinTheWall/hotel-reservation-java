package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bihe.bean.RequestForm;


public class RequestFormDaoImpl implements Dao<RequestForm> , Extractor<RequestForm>{

	private static RequestFormDaoImpl instance;

	private RequestFormDaoImpl() {
	}

	public static RequestFormDaoImpl getInstance() {
		if (instance == null) {
			instance = new RequestFormDaoImpl();
		}
		return instance;
	}
	@Override
	public Set<RequestForm> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM requestform");
			Set<RequestForm> requestForms = new HashSet<RequestForm>();
			
			while(rs.next()) {
				RequestForm requestForm = extract(rs);
				requestForms.add(requestForm);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return requestForms;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public RequestForm getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM requestform WHERE ID=" + id);
			if (rs.next()) {
				RequestForm ad = extract(rs);
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
	public boolean addElement(RequestForm requestForm) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO requestform VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, requestForm.getId());
			ps.setInt(2, requestForm.getHotelManager().getId());
			ps.setBoolean(3, requestForm.isBreakfast());
			ps.setBoolean(4, requestForm.isLunch());
			ps.setBoolean(5, requestForm.isDinner());
			ps.setBoolean(6, requestForm.isPool());
			ps.setBoolean(7, requestForm.isGym());
			ps.setBoolean(8, requestForm.isWifi());
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
	public boolean deleteElement(RequestForm requestForm) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
		    int i = st.executeUpdate("DELETE FROM requestform WHERE ID=" + requestForm.getId());
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
	public boolean updateElement(RequestForm requestForm) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE room SET Hotel_Manager_ID=?, Breakfast=?, Lunch=?, Dinner=?, Swimming_Pool=?, Gym=?, Wifi=? WHERE ID=?");
			ps.setInt(1, requestForm.getHotelManager().getId());
			ps.setBoolean(2, requestForm.isBreakfast());
			ps.setBoolean(3, requestForm.isLunch());
			ps.setBoolean(4, requestForm.isDinner());
			ps.setBoolean(5, requestForm.isPool());
			ps.setBoolean(6, requestForm.isGym());
			ps.setBoolean(7, requestForm.isWifi());
			ps.setInt(8, requestForm.getId());
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
	public RequestForm extract(java.sql.ResultSet rs) throws SQLException {
		RequestForm requestForm = new RequestForm();
		requestForm.setId(rs.getInt("ID"));
		requestForm.setHotelManager(HotelManagerDaoImpl.getInstance().getElement(rs.getInt("Hotel_Manager_ID")));
		requestForm.setBreakfast(rs.getBoolean("Breakfast"));
		requestForm.setLunch(rs.getBoolean("Lunch"));
		requestForm.setDinner(rs.getBoolean("Dinner"));
		requestForm.setPool(rs.getBoolean("Swimming_Pool"));
		requestForm.setGym(rs.getBoolean("Gym"));
		requestForm.setWifi(rs.getBoolean("Wifi"));
		return requestForm;
	}
	
	
}