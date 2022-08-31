package com.prac.utube.vo;

public class Expression {
	private int idx;
	private int boardidx;
	private String user_email;
	private int user_expression;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoardidx() {
		return boardidx;
	}
	public void setBoardidx(int boardidx) {
		this.boardidx = boardidx;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getUser_expression() {
		return user_expression;
	}
	public void setUser_expression(int user_expression) {
		this.user_expression = user_expression;
	}
	@Override
	public String toString() {
		return "Expression [idx=" + idx + ", boardidx=" + boardidx + ", user_email=" + user_email + ", user_expression="
				+ user_expression + "]";
	}
	
	public Expression() {
		
	}
}
