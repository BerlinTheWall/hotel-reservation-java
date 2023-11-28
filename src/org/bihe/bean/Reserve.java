package org.bihe.bean;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

import org.bihe.dao.HotelManagerDaoImpl;
import org.bihe.dao.ReserveDaoImpl;

public class Reserve implements Cloneable {
	private int id;
	private Hotel hotel;
	private Room room;
	private Guest guest;
	private MyDate startReservation;
	private MyDate endReservation;
	private double price;

	// -----------------------------Constructors------------------------

	public Reserve(Hotel hotel, Room room, Guest guest, MyDate startReservation, MyDate endReservation) {

		this.hotel = hotel;
		this.room = room;
		this.guest = guest;
		this.startReservation = startReservation;
		this.endReservation = endReservation;
		this.price = generatePrice();
		this.id = generateId();
	}

	public Reserve() {

	}
	// -------------------------------Mutator-----------------------------

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public void setStartReservation(MyDate startReservation) {
		this.startReservation = startReservation;
	}

	public void setEndReservation(MyDate endReservation) {
		this.endReservation = endReservation;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// --------------------------------Accessor--------------------------------

	public Hotel getHotel() {
		return hotel;
	}

	public int getId() {
		return id;
	}

	public Room getRoom() {
		return room;
	}

	public Guest getGuest() {
		return guest;
	}

	public MyDate getStartReservation() {
		return startReservation;
	}

	public MyDate getEndReservation() {
		return endReservation;
	}

	public double getPrice() {
		return price;
	}

	private double generatePrice() {
		int days = (int) ChronoUnit.DAYS.between(MyDate.convertMydatToLocalDate(startReservation), MyDate.convertMydatToLocalDate(endReservation));
		return this.room.getPrice()*days;
	}
	// ----------------------------Generating Id for Reserve------------------
	private static int generateId() {
		if (ReserveDaoImpl.getInstance().getAllElements().isEmpty()) {
			return 1;
		} else
			return getHighestId() + 1;
	}

	private static int getHighestId() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Reserve hm : ReserveDaoImpl.getInstance().getAllElements()) {
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
		Reserve other = (Reserve) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	protected Reserve clone() throws CloneNotSupportedException {
		Reserve cloned = new Reserve();
		cloned.id = this.id;
		cloned.hotel = this.hotel.clone();
		cloned.room = this.room.clone();
		cloned.guest = this.guest.clone();
		cloned.startReservation = this.startReservation.clone();
		cloned.endReservation = this.endReservation.clone();
		cloned.price = this.price;
		return cloned;
	}

}
