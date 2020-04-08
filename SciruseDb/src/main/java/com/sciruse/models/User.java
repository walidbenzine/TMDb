package com.sciruse.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "id", updatable = false, nullable = false)
private Long id;


private String nom;
private String prenom;
private String email;
private String login;
private String password;
private String tel;
private int jeton ;
private String picture ;

@ManyToMany (cascade = {CascadeType.ALL})
@JsonIgnore
@Column(unique = false,nullable = true)
private  List<Film>FilmFavoris = new ArrayList<Film>();  

@ManyToMany (cascade = {CascadeType.ALL})
@JsonIgnore
@Column(unique = false,nullable = true)
private List<Film>Filmhistory = new ArrayList<Film>() ; 

@ManyToMany (cascade = {CascadeType.ALL})
@JsonIgnore
@Column(unique = false,nullable = true)
private List<Serie>SerieFavoris = new ArrayList<Serie>(); 

@ManyToMany (cascade = {CascadeType.ALL})
@JsonIgnore
@Column(unique = false,nullable = true)
private  List<Serie>Seriehistory = new ArrayList<Serie>();

@ManyToMany (cascade = {CascadeType.ALL})
@JsonIgnore
@Column(unique = false,nullable = true)
private List<Genre>genrePref =  new ArrayList<Genre>(); 

public Long getId() {
	return id;
}
public void setJeton(Long id) {
	this.id = id;
}


public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public List<Film> getFilmFavoris() {
	return FilmFavoris;
}
public void setFilmFavoris(Film film) {
	this.FilmFavoris.add(film);
}
public List<Film> getFilmhistory() {
	return Filmhistory;
}
public void setFilmhistory(Film film) {
	this.Filmhistory.add(film);
}
public List<Serie> getSerieFavoris() {
	return SerieFavoris;
}
public void setSerieFavoris(Serie serie) {
	this.SerieFavoris.add(serie);
}
public List<Serie> getSeriehistory() {
	return Seriehistory;
}
public void setSeriehistory(Serie serie) {
	this.Seriehistory.add(serie);
}
public List<Genre> getGenrePref() {
	return genrePref;
}
public void setGenrePref(List<Genre> genrePref) {
	this.genrePref = genrePref;
}
public int getJeton() {
	return jeton;
}
public void setJeton(int jeton) {
	this.jeton = jeton;
}
public String getPicture() {
	return picture;
}
public void setPicture(String picture) {
	this.picture = picture;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
} 

}
