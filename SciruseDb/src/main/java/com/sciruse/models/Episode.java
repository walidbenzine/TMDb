package com.sciruse.models;

public class Episode {

	private String resume ;
	private String bandeAnnonce ;
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
