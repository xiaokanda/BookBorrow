package top.vist.bookborrow.entity;

public class Reader {
	private String readerId;
	private Integer type;
	private String name;
	private Integer age;
	private String sex;
	private String phone;
	private String dapt;
	private String regDate;
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDapt() {
		return dapt;
	}
	public void setDapt(String dapt) {
		this.dapt = dapt;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Reader [readerId=" + readerId + ", type=" + type + ", name=" + name + ", age=" + age + ", sex=" + sex
				+ ", phone=" + phone + ", dapt=" + dapt + ", regDate=" + regDate + "]";
	}
	
}
