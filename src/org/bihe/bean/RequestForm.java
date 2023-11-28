package org.bihe.bean;

import java.util.ArrayList;
import java.util.Collections;

import org.bihe.dao.HotelManagerDaoImpl;
import org.bihe.dao.RequestFormDaoImpl;

public class RequestForm implements Cloneable {
	private int id;
	private boolean breakfast;
	private boolean lunch;
	private boolean dinner;
	private boolean pool;
	private boolean gym;
	private boolean wifi;
	private HotelManager hotelManager;

	// -----------------------------Constructors------------------------

	public RequestForm(boolean breakfast, boolean lunch, boolean dinner, boolean pool, boolean gym, boolean wifi,
			HotelManager hotelManager) {

		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		this.pool = pool;
		this.gym = gym;
		this.wifi = wifi;
		this.hotelManager = hotelManager;
		this.id = generateId();
	}

	public RequestForm() {

	}

	// -------------------------------Mutator-----------------------------

	public void setHotelManager(HotelManager hotelManager) {
		this.hotelManager = hotelManager;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public void setLunch(boolean lunch) {
		this.lunch = lunch;
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

	// --------------------------------Accessor--------------------------------

	public HotelManager getHotelManager() {
		return hotelManager;
	}

	public int getId() {
		return id;
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

	// ----------------------------Generating Id for RequestForm------------------
	private static int generateId() {
		if (RequestFormDaoImpl.getInstance().getAllElements().isEmpty()) {
			return 1;
		} else
			return getHighestId() + 1;
	}

	private static int getHighestId() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (RequestForm hm : RequestFormDaoImpl.getInstance().getAllElements()) {
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
		RequestForm other = (RequestForm) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	protected RequestForm clone() throws CloneNotSupportedException {
		RequestForm cloned = new RequestForm();
		cloned.id = this.id;
		cloned.hotelManager = this.hotelManager.clone();
		cloned.breakfast = this.breakfast;
		cloned.lunch = this.lunch;
		cloned.pool = this.pool;
		cloned.gym = this.gym;
		cloned.wifi = this.wifi;
		return cloned;
	}

}
