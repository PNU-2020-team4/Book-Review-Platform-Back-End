package com.team4.bookreview.vo;

import java.util.Date;

public class PostVO extends DataVO {
	int idx;
	String title;
	String writer;
	String content;
	int cmt_cnt;
	int view_cnt;
	Date date;
	Date modified;
	boolean delFlag;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCmt_cnt() {
		return cmt_cnt;
	}

	public void setCmt_cnt(int cmt_cnt) {
		this.cmt_cnt = cmt_cnt;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "PostVO [cmt_cnt=" + cmt_cnt + ", content=" + content + ", date=" + date + ", delFlag=" + delFlag
				+ ", idx=" + idx + ", modified=" + modified + ", title=" + title + ", view_cnt=" + view_cnt
				+ ", writer=" + writer + "]";
	}

	
}
