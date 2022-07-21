package vn.edu.hcmuaf.fit.model;

import java.io.Serializable;

public class Patient implements Serializable {
	private String id;
	private String fullname;
	private int age;
	private boolean male;

	public Patient() {
	}

	public Patient(String id, String fullname, int age, boolean male) {
		this.id = id;
		this.fullname = fullname;
		this.age = age;
		this.male = male;
	}

	public Patient(Patient patient) {
		id = patient.getId();
		fullname = patient.getFullname();
		age = patient.getAge();
		male = patient.isMale();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

}
