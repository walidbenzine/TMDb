package com.sciruse.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Comments {
	
	@Id @GeneratedValue
	private Integer id;
	@Column(columnDefinition="LONGTEXT")
	private String text  ;
	private String user;
	private String type;
	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Comments [id=" + id + ", text=" + text + ", user=" + user + "]";
	}
	
	
	
}

