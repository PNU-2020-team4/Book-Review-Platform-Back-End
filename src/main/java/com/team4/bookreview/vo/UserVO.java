package com.team4.bookreview.vo;

import java.io.Serializable;

public class UserVO extends DataVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	int id;
	String email;
	String name;
	String nickname;
	String age;
	String profile_image;
	String gender;
	int hist_cnt;
	boolean withdrawal;
	String birthday;

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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getHist_cnt() {
		return hist_cnt;
	}
	public void setHist_cnt(int hist_cnt) {
		this.hist_cnt = hist_cnt;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}


	public boolean isWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(boolean withdrawal) {
		this.withdrawal = withdrawal;
	}

	@Override
	public String toString() {
		return "UserVO [age=" + age + ", birthday=" + birthday + ", email=" + email + ", gender=" + gender
				+ ", hist_cnt=" + hist_cnt + ", id=" + id + ", name=" + name + ", nickname=" + nickname
				+ ", profile_image=" + profile_image + ", withdraw=" + withdrawal + "]";
	}

	
}
