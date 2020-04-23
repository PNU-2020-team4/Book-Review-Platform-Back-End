package com.team4.bookreview.vo;

/**
 * @author KwanwooKim
 * @date 2020-04-22
 */
public class ReviewVO {
	// 리뷰 인덱스 
	int idx;
	// 리뷰 작성자 
	String writer;
	// 리뷰 내용 
	String content;
	// 별점 
	int star;
	// 책 인덱스 
	int book;
	
	@Override
	public String toString() {
		return "ReviewVO [idx=" + idx + ", writer=" + writer + ", content=" + content + ", star=" + star + ", book="
				+ book + "]";
	}
}
