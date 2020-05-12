package com.team4.bookreview.vo;

public class BookVO extends DataVO {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	String name;
	String author;
	int idx;
	@Override
	public String toString() {
		return "BookVO [name=" + name + ", author=" + author + ", idx=" + idx + "]";
	}
	
	
	
}
