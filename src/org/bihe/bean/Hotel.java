package org.bihe.bean;

import java.util.ArrayList;
import java.util.Collections;

import org.bihe.dao.HotelDaoImpl;
import org.bihe.dao.HotelManagerDaoImpl;

public class Hotel implements Comparable<Hotel>, Cloneable{
	private int id;
	private String hotelName;
	private Address address;
	private String phoneNo;
	private int star;
	private int roomNo;

	// -----------------------------Constructors------------------------

	public Hotel(String hotelName, int roomNo, Address address, String phoneNo, int star) {
		this.hotelName = hotelName;
		this.roomNo = roomNo;
		this.address = address;
		this.phoneNo = phoneNo;
		this.star = star;
		this.id = generateId();

	}

	public Hotel() {

	}
	// -------------------------------Mutator-----------------------------

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	// --------------------------------Accessor--------------------------------

	public String getHotelName() {
		return hotelName;
	}

	public int getId() {
		return id;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public int getStar() {
		return star;
	}

	public int getRoomNo() {
		return roomNo;
	}
	
	// ----------------------------Generating Id for Hotel------------------
		private static int generateId() {
			if(HotelDaoImpl.getInstance().getAllElements().isEmpty()) {
				return 1;
			}
			else return getHighestId() + 1;
		}
		
		private static int getHighestId(){
			ArrayList<Integer> ids = new ArrayList<Integer>();
			for (Hotel h : HotelDaoImpl.getInstance().getAllElements()) {
				ids.add(h.getId());
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
			Hotel other = (Hotel) obj;
			if (id != other.id)
				return false;
			return true;
		}

		@Override
		public int compareTo(Hotel o) {
			return this.getHotelName().compareToIgnoreCase(o.getHotelName()); 
				
		}

		@Override
		protected Hotel clone() throws CloneNotSupportedException {
			Hotel cloned = new Hotel();
			cloned.id = this.id;
			cloned.hotelName = new String(this.hotelName);
			cloned.address = this.address.clone();
			cloned.phoneNo = new String(this.phoneNo);
			cloned.star = this.star;
			cloned.roomNo = this.roomNo;
			return cloned;
		}

		
}
