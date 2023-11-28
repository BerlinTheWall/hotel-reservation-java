package org.bihe.bean;

public class Admin extends Person implements Cloneable{
	private int id;

	// -----------------------------Constructors------------------------

	public Admin(String firstName, String lastName, String nationalId, String phoneNo) {
		super(firstName, lastName, nationalId, phoneNo);
		this.id = 0;
	}

	public Admin() {

	}
	// --------------------------------Accessor--------------------------------

	public int getId() {
		return id;
	}


	// -------------------------------Mutator-----------------------------

	public void setId(int id) {
		this.id = id;
	}

	@Override
	protected Admin clone() throws CloneNotSupportedException {
		Admin cloned = new Admin();
		cloned.id = this.id;
		return cloned;
	}

	
	
}
