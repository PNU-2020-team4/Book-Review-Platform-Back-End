package com.team4.bookreview.vo;

/**
 * @author KwanwooKim
 * @date 2020-04-22
 */
public class ReviewVO {
	int idx;
	String writer;
	String content;
	int star;
	int book;
	
	@Override
	public String toString() {
		return "ReviewVO [idx=" + idx + ", writer=" + writer + ", content=" + content + ", star=" + star + ", book="
				+ book + "]";
	}
}
