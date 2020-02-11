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
			private List<Saison>saisons ;
			private  List<Serie>seriesLiees;
			private List<Comments>comments ;
			private  List<String>genre ;
}
