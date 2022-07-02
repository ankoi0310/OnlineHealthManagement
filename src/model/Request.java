package model;

import java.util.List;

public class Request {
	private Long id;
	private List<Patient> patients;
	private String phone;
	private String address;
	private String problemDescription;
	private int status;
	private User user;

	public Request() {
	}

	public Request(Long id, List<Patient> patients, String phone, String address, String problemDescription, int status, User user) {
		this.id = id;
		this.patients = patients;
		this.phone = phone;
		this.address = address;
		this.problemDescription = problemDescription;
		this.status = status;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
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

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
