package project;

public class Request {
	private String name;
	private String sex;
	private int age;
	private String CMND;
	private String address;
	private String reason;

	public Request(String name, String sex, int age, String cMND, String address, String reason) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		CMND = cMND;
		this.address = address;
		this.reason = reason;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String toString() {
		return "Name :" + this.name + "\nSex: " + this.sex + "\nAge: " + this.age + "\nCMND: " + this.CMND
				+ "\nAddress: " + this.address + "\nReason: " + this.reason;
	}
}
