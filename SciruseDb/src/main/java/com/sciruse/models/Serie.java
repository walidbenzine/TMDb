package com.sciruse.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Serie {

	@Id 
	@Column(unique = true,nullable = false)
	private Integer id;
	
	private String title;
	
	@Column(columnDefinition="LONGTEXT")
	private String resume ;
	
	private String dateSortie ;
	private String note ;
	private String nbrEpisodes;
	private String nbrSaison ;
	private String Image;
	
	@JsonIgnore
	@ManyToMany (cascade = {CascadeType.ALL})
	private List<Saison>saisons ;
	
	@JsonIgnore
	@ManyToMany (cascade = {CascadeType.ALL})
	@Column(unique = false,nullable = true)
	private  List<Serie>seriesLiees;
	
	@JsonIgnore
	@ManyToMany (cascade = {CascadeType.ALL})
	private List<Comments>comments = new ArrayList<Comments>();
	
	@ManyToMany (cascade = {CascadeType.ALL})
	private  List<Genre> genre =  new ArrayList<Genre>();
	
	@JsonIgnore
	@ManyToMany (cascade = {CascadeType.ALL})
	private List<Actors> actors;


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getNbrEpisodes() {
		return nbrEpisodes;
	}
	public void setNbrEpisodes(String nbrEpisodes) {
		this.nbrEpisodes = nbrEpisodes;
	}
	public String getNbrSaison() {
		return nbrSaison;
	}
	public void setNbrSaison(String nbrSaison) {
		this.nbrSaison = nbrSaison;
	}


	public String getImage() {
		return Image;
	}
	public void setImage(String Image) {
		this.Image = Image;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id_tmdb) {
		this.id = id_tmdb;
	}


	public List<Comments> getComments() {
		return comments;
	}
	
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

	public List<Actors> getActors() {
		return actors;
	}
	public void setActors(List<Actors> actors) {
		this.actors = actors;
	}
	
	
	public List<Saison> getSaisons() {
		return saisons;
	}
	public void setSaisons(List<Saison> saisons) {
		this.saisons = saisons;
	}
	
	public List<Serie> getSeriesLiees() {
		return seriesLiees;
	}
	public void setSeriesLiees(Serie seriesLiees) {
		this.seriesLiees.add(seriesLiees);
	}
	
	@Override
	public String toString() {
		return "Serie [id=" + id + ", title=" + title + ", resume=" + resume + ", dateSortie="
				+ dateSortie + ", note=" + note + ", nbrEpisodes=" + nbrEpisodes + ", nbrSaison=" + nbrSaison
				+ ", Image=" + Image + ", comments=" + comments + ", genre=" + genre + "]";
	}











}

