package com.sciruse.models;

public class Genre {

	
	private String id_tmdb;
	private String desig;
	
	
	public Genre() {
		// TODO Auto-generated constructor stub
	}



	public String getId() {
		return id_tmdb;
	}



	public void setId(String id) {
		this.id_tmdb = id;
	}



	public String getDesig() {
		return desig;
	}



	public void setDesig(String desig) {
		this.desig = desig;
	}



	@Override
	public String toString() {
		return "Genre [id=" + id_tmdb + ", desig=" + desig + "]";
	}
	
	
	
}
