package com.team4.bookreview.vo;

import java.io.Serializable;
import java.sql.Date;

public class UserVO implements Serializable {
	
	int id;
	String email;
	String name;
	String nick;
	String age;
	char gender;
	int hist_cnt;
	String birth;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getHist_cnt() {
		return hist_cnt;
	}
	public void setHist_cnt(int hist_cnt) {
		this.hist_cnt = hist_cnt;
	}
	public String getBirthday() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", email=" + email + ", name=" + name + ", nick=" + nick + ", age=" + age
				+ ", gender=" + gender + ", hist_cnt=" + hist_cnt + ", birth=" + birth + "]";
	}
}
