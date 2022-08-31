package com.prac.utube.vo;

import java.util.Date;

public class BoardReply {
	private int reply_idx;
	private int board_idx;
	private String reply_writer;
	private String reply_content;
	private int restep;
	private int relevel;
	private Date reply_regidate;
	
	public int getReply_idx() {
		return reply_idx;
	}
	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getReply_writer() {
		return reply_writer;
	}
	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}
	public Date getReply_regidate() {
		return reply_regidate;
	}
	public void setReply_regidate(Date reply_regidate) {
		this.reply_regidate = reply_regidate;
	}
	@Override
	public String toString() {
		return "BoardReply [reply_idx=" + reply_idx + ", board_idx=" + board_idx + ", reply_writer=" + reply_writer
				+ ", reply_content=" + reply_content + ", restep=" + restep + ", relevel=" + relevel
				+ ", reply_regidate=" + reply_regidate + "]";
	}
	
	
	
}
