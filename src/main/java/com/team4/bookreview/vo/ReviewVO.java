package com.team4.bookreview.vo;

import java.sql.Timestamp;

/**
 * @author KwanwooKim
 * @date 2020-04-22
 */
public class ReviewVO extends DataVO {
	
	int idx;
	int writer;
	String content;
	int star;
	int book;
	Timestamp date;
	
	public ReviewVO(int writer, String content, int star, int book, Timestamp date) {
		super();
		this.writer = writer;
		this.content = content;
		this.star = star;
		this.book = book;
		this.date = date;
	}
	
	public ReviewVO(int idx, int writer, String content, int star, int book, Timestamp date) {
		super();
		this.idx = idx;
		this.writer = writer;
		this.content = content;
		this.star = star;
		this.book = book;
		this.date = date;
	}
	
	@Override
	public String toString() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "ReviewVO [idx=" + idx + ", writer=" + writer + ", content=" + content + ", star=" + star + ", book="
				+ book + ", date=" + sdf.format(date) + "]";
	}

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

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getBook() {
		return book;
	}

	public void setBook(int book) {
		this.book = book;
	}
	
	public Timestamp getDate() {
		return date ;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
