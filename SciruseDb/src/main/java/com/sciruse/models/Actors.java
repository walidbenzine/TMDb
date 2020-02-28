 package com.sciruse.models;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Actors {

	@Id
	@Column(unique = true, nullable = false)
	private Integer id;
	private String  nom ;
	private String lieuNaissance ;
	@Column(columnDefinition="LONGTEXT")
	private String bibliographie ;
	private	String popularite ;
	private String dateNaissance ;
	@ManyToMany (cascade = {CascadeType.ALL})
	@JsonIgnore
	@Column(unique = false,nullable = true)
	private	List<Film>filmographie ;
	private	String photo ; //TYPE DE VARIABLE A CHANGER
	
	
	public List<Film> getFilmographie() {
		return filmographie;
	}
	public void setFilmographie(List<Film> filmographie) {
		this.filmographie = filmographie;
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
	

	@Override
	public String toString() {
		return "Actors [id=" + id + ", nom=" + nom + ", lieuNaissance=" + lieuNaissance
				+ ", bibliographie=" + bibliographie + ", popularite=" + popularite + ", dateNaissance=" + dateNaissance
				+ ", photo=" + photo + "]";
	}
	
	
	
	
	
	
	
}
