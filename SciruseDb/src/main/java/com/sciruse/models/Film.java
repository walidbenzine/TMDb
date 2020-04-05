package com.sciruse.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.sciruse.models.Actors;
import org.hibernate.validator.constraints.UniqueElements;
@Entity
public class Film {
	
	@Id 	
	@Column(unique = true)
	private Integer ID;

	private String title ;
	@Column(columnDefinition="LONGTEXT")
	private String resume;
	private	 String note ;
	private String dateSortie ;
	private String image;
	//private List<Film>filmsLiees ;
	@ManyToMany (cascade = {CascadeType.ALL})
	private List<Genre>genre = new ArrayList<Genre>();
	
	@ManyToMany (cascade = {CascadeType.ALL})
	private List<Actors> actors;
	
	//private List<String>realisator ;
	@ManyToMany (cascade = {CascadeType.ALL})
	private List<Comments>comments = new ArrayList<Comments>();


	
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	
	
	
	public List<Actors> getActors() {
		return actors;
	}
	public void setActors(List<Actors> actors) {
		this.actors = actors;
	}
	public Film() {
		
	}
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
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}

	public List<Genre> getGenre() {
		return genre;
	}
	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Film [ID=" + ID + ", title=" + title + ", resume=" + resume + ", note=" + note
				+ ", dateSortie=" + dateSortie +", image=" + image + ", genre=" + genre
				+ "]";
	}
	
	
	



}
