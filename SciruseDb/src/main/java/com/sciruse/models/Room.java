package com.sciruse.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {

	@Id
	@Column(unique = true, nullable = false)
	private Integer id; 
	private String nom  ;
	private String adresse; 
	private String image;  
	private Double alt; 
	private Double lang;
	private boolean fav;
	
	
	public Room() {
		
	}
	
	
	
	
	public Room(Integer id, String nom, String adresse, String image, Double alt, Double lang, boolean fav) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.image = image;
		this.alt = alt;
		this.lang = lang;
		this.fav = fav;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getAlt() {
		return alt;
	}
	public void setAlt(Double alt) {
		this.alt = alt;
	}
	public Double getLang() {
		return lang;
	}
	public void setLang(Double lang) {
		this.lang = lang;
	}
	public boolean isFav() {
		return fav;
	}
	public void setFav(boolean fav) {
		this.fav = fav;
	}
	@Override
	public String toString() {
		return "Room [nom=" + nom + ", adresse=" + adresse + ", image=" + image + ", alt=" + alt + ", lang=" + lang
				+ ", fav=" + fav + "]";
	}
	
	
	
	
	
}
