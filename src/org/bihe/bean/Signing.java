package org.bihe.bean;

import java.util.ArrayList;
import java.util.Collections;

import org.bihe.dao.HotelManagerDaoImpl;
import org.bihe.dao.SigningDaoImpl;

public class Signing<T> {
	private int id;
	private String username;
	private String password;
	private T object;

	// -----------------------------Constructors------------------------

	public Signing(String username, String password, T object) {
		this.username = username;
		this.password = password;
		this.object = object;
		this.id = generateId();
	}

	public Signing() {

	}
	
	// -------------------------------Mutator-----------------------------

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setObject(T object) {
		this.object = object;
	}

	// --------------------------------Accessor--------------------------------

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public T getObject() {
		return object;
	}

	// ----------------------------Generating Id for Signing------------------
		private static int generateId() {
			if(SigningDaoImpl.getInstance().getAllElements().isEmpty()) {
				return 1;
			}
			else return getHighestId() + 1;
		}
		
		private static int getHighestId(){
			ArrayList<Integer> ids = new ArrayList<Integer>();
			for (Signing hm : SigningDaoImpl.getInstance().getAllElements()) {
				ids.add(hm.getId());
			}
			Collections.sort(ids);
			return ids.get(ids.size() - 1);

		}
		
		
	@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Signing other = (Signing) obj;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}

	@Override
	public String toString() {
		return "Signing [username=" + username + ", password=" + password + ", object=" + object + "]";
	}

}
