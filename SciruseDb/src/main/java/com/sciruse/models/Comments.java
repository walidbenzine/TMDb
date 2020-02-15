package com.sciruse.models;

import java.util.Date;

public class Comments {

	private String text  ;
	private String TypeComment;
	private Date dateComment ;
	
	private String user;
	
	
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTypeComment() {
		return TypeComment;
	}
	public void setTypeComment(String typeComment) {
		TypeComment = typeComment;
	}
	public Date getDateComment() {
		return dateComment;
	}
	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Comments [text=" + text + ", TypeComment=" + TypeComment + ", dateComment=" + dateComment + ", user="
				+ user + "]";
	}
	
	
}
