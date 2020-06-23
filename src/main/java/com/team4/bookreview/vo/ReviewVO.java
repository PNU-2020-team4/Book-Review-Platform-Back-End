package com.team4.bookreview.vo;

import java.util.Date;

/**
 * @author KwanwooKim
 * @date 2020-04-22
 */
public class ReviewVO extends DataVO {
	
	int idx;
	int writer;
	String content;
	double star;
	int book;
	Date date;
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getWriter() {
		return writer;
	}

	public void setWriter(int writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public int getBook() {
		return book;
	}

	public void setBook(int book) {
		this.book = book;
	}
	
	public Date getDate() {
		return date ;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
