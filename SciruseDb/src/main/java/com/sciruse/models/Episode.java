package com.sciruse.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Episode {

	@Id 
	private Integer id;
	
	@Column(columnDefinition="LONGTEXT")
	private String resume ;
	
	private String bandeAnnonce ;
	private String number;
	private String Date_Diff;
	private String image;
	private String Name;
	
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDate_Diff() {
		return Date_Diff;
	}
	public void setDate_Diff(String date_Diff) {
		Date_Diff = date_Diff;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getBandeAnnonce() {
		return bandeAnnonce;
	}
	public void setBandeAnnonce(String bandeAnnonce) {
		this.bandeAnnonce = bandeAnnonce;
	}
	@Override
	public String toString() {
		return "Episode [resume=" + resume + ", bandeAnnonce=" + bandeAnnonce + "]";
	}
	
	
	
}
