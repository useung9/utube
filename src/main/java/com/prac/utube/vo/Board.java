package com.prac.utube.vo;

import java.util.Date;

public class Board {
	private int board_idx;
	private String board_writer;
	private String board_title;
	private String board_content;
	private String board_url;
	private String board_thum;
	private int board_views;
	private int board_likes;
	private int board_dislikes;
	private Date board_regidate;
	
	
	public int getBoard_views() {
		return board_views;
	}
	public void setBoard_views(int board_views) {
		this.board_views = board_views;
	}
	public int getBoard_likes() {
		return board_likes;
	}
	public void setBoard_likes(int board_likes) {
		this.board_likes = board_likes;
	}
	public int getBoard_dislikes() {
		return board_dislikes;
	}
	public void setBoard_dislikes(int board_dislikes) {
		this.board_dislikes = board_dislikes;
	}
	public Date getBoard_regidate() {
		return board_regidate;
	}
	public void setBoard_regidate(Date board_regidate) {
		this.board_regidate = board_regidate;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getBoard_thum() {
		return board_thum;
	}
	public void setBoard_thum(String board_thum) {
		this.board_thum = board_thum;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_url() {
		return board_url;
	}
	public void setBoard_url(String board_url) {
		this.board_url = board_url;
	}
	
	
	
	@Override
	public String toString() {
		return "Board [board_idx=" + board_idx + ", board_writer=" + board_writer + ", board_title=" + board_title
				+ ", board_content=" + board_content + ", board_url=" + board_url + ", board_thum=" + board_thum
				+ ", board_views=" + board_views + ", board_likes=" + board_likes + ", board_dislikes=" + board_dislikes
				+ ", board_regidate=" + board_regidate + "]";
	}
	public Board() {
		
	}
	
	
}
