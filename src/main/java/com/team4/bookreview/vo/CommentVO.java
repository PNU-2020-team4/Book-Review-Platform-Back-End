package com.team4.bookreview.vo;

import java.util.Date;

public class CommentVO {
	int post;
	int cmt_no;
	int writer;
	String content;
	Date date;
	int parent;
	boolean delFlag;
	
	public int getPost() {
		return post;
	}
	public void setPost(int post) {
		this.post = post;
	}
	public boolean getdelFlag() {
		return delFlag;
	}
	public void setdelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	public int getCmt_no() {
		return cmt_no;
	}
	public void setCmt_no(int cmt_no) {
		this.cmt_no = cmt_no;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return "CommentVO [post=" + post + ", cmt_no=" + cmt_no + ", writer=" + writer + ", content=" + content
				+ ", date=" + date + ", parent=" + parent + "]";
	}

}
