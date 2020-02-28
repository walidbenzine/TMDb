package com.sciruse.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Serie {

			@Id 	
			@Column(unique = true)
			private Integer id;
			private String title;
			@Column(columnDefinition="LONGTEXT")
			private String resume ;
			private String dateSortie ;
			private String note ;
			private String nbrEpisodes;
			private String nbrSaison ;
			private String Path;
			//private List<Saison>saisons ;
			//private  List<Serie>seriesLiees;
			@ManyToMany (cascade = {CascadeType.ALL})
			private List<Comments>comments = new ArrayList<Comments>();
			@ManyToMany (cascade = {CascadeType.ALL})
			private  List<Genre> genre =  new ArrayList<Genre>();
			
			
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
			
			
			public String getPath() {
				return Path;
			}
			public void setPath(String path) {
				Path = path;
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
			@Override
			public String toString() {
				return "Serie [id=" + id + ", title=" + title + ", resume=" + resume + ", dateSortie="
						+ dateSortie + ", note=" + note + ", nbrEpisodes=" + nbrEpisodes + ", nbrSaison=" + nbrSaison
						+ ", Path=" + Path + ", comments=" + comments + ", genre=" + genre + "]";
			}
			
			
			
			
			
			
			
		
			
			
			
}
