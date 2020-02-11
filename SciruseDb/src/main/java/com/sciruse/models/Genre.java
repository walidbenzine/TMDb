package com.sciruse.models;

public class Genre {

	private String id;
	private String desig;
	
	
	public Genre() {
		// TODO Auto-generated constructor stub
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getDesig() {
		return desig;
	}



	public void setDesig(String desig) {
		this.desig = desig;
	}



	@Override
	public String toString() {
		return "Genre [id=" + id + ", desig=" + desig + "]";
	}
	
	
	
}
