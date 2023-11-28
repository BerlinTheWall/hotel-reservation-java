package org.bihe.bean;

import org.bihe.dao.AdminDaoImpl;
import org.bihe.dao.GuestDaoImpl;
import org.bihe.dao.HotelManagerDaoImpl;

public class Person {
	private String firstName;
	private String lastName;
	private String nationalId;
	private String phoneNo;
	
    //-----------------------------Constructors------------------------
	
	public Person(String firstName, String lastName, String nationalId, String phoneNo) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalId = nationalId;
		this.phoneNo = phoneNo;
	}
	
	public Person() {
		
	}

    //-------------------------------Mutator-----------------------------

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

    //--------------------------------Accessor-------------------------------

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNationalId() {
		return nationalId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nationalId == null) ? 0 : nationalId.hashCode());
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
		Person other = (Person) obj;
		if (nationalId == null) {
			if (other.nationalId != null)
				return false;
		} else if (!nationalId.equals(other.nationalId))
			return false;
		return true;
	}
	 
}
