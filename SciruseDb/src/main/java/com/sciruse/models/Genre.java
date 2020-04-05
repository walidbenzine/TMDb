package com.sciruse.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Genre {

	@Id @GeneratedValue
	private Integer id;
	private String id_tmdb;
	private String desig;
	

	public Genre() {
		// TODO Auto-generated constructor stub
	}

	


	public String getId_tmdb() {
		return id_tmdb;
	}




	public void setId_tmdb(String id_tmdb) {
		this.id_tmdb = id_tmdb;
	}




	public void setId(Integer id) {
		this.id = id;
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
		return "[idtmdb=" + id_tmdb + ", desig=" + desig + "]";
	}
	
	
	
}
