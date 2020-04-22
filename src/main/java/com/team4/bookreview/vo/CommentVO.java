package com.team4.bookreview.vo;

import java.util.Date;

public class CommentVO {
	// 게시글 인덱스 
	int post;
	// 게시글 내 댓글 번호 
	int cmt_no;
	// 댓글 작성자
	String writer;
	// 댓글 내용 
	String content;
	// 작성 시각 
	Date date;
	// 부모 댓글
	int parent;
}
