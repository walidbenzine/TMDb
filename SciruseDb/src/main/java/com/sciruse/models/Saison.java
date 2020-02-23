package com.sciruse.models;

import java.util.Date;
import java.util.List;

public class Saison {

	private String nom ;
	private String details;
	private Date dateSortie ;
	private int nbrEpisodes;
	private	 List<Episode> listEpisodes;
	private  List<Actors>actors;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}
	public int getNbrEpisodes() {
		return nbrEpisodes;
	}
	public void setNbrEpisodes(int nbrEpisodes) {
		this.nbrEpisodes = nbrEpisodes;
	}
	public List<Episode> getListEpisodes() {
		return listEpisodes;
	}
	public void setListEpisodes(List<Episode> listEpisodes) {
		this.listEpisodes = listEpisodes;
	}
	public List<Actors> getActors() {
		return actors;
	}
	public void setActors(List<Actors> actors) {
		this.actors = actors;
	}
	@Override
	public String toString() {
		return "Saison [nom=" + nom + ", details=" + details + ", dateSortie=" + dateSortie + ", nbrEpisodes="
				+ nbrEpisodes + ", listEpisodes=" + listEpisodes + ", actors=" + actors + "]";
	} 
	
	
}
