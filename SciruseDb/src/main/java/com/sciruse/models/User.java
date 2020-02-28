package com.sciruse.models;

import java.util.List;

public class User {

private String name;
private String prenom;
private String email;
private String password;
private String tel;

private  List<Film>FilmFavoris ;  //A CHANGER
 private List<Film>Filmhistory ;  //peut etre
 private List<Serie>SerieFavoris; //en hashmap
private  List<Serie>Seriehistory;  //pour les favoris

 private List<String>genrePref;  //PEUT ETRE A CHANGER
 private int jeton ;
 private String picture ; //TYPE DE VARIABLE A CHANGER
}
