package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bihe.bean.Address;

public class AddressDaoImpl implements Dao<Address> , Extractor<Address>{

	private static AddressDaoImpl instance;

	private AddressDaoImpl() {
	}

	public static AddressDaoImpl getInstance() {
		if (instance == null) {
			instance = new AddressDaoImpl();
		}
		return instance;
	}
	@Override
	public Set<Address> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM address");
			Set<Address> addresses = new HashSet<Address>();
			
			while(rs.next()) {
				Address address = extract(rs);
				addresses.add(address);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return addresses;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public Address getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM address WHERE Id=" + id);
			if (rs.next()) {
				Address ad = extract(rs);
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
	public boolean addElement(Address address) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO address VALUES (?, ?, ?)");
			ps.setInt(1, address.getId());
			ps.setString(2, address.getCity());
			ps.setString(3, address.getStreet());
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
	public boolean deleteElement(Address address) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
		    int i = st.executeUpdate("DELETE FROM address WHERE Id=" + address.getId());
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
	public boolean updateElement(Address address) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE address SET City=?, Street=? WHERE Id=?");
			ps.setString(1, address.getCity());
			ps.setString(2, address.getStreet());
			ps.setInt(3, address.getId());
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
	public Address extract(java.sql.ResultSet rs) throws SQLException {
		Address address = new Address();
		address.setId(rs.getInt("Id"));
		address.setCity(rs.getString("City"));
		address.setStreet(rs.getString("Street"));
		return address;
	}

}
