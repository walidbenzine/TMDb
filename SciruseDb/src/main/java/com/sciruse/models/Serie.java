package com.sciruse.models;

import java.util.Date;
import java.util.List;

public class Serie {

			private String id_tmdb;
			private String title;
			private String resume ;
			private String dateSortie ;
			private String note ;
			private String nbrEpisodes;
			private String nbrSaison ;
			private String Path;
			//private List<Saison>saisons ;
			//private  List<Serie>seriesLiees;
			//private List<Comments>comments ;
			//private  List<String>genre ;
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
			
			
			
			public String getId_tmdb() {
				return id_tmdb;
			}
			public void setId_tmdb(String id_tmdb) {
				this.id_tmdb = id_tmdb;
			}
			@Override
			public String toString() {
				return "Serie [title=" + title + ", resume=" + resume + ", dateSortie=" + dateSortie + ", note=" + note
						+ ", nbrEpisodes=" + nbrEpisodes + ", nbrSaison=" + nbrSaison + ", Path=" + Path + "]";
			}
		
			
			
			
}
