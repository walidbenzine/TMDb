 package com.sciruse.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Actors {

	@Id
	@GeneratedValue
	private Long id;
	private String  nom ;
	private String tdm_id;
	private String lieuNaissance ;
	private String bibliographie ;
	private	String popularite ;
	private String dateNaissance ;
	//private	List<Film>filmographie ;
	private	String photo ; //TYPE DE VARIABLE A CHANGER
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	public String getBibliographie() {
		return bibliographie;
	}
	public void setBibliographie(String bibliographie) {
		this.bibliographie = bibliographie;
	}
	public String getPopularite() {
		return popularite;
	}
	public void setPopularite(String popularite) {
		this.popularite = popularite;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
	public String getTdm_id() {
		return tdm_id;
	}
	public void setTdm_id(String tdm_id) {
		this.tdm_id = tdm_id;
	}
	@Override
	public String toString() {
		return "Actors [id=" + id + ", nom=" + nom + ", tdm_id=" + tdm_id + ", lieuNaissance=" + lieuNaissance
				+ ", bibliographie=" + bibliographie + ", popularite=" + popularite + ", dateNaissance=" + dateNaissance
				+ ", photo=" + photo + "]";
	}
	
	
	
	
	
	
	
}
