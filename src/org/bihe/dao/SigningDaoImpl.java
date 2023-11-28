package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bihe.bean.Admin;
import org.bihe.bean.Guest;
import org.bihe.bean.HotelManager;
import org.bihe.bean.Signing;

public class SigningDaoImpl implements Dao<Signing>, Extractor<Signing> {

	private static SigningDaoImpl instance;

	private SigningDaoImpl() {
	}

	public static SigningDaoImpl getInstance() {
		if (instance == null) {
			instance = new SigningDaoImpl();
		}
		return instance;
	}

	@Override
	public Set<Signing> getAllElements() {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM signing");
			Set<Signing> signings = new HashSet<Signing>();

			while (rs.next()) {
				Signing signing = extract(rs);
				signings.add(signing);
			}
			st.close();
			con.close();
			Dao.connect().close();
			return signings;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Signing getElement(int id) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM signing WHERE ID=" + id);
			if (rs.next()) {
				Signing ad = extract(rs);
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
	public boolean addElement(Signing signing) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO signing VALUES (?, ?, ?, ?)");
			ps.setInt(1, signing.getId());
			ps.setString(2, signing.getUsername());
			ps.setString(3, signing.getPassword());
			if (signing.getObject() instanceof HotelManager) {
				HotelManager hm = (HotelManager) signing.getObject();
				ps.setInt(4, hm.getId());
			} else if (signing.getObject() instanceof Guest) {
				Guest g = (Guest) signing.getObject();
				ps.setInt(4, g.getId());
			} else if (signing.getObject() instanceof Admin) {
				Admin a = (Admin) signing.getObject();
				ps.setInt(4, a.getId());
			}
			int i = ps.executeUpdate();
			if (i == 1) {
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
	public boolean deleteElement(Signing signing) {
		Connection con = Dao.connect();
		try {
			java.sql.Statement st = con.createStatement();
			int i = st.executeUpdate("DELETE FROM signing WHERE ID=" + signing.getId());
			if (i == 1) {
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
	public boolean updateElement(Signing signing) {
		Connection con = Dao.connect();
		try {
			PreparedStatement ps = con
					.prepareStatement("UPDATE signing SET Username=?, Password=?, Object_ID=? WHERE ID=?");
			ps.setString(1, signing.getUsername());
			ps.setString(2, signing.getPassword());
			if (signing.getObject() instanceof HotelManager) {
				HotelManager hm = (HotelManager) signing.getObject();
				ps.setInt(3, hm.getId());
			} else if (signing.getObject() instanceof Guest) {
				Guest g = (Guest) signing.getObject();
				ps.setInt(3, g.getId());
			} else if (signing.getObject() instanceof Admin) {
				Admin a = (Admin) signing.getObject();
				ps.setInt(3, a.getId());
			}
			ps.setInt(4, signing.getId());
			int i = ps.executeUpdate();
			if (i == 1) {
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
	public Signing extract(java.sql.ResultSet rs) throws SQLException {
		Signing signing = new Signing();
		signing.setId(rs.getInt("ID"));
		signing.setUsername(rs.getString("Username"));
		signing.setPassword(rs.getString("Password"));
		if(rs.getInt("Object_ID") == 0) {
			signing.setObject(AdminDaoImpl.getInstance().getElement(0));
		} else if(rs.getInt("Object_ID")%2 == 0) {
			signing.setObject(GuestDaoImpl.getInstance().getElement(rs.getInt("Object_ID")));
		} else if(rs.getInt("Object_ID")%2 == 1) {
			signing.setObject(HotelManagerDaoImpl.getInstance().getElement(rs.getInt("Object_ID")));
		}
		return signing;
	}

}