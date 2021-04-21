package model;

public class Passenger {

	private int id;
	private String fullName;
	private String email;
	private int age;
	private String mobileNumber;
	private String gender;
	public Passenger(int id, String fullName, String email, String gender, int age, String mobileNumber) {
		this.id=id;
		this.fullName=fullName;
		this.email= email;
		this.gender= gender;
		this.age= age;
		this.mobileNumber= mobileNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
