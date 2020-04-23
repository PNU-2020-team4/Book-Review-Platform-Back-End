package com.team4.bookreview.vo;

import java.util.Date;

public class PostVO {
	// 게시글 인덱스 
	int idx;
	// 제목 
	String title;
	// 작성자 
	String writer;
	// 게시글 내용 
	String content;
	// 댓글 수 
	int cmt_cnt;
	// 조회 수 
	int view_cnt;
	// 작성 시각 
	Date date;
}
