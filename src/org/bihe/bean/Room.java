package org.bihe.bean;

import java.util.ArrayList;
import java.util.Collections;

import org.bihe.Enum.RoomType;
import org.bihe.dao.HotelManagerDaoImpl;
import org.bihe.dao.RoomDaoImpl;

public class Room implements Comparable<Room>, Cloneable {
	private int id;
	private Hotel hotel;
	private int RoomNo;
	private boolean isEmpty;
	private double price;
	private RoomType roomType;
	private boolean breakfast;
	private boolean lunch;
	private boolean dinner;
	private boolean pool;
	private boolean gym;
	private boolean wifi;

	// -----------------------------Constructors------------------------
	public Room(Hotel hotel, int roomNo, double price, RoomType roomType, boolean breakfast, boolean lunch,
			boolean dinner, boolean pool, boolean gym, boolean wifi) {

		this.hotel = hotel;
		this.RoomNo = roomNo;
		this.price = price;
		this.roomType = roomType;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		this.pool = pool;
		this.gym = gym;
		this.wifi = wifi;
		this.isEmpty = true;
		this.id = generateId();
	}

	public Room() {

	}

	public Room(Hotel hotel, int roomNo, boolean breakfast, boolean lunch, boolean dinner, boolean pool,
			boolean gym, boolean wifi, RoomType roomType) {
		this.hotel = hotel;
		this.RoomNo = roomNo;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		this.pool = pool;
		this.gym = gym;
		this.wifi = wifi;
		this.roomType = roomType;
		this.isEmpty = true;
		this.id = generateId();
	}

	// --------------------------------Accessors--------------------------------
	public Hotel getHotel() {
		return hotel;
	}

	public int getId() {
		return id;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public int getRoomNo() {
		return RoomNo;
	}

	public double getPrice() {
		return price;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public boolean isLunch() {
		return lunch;
	}

	public boolean isDinner() {
		return dinner;
	}

	public boolean isPool() {
		return pool;
	}

	public boolean isGym() {
		return gym;
	}

	public boolean isWifi() {
		return wifi;
	}

	// -------------------------------Mutators-----------------------------
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRoomNo(int roomNo) {
		RoomNo = roomNo;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public void setDinner(boolean dinner) {
		this.dinner = dinner;
	}

	public void setPool(boolean pool) {
		this.pool = pool;
	}

	public void setGym(boolean gym) {
		this.gym = gym;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	// ----------------------------Generating Id for Room------------------
	private static int generateId() {
		if (RoomDaoImpl.getInstance().getAllElements().isEmpty()) {
			return 1;
		} else
			return getHighestId() + 1;
	}

	private static int getHighestId() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Room hm : RoomDaoImpl.getInstance().getAllElements()) {
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
		Room other = (Room) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		return sb.toString();
	}

	@Override
	public int compareTo(Room o) {
		return Integer.valueOf(this.getRoomNo()).compareTo(Integer.valueOf(o.getRoomNo()));
	}

	@Override
	protected Room clone() throws CloneNotSupportedException {
		Room cloned = new Room();
		cloned.id = this.id;
		cloned.hotel = this.hotel.clone();
		cloned.RoomNo = this.RoomNo;
		cloned.isEmpty = this.isEmpty;
		cloned.roomType = this.roomType;
		cloned.breakfast = this.breakfast;
		cloned.lunch = this.lunch;
		cloned.dinner = this.dinner;
		cloned.pool = this.pool;
		cloned.gym = this.gym;
		cloned.wifi = this.wifi;
		return cloned;
	}

}
