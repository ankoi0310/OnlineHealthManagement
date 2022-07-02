package dto.patient;

public class PatientCreate {
	private String id;
	private String fullname;
	private int age;
	private boolean male;

	public PatientCreate() {
	}

	public PatientCreate(String id, String fullname, int age, boolean male) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.age = age;
		this.male = male;
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
