package dto.user;

public class UserRegister {
	private String id;
	private String fullname;
	private int age;
	private boolean male;
	private String phone;
	private String password;
	private String address;

	public UserRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegister(String id, String fullname, int age, boolean male, String phone, String password, String address) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.age = age;
		this.male = male;
		this.phone = phone;
		this.password = password;
		this.address = address;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
