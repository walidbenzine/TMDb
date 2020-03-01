package com.sciruse.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sciruse.models.Comments;
import com.sciruse.models.Episode;
import com.sciruse.models.Film;
import com.sciruse.models.Genre;
import com.sciruse.models.Saison;
import com.sciruse.models.Serie;
import com.sciruse.models.Actors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImportFunctions {


	private final static OkHttpClient httpClient = new OkHttpClient();
	private static String Base_url="https://api.themoviedb.org/3/";
	private static String API_Key="94327dc22a17d2c12b806d241682cd96";
	
	private static Vector<String> idsActor=new Vector<String>(); 
	private static Vector<String> idsFilms=new Vector<String>(); 
	private static Vector<String> idsSerie=new Vector<String>(); 
	
	public ImportFunctions() {
		
	}
	public static void main(String[] args) {
		Vector<Film>films;
		Vector<Genre>genres;
		Vector<Comments>comments;
		Vector<Actors>actors;
		Vector<Serie> series;
		Actors act;
		try {
			//films = MoviesPopular(Base_url+"movie/popular?api_key="+API_Key+"&language=fr&page=1");

			//we need to give it the movie id 
			//comments = Comment(Base_url+"movie/419704/reviews?api_key="+API_Key+"&language=en-US&page=1");
			//actors = Actors(Base_url+"movie/419704/credits?api_key="+API_Key);
			//act = getActorInfo(Base_url+"person/287?api_key="+API_Key+"&language=en-US");
			//series = Serie(Base_url+"tv/popular?api_key="+API_Key+"&language=fr&page=1");
			//actors=Actors(Base_url+"tv/456/credits?api_key="+API_Key+"&language=en-US");
			Saison e =  getSaisonInfo(Base_url+"tv/456/season/1?api_key="+API_Key+"&language=en-US");
			System.out.println(e);
		}catch (Exception e) {System.out.println(e);}


	}

	public static  JSONObject GetMyJson(String url) throws IOException {
		JSONObject object;
		Request request = new Request.Builder()
				.url(url)
				.build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

			object = new JSONObject(response.body().string());
		}
		return object;
	}

	public static  Vector<Film> MoviesPopular(String url) throws IOException {

		Vector<Film>films =new Vector<Film>();
		ArrayList<String> listdata;      
		JSONObject movie;

		JSONObject object = GetMyJson(url);

		JSONArray liJsonArray = object.getJSONArray("results");
		String id;
		Film a =null ;
		for (int i = 0; i < liJsonArray.length(); i++) {
			JSONObject obj =(JSONObject) liJsonArray.get(i);
			id = obj.get("id").toString();
			listdata= new ArrayList<String>();
			
			if(idsActor.contains(id)==false) {
				idsActor.add(id);
			a= getFilmInfo(Base_url+"movie/"+id+"?api_key="+API_Key+"&language=fr");
			films.add(a);
			}
		}
		return films; 
	}

	//Base_url+"person/287?api_key="+API_Key+"&language=en-US"
	public static  Vector<Actors> Actors(String url) throws IOException {
		Vector<Actors> actors=new Vector<Actors>(); 
				String id;
		JSONObject object = GetMyJson(url);
		JSONArray actorArray = object.getJSONArray("cast");
		Actors act = null;
		if (actorArray != null) { 
			for (int j=0;j<actorArray.length();j++){ 
				JSONObject obj =(JSONObject) actorArray.get(j);
				id = obj.get("id").toString();
				if(idsActor.contains(id)==false) {
					idsActor.add(id);
					act = getActorInfo(Base_url+"person/"+id+"?api_key="+API_Key+"&language=fr");
					actors.add(act);
				}
				
			} 
		} 

		return actors;
	}

	//            get/movie/{movie_id}
	public static  Film getFilmInfo(String url) throws IOException {
		Film film = new Film();
		Genre g =null;
		JSONObject object = GetMyJson(url);

		film.setTitle(object.get("title").toString());
		film.setID((Integer) object.get("id"));
		film.setResume(object.get("overview").toString());
		film.setDateSortie(object.get("release_date").toString()); 
		film.setImage(object.get("poster_path").toString());
		film.setNote(object.get("vote_average").toString());
		film.setGenre(genre(object));

		return film;
	}
	
	
	public static  List<Film> getFilmBiblio(String url) throws IOException {
		List<Film>films =new Vector<Film>();
		String id;
		JSONObject object = GetMyJson(url);
		JSONArray FilmArray = object.getJSONArray("cast");
		Film a =null ;
		if (FilmArray != null) { 
			int x=FilmArray.length();
			if(x>4) { x = 4;}
			for (int j=0;j<x;j++){ 

				JSONObject obj =(JSONObject) FilmArray.get(j);
				id = obj.get("id").toString();
				if(idsActor.contains(id)==false) {
					idsActor.add(id);
				a= getFilmInfo(Base_url+"movie/"+id+"?api_key="+API_Key+"&language=fr");
				films.add(a);
				}
			} 
		} 

		return films;
	}


	public static  Actors getActorInfo(String url) throws IOException {
		Actors actor = new Actors();
		JSONObject myobj;
		JSONObject object = GetMyJson(url);
		actor.setDateNaissance(object.get("birthday").toString());  ;
		actor.setLieuNaissance(object.get("place_of_birth").toString());
		actor.setNom(object.get("name").toString());
		actor.setPhoto(object.get("profile_path").toString());
		actor.setBibliographie(object.get("biography").toString());
		actor.setPopularite(object.get("popularity").toString());
		actor.setId((Integer) object.get("id"));
		return actor;
	}

	public static  Vector<Comments> Comment(String url) throws IOException {
		Vector<Comments> comments=new Vector<Comments>(); 
		JSONObject object = GetMyJson(url);
		JSONArray commment = object.getJSONArray("results");
		Comments com = null;
		if (commment != null) { 
			for (int j=0;j<commment.length();j++){ 
				com =new Comments();
				JSONObject obj =(JSONObject) commment.get(j);
				com.setUser(obj.get("author").toString());
				com.setText(obj.getString("content").toString());
				
				
				comments.add(com);
			} 
		} 

		return comments;
	}

	public static Vector<Genre> genre(JSONObject myJsonObject) throws IOException {
		//https://api.themoviedb.org/3/genre/movie/list?api_key=94327dc22a17d2c12b806d241682cd96&language=fr
		Vector<Genre> genres=new Vector<Genre>(); 
		JSONObject object = myJsonObject;
		JSONArray genreArr = object.getJSONArray("genres");
		Genre g =null;

		if (genreArr != null) { 
			for (int j=0;j<genreArr.length();j++){ 
				g =new Genre();
				JSONObject obj =(JSONObject) genreArr.get(j);
				g.setId(obj.get("id").toString());
				g.setDesig(obj.getString("name").toString());
				genres.add(g);
			} 
		} 

		return genres;
	}


	public static  Vector<Serie> Serie(String url) throws IOException {
		Vector<Serie> series=new Vector<Serie>();
		List<Saison> saisons =null;
		String id;
		JSONObject object = GetMyJson(url);
		JSONArray serieArray = object.getJSONArray("results");
		Serie serie = null;
		if (serieArray != null) { 
			for (int j=0;j<serieArray.length();j++){ 

				serie = new Serie();
				JSONObject obj =(JSONObject) serieArray.get(j);
				id = obj.get("id").toString();
				if(idsSerie.contains(id)==false) {
					idsSerie.add(id);
				
				serie.setId((Integer) obj.get("id"));
				serie.setResume(obj.get("overview").toString());
				serie.setDateSortie(obj.get("first_air_date").toString());
				serie.setNote(obj.get("vote_average").toString());
				serie.setPath(obj.get("poster_path").toString());
				serie.setTitle(obj.get("original_name").toString());

				serie = setSaisonEpisodeCount(id, serie);

				serie.setComments(Comment(Base_url+"tv/"+id+"/reviews?api_key="+API_Key+"&language=&page=1"));
				
				serie.setGenre(genre(GetMyJson(Base_url+"tv/"+id+"?api_key="+API_Key+"&language=fr&page=1")));
				
				serie.setActors(Actors(Base_url+"tv/"+id+"/credits?api_key="+API_Key+"&language=en-US"));
				
				for (int i = 1; i < Integer.parseInt( serie.getNbrSaison()); i++) {
					saisons = new ArrayList<Saison>();
					saisons.add(getSaisonInfo(Base_url+"tv/"+id+"/season/"+i+"?api_key="+API_Key+"&language=en-US"));
					
				}
				
				serie.setSaisons(saisons);
				series.add(serie);
				}
			} 
		} 

		return series;
	}


	public static Serie setSaisonEpisodeCount(String movieId,Serie serie) throws IOException {

		JSONObject myobj;
		JSONObject object = GetMyJson(Base_url+"tv/"+movieId+"?api_key="+API_Key+"&language=en-US");

		serie.setNbrEpisodes(object.get("number_of_episodes").toString());
		serie.setNbrSaison(object.get("number_of_seasons").toString());
		return serie;
	}
	
	
	public static  Saison getSaisonInfo(String url) throws IOException {
		List<Episode> ep = new ArrayList<Episode>();
		Saison saison = new Saison();
		JSONObject myobj;
		JSONObject object = GetMyJson(url);
		JSONArray EpisodArray = object.getJSONArray("episodes");
		saison.setId((int) object.get("id"));
		saison.setDetails(object.get("overview").toString());
		saison.setDateSortie(object.get("air_date").toString());
		saison.setNom(object.get("name").toString());
		saison.setNbrEpisodes(EpisodArray.length());
		saison.setImage(object.get("poster_path").toString());
		
		Episode E;
		
		for (int i = 0; i < EpisodArray.length(); i++) {
			E = new Episode();
			JSONObject obj =(JSONObject) EpisodArray.get(i);
			E.setResume(obj.get("overview").toString());
			E.setDate_Diff(obj.get("air_date").toString());
			E.setId((Integer) obj.get("id"));
			E.setName(obj.get("name").toString());
			E.setNumber(obj.get("episode_number").toString());
			E.setImage(obj.get("still_path").toString());
			
			//bd to  be added
			ep.add(E);
			
		}
		
		saison.setListEpisodes(ep);
		return saison;
	}
	

	



}

