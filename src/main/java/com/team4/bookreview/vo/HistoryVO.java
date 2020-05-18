package com.team4.bookreview.vo;

import java.util.Date;

public class HistoryVO extends DataVO{
	Date date;
	String book;
	String book_author;
	int user;
	int hist_no;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
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
