package com.sciruse.models;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Saison {

	@Id 
	private Integer id;
	private String nom ;
	@Column(columnDefinition="LONGTEXT")
	private String details;
	private String dateSortie ;
	private int nbrEpisodes;
	private String image;
	
	
	@ManyToMany (cascade = {CascadeType.ALL})
	private	 List<Episode> listEpisodes;
	

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(String dateSortie) {
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
	
	@Override
	public String toString() {
		return "Saison [nom=" + nom + ", details=" + details + ", dateSortie=" + dateSortie + ", nbrEpisodes="
				+ nbrEpisodes + ", listEpisodes=" + listEpisodes + ", actors=" ;
	} 
	
	
}
