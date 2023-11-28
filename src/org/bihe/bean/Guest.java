package org.bihe.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.bihe.dao.GuestDaoImpl;
import org.bihe.dao.ReserveDaoImpl;

public class Guest extends Person implements Cloneable{
	private int id;
	private Address address;

	// -----------------------------Constructors------------------------

	public Guest(String firstName, String lastName, String nationalId, Address address, String phoneNo) {
		super(firstName, lastName, nationalId, phoneNo);
		this.address = address;
		this.id = generateId();
	}
	
	public Guest() {
		
	}
	
	// --------------------------------Accessor--------------------------------

	public Address getAddress() {
		return address;
	}

	public int getId() {
		return id;
	}
	
	public ArrayList<Reserve> getReservations(){
		ArrayList<Reserve> resrevations = new ArrayList<>();
		for (Reserve reservation : ReserveDaoImpl.getInstance().getAllElements()) {
			if(reservation.getGuest().equals(this)) {
				resrevations.add(reservation);
			}
		}
		return resrevations;
	}
	
	// -------------------------------Mutator-----------------------------


	public void setAddress(Address address) {
		this.address = address;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	// ----------------------------Generating Id for guest------------------
	private static int generateId() {
		if(GuestDaoImpl.getInstance().getAllElements().isEmpty()) {
			return 2;
		}
		else return getHighestId() + 2;
	}
	
	private static int getHighestId(){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Guest guest : GuestDaoImpl.getInstance().getAllElements()) {
			ids.add(guest.getId());
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
		Guest other = (Guest) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Guest [id=" + id + ", address=" + address + "]";
	}
	
	@Override
	protected Guest clone() throws CloneNotSupportedException {
		Guest cloned = new Guest();
		cloned.id = this.id;
		cloned.address = this.address.clone();
		return cloned;
	}
	
}
