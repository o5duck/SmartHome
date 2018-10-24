package mvc.model.vo;

import java.util.List;

public class AuthVo{
	private String userId;
	private String pwd;
	private String name;
	private String tel;
	private String address;
	private String mail;
	private String position;
	private String grade;
	private String className;
	private List<String> clubs;
	private List<String> subjects;
	private List<String> generals;
	
	public AuthVo(String userId, String pwd, String name, String tel, String address, String mail, String position, String grade, String className){
		this.userId = userId;
		this.pwd = pwd;
		this.tel = tel;
		this.name = name;
		this.address = address;
		this.mail = mail;
		this.position = position;
		this.grade = grade;
		this.className = className;
		this.clubs = null;
		this.subjects = null;
		this.generals = null;
	}
	
	public void setClubs(List<String> clubs) {
		this.clubs = clubs;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}
	
	public void setGenerals(List<String> generals){
		this.generals = generals;
	}

	public String getUserId() {
		return userId;
	}
	public String getPwd() {
		return pwd;
	}
	public String getName(){
		return name;
	}
	public String getTel() {
		return tel;
	}
	public String getAddress() {
		return address;
	}
	public String getMail() {
		return mail;
	}
	public String getPosition() {
		return position;
	}
	public String getGrade() {
		return grade;
	}
	public String getClassName() {
		return className;
	}
	public List<String> getClubs() {
		return clubs;
	}
	public List<String> getSubjects() {
		return subjects;
	}
	public boolean matchPassword(String pwd){
		return this.pwd.equals(pwd);
	}
	public List<String> getGenerals(){
		return generals;
	}
}
