package org.bihe.bean;

import java.util.ArrayList;
import java.util.Collections;

import org.bihe.dao.AddressDaoImpl;
import org.bihe.dao.HotelManagerDaoImpl;

public class Address implements Comparable<Address>, Cloneable{
	private int id;
	private String city;
	private String street;

	// -----------------------------Constructors------------------------

	public Address(String city, String street) {
		this.city = city;
		this.street = street;
		this.id = generateId();
	}

	public Address() {

	}
	
	// -------------------------------Mutator-----------------------------

	public void setCity(String city) {
		this.city = city;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	// --------------------------------Accessor--------------------------------

	public String getCity() {
		return city;
	}

	public int getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	// ----------------------------Generating Id for Address------------------
		private static int generateId() {
			if(AddressDaoImpl.getInstance().getAllElements().isEmpty()) {
				return 1;
			}
			else return getHighestId() + 1;
		}
		
		private static int getHighestId(){
			ArrayList<Integer> ids = new ArrayList<Integer>();
			for (Address hm : AddressDaoImpl.getInstance().getAllElements()) {
				ids.add(hm.getId());
			}
			Collections.sort(ids);
			return ids.get(ids.size() - 1);

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
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
			Address other = (Address) obj;
			if (id != other.id)
				return false;
			return true;
		}

		protected Address clone() throws CloneNotSupportedException {
			Address cloned = new Address();
			cloned.id = this.id;
			cloned.city = new String(this.city);
			cloned.street = new String(this.street);
			return cloned;
		}

		@Override
		public int compareTo(Address o) {
			return this.getCity().compareToIgnoreCase(o.getCity());
		}
		
}
