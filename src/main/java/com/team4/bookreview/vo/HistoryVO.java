package com.team4.bookreview.vo;

import java.util.Date;

public class HistoryVO extends DataVO{
	Date date;
	int book;
	int user;
	int hist_no;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getHist_no() {
		return hist_no;
	}
	public void setHist_no(int hist_no) {
		this.hist_no = hist_no;
	}
}
