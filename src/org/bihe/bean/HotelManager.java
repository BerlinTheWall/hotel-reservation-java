package org.bihe.bean;

import java.util.ArrayList;
import java.util.Collections;

import org.bihe.dao.GuestDaoImpl;
import org.bihe.dao.HotelManagerDaoImpl;
import org.bihe.dao.RequestFormDaoImpl;

public class HotelManager extends Person implements Cloneable{
	private int id;
	private Hotel hotel;
	private boolean isAccepted;

	// -----------------------------Constructors------------------------

	public HotelManager(Hotel hotel, String firstName, String lastName, String nationalId, String phoneNo) {
		super(firstName, lastName, nationalId, phoneNo);
		this.hotel = hotel;
		this.id = generateId();
		this.isAccepted = false;
	}

	public HotelManager() {

	}

	// --------------------------------Accessor--------------------------------

	public Hotel getHotel() {
		return hotel;
	}

	public int getId() {
		return id;
	}
	
	public boolean isAccepted() {
		return isAccepted;
	}
	// -------------------------------Mutator-----------------------------

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	// ----------------------------Generating Id for Hotel Manager------------------
	private static int generateId() {
		if(HotelManagerDaoImpl.getInstance().getAllElements().isEmpty()) {
			return 1;
		}
		else return getHighestId() + 2;
	}
	
	private static int getHighestId(){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (HotelManager hm : HotelManagerDaoImpl.getInstance().getAllElements()) {
			ids.add(hm.getId());
		}
		Collections.sort(ids);
		return ids.get(ids.size() - 1);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelManager other = (HotelManager) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	protected HotelManager clone() throws CloneNotSupportedException {
		HotelManager cloned = new HotelManager();
		cloned.id = this.id;
		cloned.hotel = this.hotel;
		return cloned;
	}


}
