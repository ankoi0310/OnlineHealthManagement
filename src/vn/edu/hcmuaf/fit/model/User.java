package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.dto.Role;

public class User {
	private String id;
	private String fullname;
	private int age;
	private boolean male;
	private String phone;
	private String password;
	private String address;
	private Role role;

	public User() {
	}

	public User(String id, String fullname, int age, boolean male, String phone, String password, String address, Role role) {
		this.id = id;
		this.fullname = fullname;
		this.age = age;
		this.male = male;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.role = role;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
