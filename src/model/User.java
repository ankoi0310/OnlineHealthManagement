package model;

public class User {
	private Long id;
	private String fullname;
	private String phone;
	private String address;
	private int roleId;

	public User() {
	}

	public User(Long id, String fullname, String phone, String address, int roleId) {
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
