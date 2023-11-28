package org.bihe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

public interface Dao<T> {
	
	public Set<T> getAllElements();
	
	public T getElement(int id);
	
	public boolean addElement(T t);
	
	public boolean deleteElement(T t);
	
	public boolean updateElement(T t);
	
	/**
	 * This method for connecting to database
	 * @return connection interface
	 */
	@SuppressWarnings("deprecation")
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/hotelmanagementproject?user=root";
		    con = DriverManager.getConnection(url);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
		
		return con;
	}
}