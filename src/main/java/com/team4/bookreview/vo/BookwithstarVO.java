package com.team4.bookreview.vo;

public class BookwithstarVO extends BookVO {
	private float star;
	public float getStar() {
		return this.star;
	}
	public void setStar(float star) {
		this.star = star;
	}
	@Override
	public String toString() {
		return super.toString() + "[ star : " + star + " ]";
	}
}
