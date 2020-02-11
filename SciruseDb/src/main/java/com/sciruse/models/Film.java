package com.sciruse.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Film {
	
	private String ID;
	private String title ;
	private String resume;
	private	 String note ;
	private String dateSortie ;
	private String duration ;
	//private List<Film>filmsLiees ;
	private List<String>genre;
	//private List<Actors> actors;
	//private List<String>realisator ;
	//private List<Comments>comments ;


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
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public List<String> getGenre() {
		return genre;
	}
	public void setGenre(List<String> genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Film [ID=" + ID + ", title=" + title + ", resume=" + resume + ", note=" + note + ", dateSortie="
				+ dateSortie + ", duration=" + duration + ", genre=" + genre + "]";
	}
	
	
	



}
