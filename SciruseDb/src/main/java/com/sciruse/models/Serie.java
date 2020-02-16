package com.sciruse.models;

import java.util.Date;
import java.util.List;

public class Serie {

	private String title;
			private String resume ;
			private Date dateSortie ;
			private int note ;
			private int nbrEpisodes;
			private int nbrSaison ;
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
			public Date getDateSortie() {
				return dateSortie;
			}
			public void setDateSortie(Date dateSortie) {
				this.dateSortie = dateSortie;
			}
			public int getNote() {
				return note;
			}
			public void setNote(int note) {
				this.note = note;
			}
			public int getNbrEpisodes() {
				return nbrEpisodes;
			}
			public void setNbrEpisodes(int nbrEpisodes) {
				this.nbrEpisodes = nbrEpisodes;
			}
			public int getNbrSaison() {
				return nbrSaison;
			}
			public void setNbrSaison(int nbrSaison) {
				this.nbrSaison = nbrSaison;
			}
			@Override
			public String toString() {
				return "Serie [title=" + title + ", resume=" + resume + ", dateSortie=" + dateSortie + ", note=" + note
						+ ", nbrEpisodes=" + nbrEpisodes + ", nbrSaison=" + nbrSaison + "]";
			}
			
			
			
}
