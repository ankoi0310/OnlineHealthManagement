package dto.request;

import java.util.List;

import model.Patient;

public class RequestCreate {
	private List<Patient> patients;
	private String phone;
	private String address;
	private String problemDescription;
	private String userId;

	public RequestCreate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestCreate(List<Patient> patients, String phone, String address, String problemDescription, String userId) {
		super();
		this.phone = phone;
		this.address = address;
		this.problemDescription = problemDescription;
		this.userId = userId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
