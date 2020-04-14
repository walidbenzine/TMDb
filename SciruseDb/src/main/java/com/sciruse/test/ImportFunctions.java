package com.sciruse.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sciruse.models.Comments;
import com.sciruse.models.Episode;
import com.sciruse.models.Film;
import com.sciruse.models.Genre;
import com.sciruse.models.Room;
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
			//Saison e =  getSaisonInfo(Base_url+"tv/456/season/1?api_key="+API_Key+"&language=en-US");
			
			System.out.println(getMovieVideo(Base_url+"movie/654527/videos?api_key="+API_Key+"&language=en-US"));
			
			
		}catch (Exception e) {System.out.println(e);}

		//addRoom();

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
			a= getFilmInfo(Base_url+"movie/"+id+"?api_key="+API_Key+"&language=en-US");
			if(a!=null) {
			films.add(a);
			}
		}}
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
					act = getActorInfo(Base_url+"person/"+id+"?api_key="+API_Key+"&language=en-US");
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
		try {
			
		JSONObject object = GetMyJson(url);

		film.setTitle(object.get("title").toString());
		film.setID((Integer) object.get("id"));
		film.setResume(object.get("overview").toString());
		film.setDateSortie(object.get("release_date").toString()); 
		film.setImage(object.get("poster_path").toString());
		film.setNote(object.get("vote_average").toString());
		film.setGenre(genre(object));

		Vector<Comments> Comments = Comment(Base_url+"movie/"+object.get("id")+"/reviews?api_key="+API_Key+"&language=en-US&page=1");
		Iterator<Comments> value = Comments.iterator();
        while (value.hasNext()) { 
            value.next().setType("Movie");
        }
        film.setComments(Comments);

		return film;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public static  Film getFilmInfo2(String url) throws IOException {
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

		Vector<Comments> Comments = Comment(Base_url+"movie/"+object.get("id")+"/reviews?api_key="+API_Key+"&language=en-US&page=1");
		Iterator<Comments> value = Comments.iterator();
        while (value.hasNext()) { 
            value.next().setType("Movie");
        }
        film.setComments(Comments);
		
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
			if(x>15) { x = 15;}
			for (int j=0;j<x;j++){ 

				JSONObject obj =(JSONObject) FilmArray.get(j);
				id = obj.get("id").toString();
				if(idsActor.contains(id)==false) {
					idsActor.add(id);
				a= getFilmInfo(Base_url+"movie/"+id+"?api_key="+API_Key+"&language=en-US");
				films.add(a);
				}
			} 
		} 

		return films;
	}
	
	
	public static  List<Serie> getSerieBiblio(String url) throws IOException {
		List<Serie> series =new Vector<Serie>();
		String id;
		JSONObject object = GetMyJson(url);
		JSONArray SerieArray = object.getJSONArray("cast");
		Serie a =null ;
		if (SerieArray != null) { 
			int x=SerieArray.length();
			if(x>15) { x = 15;}
			for (int j=0;j<x;j++){ 

				JSONObject obj =(JSONObject) SerieArray.get(j);
				id = obj.get("id").toString();
				if(idsActor.contains(id)==false) {
					idsActor.add(id);
				a= getSerieInfo(Base_url+"tv/"+id+"?api_key="+API_Key+"&language=en-US");
				if(a!=null) {
				series.add(a);
				}
				}
			} 
		} 

		return series;
	}
	
	public static  List<Film> getFilmLiee(String url) throws IOException {
		List<Film>films =new Vector<Film>();
		String id;
		int i =0;
		JSONObject object = GetMyJson(url);
		JSONArray FilmArray = object.getJSONArray("results");
		Film a =null ;
		if (FilmArray != null) { 
			
			for (int j=0;j<FilmArray.length();j++){ 
				i++;
				JSONObject obj =(JSONObject) FilmArray.get(j);
				id = obj.get("id").toString();
				
				a= getFilmInfo2(Base_url+"movie/"+id+"?api_key="+API_Key+"&language=en-US");
				
				films.add(a);
				if(i==5)j=FilmArray.length();
			} 
		} 

		return films;
	}


	public static  Actors getActorInfo(String url) throws IOException {
		Actors actor = new Actors();
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

	public static  List<Serie> Serie(String url) throws IOException {
		List<Serie> series=new Vector<Serie>();
		String id;
		JSONObject object = GetMyJson(url);
		if(object != null) {
			JSONArray serieArray = object.getJSONArray("results");
			Serie serie = null;
			if (serieArray != null) { 
				int x = 30;
				if (serieArray.length() <= 30) {
					x = serieArray.length();
				}
				for (int j=0;j<x;j++){ 
					System.out.println("GETTING A PRIMARY SERIE INFOS " +j);
					serie = new Serie();
					JSONObject obj =(JSONObject) serieArray.get(j);
					id = obj.get("id").toString();
					serie = getSerieInfo(Base_url+"tv/"+id+"?api_key="+API_Key+"&language=en-US");
					if(serie!=null) {
						series.add(serie);
					}
				}
			} 
		}

		return series;
	}
	
	public static  Serie getSerieInfo(String url) throws IOException {
		Serie serie = new Serie();
		JSONObject object = GetMyJson(url);
		
		if(object != null) {
		String id = object.get("id").toString();
		List<Saison> saisons = new ArrayList<Saison>();

		if(idsSerie.contains(id)==false) {
			idsSerie.add(id);
		try {
		serie.setId((Integer) object.get("id"));
		serie.setResume(object.get("overview").toString());
		serie.setDateSortie(object.get("first_air_date").toString());
		serie.setNote(object.get("vote_average").toString());
		serie.setImage(object.get("poster_path").toString());
		serie.setTitle(object.get("original_name").toString());

		serie = setSaisonEpisodeCount(id, serie);

		Vector<Comments> Comments = Comment(Base_url+"tv/"+id+"/reviews?api_key="+API_Key+"&language=en-US&page=1");
		Iterator<Comments> value = Comments.iterator();
        while (value.hasNext()) { 
            value.next().setType("Tv");
        }
		
		serie.setComments(Comments);
		
		serie.setGenre(genre(GetMyJson(Base_url+"tv/"+id+"?api_key="+API_Key+"&language=en-US")));
		
		serie.setSeriesLiees(getSerieLiee(Base_url+"tv/"+id+"/similar?api_key="+API_Key+"&language=en-US"));


		int x = 10;
		if (Integer.parseInt( serie.getNbrSaison()) < 10) {
			x = Integer.parseInt( serie.getNbrSaison());
		}
		for (int i = 1; i < x ; i++) {	
			System.out.println("GETTING A SEASON INFOS "+i);
			Saison saison = getSaisonInfo(Base_url+"tv/"+id+"/season/"+i+"?api_key="+API_Key+"&language=en-US");
			if(saison!=null) {
				saisons.add(saison);
			}
		}
		
		serie.setSaisons(saisons);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		}
		else {
			return null;
		}
		}
		
		return serie;
	}
	
	
	
	public static Serie setSaisonEpisodeCount(String movieId,Serie serie) throws IOException {

		JSONObject object = GetMyJson(Base_url+"tv/"+movieId+"?api_key="+API_Key+"&language=en-US");
        if(object != null) {
		serie.setNbrEpisodes(object.get("number_of_episodes").toString());
		serie.setNbrSaison(object.get("number_of_seasons").toString());
        }
		return serie;
	}
	
	
	public static  Saison getSaisonInfo(String url) throws IOException {
		List<Episode> ep = new ArrayList<Episode>();
		Saison saison = new Saison();
		try {
		JSONObject object = GetMyJson(url);
		if(object != null) {
		JSONArray EpisodArray = object.getJSONArray("episodes");
		
		if(EpisodArray!= null) {
		saison.setId((int) object.get("id"));
		saison.setDetails(object.get("overview").toString());
		saison.setDateSortie(object.get("air_date").toString());
		saison.setNom(object.get("name").toString());
		saison.setNbrEpisodes(EpisodArray.length());
		saison.setImage(object.get("poster_path").toString());
		
		Episode E;
		int x = 20;
		
		if(EpisodArray.length()<=20) {
			x = EpisodArray.length();
		}
		
		for (int i = 0; i < x; i++) {
			System.out.println("GETTING EPISODE INFOS "+ i);
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
		}}

		return saison;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	

public static  List<Serie> getSerieLiee(String url) throws IOException {
		
		List<Serie> seriesLies = new ArrayList<Serie>();
		JSONObject object = GetMyJson(url);
		JSONArray serieArray = object.getJSONArray("results");
		if (serieArray != null) { 
			int x = 40;
			if(serieArray.length()<=40) {
				x = serieArray.length();
			}
		for(int i=0;i<x;i++) {
			System.out.println("GETTING SERIES LIES INFOS " +i);
			Serie serie = new Serie();
			JSONObject obj =(JSONObject) serieArray.get(i);
			String id = obj.get("id").toString();
			
			if(idsSerie.contains(id)==false) {
				idsSerie.add(id);
			serie.setId((Integer) obj.get("id"));
			serie.setResume(obj.get("overview").toString());
			serie.setDateSortie(obj.get("first_air_date").toString());
			serie.setNote(obj.get("vote_average").toString());
			serie.setImage(obj.get("poster_path").toString());
			serie.setTitle(obj.get("original_name").toString());
			
			serie = setSaisonEpisodeCount(id, serie);

			Vector<Comments> Comments = Comment(Base_url+"tv/"+id+"/reviews?api_key="+API_Key+"&language=en-US&page=1");
			Iterator<Comments> value = Comments.iterator();
	        while (value.hasNext()) { 
	            value.next().setType("Tv");
	        }
			
			serie.setComments(Comments);
			
			serie.setGenre(genre(GetMyJson(Base_url+"tv/"+id+"?api_key="+API_Key+"&language=fr&page=1")));
			
			serie.setActors(Actors(Base_url+"tv/"+id+"/credits?api_key="+API_Key+"&language=en-US"));
		
			seriesLies.add(serie);
			
		}
		}}

		return seriesLies;
	}

		public static List<Room> addRoom() {
			
			List<Room>rooms = new ArrayList<Room>();
			
			Room C1 = new Room(1, "UGC Ciné Cité Paris 19","166 Boulevard Macdonald, 75019 Paris, France", "https://cdn-www.konbini.com/fr/files/2013/10/article__X3E9464.jpg", 48.899761, 2.376952, false);
			Room C2 = new Room(2, "UGC Ciné Cité Bercy","2 Cour Saint-Emilion, 75012 Paris, France", "https://files.offi.fr/lieu/3320/images/600/63e1ff91c9853a0b1f106b3fc45af2b5.jpg", 48.832306, 2.385069, false);
			Room C3 = new Room(3, "UGC Ciné Cité Les Halles","101 Rue Berger, 75001 Paris, France", "https://www.sortiraparis.com/images/80/90246/531578-ugc-cine-cite-les-halles-3.jpg",48.863460, 2.343399, false);
			Room C4 = new Room(4, "Luminor Hôtel de Ville","20 Rue du Temple, 75004 Paris, France", "https://www.pagesjaunes.fr/media/ugc/luminor_hotel_de_ville_07505600_195521190",48.858716, 2.353561, false);
			Room C5 = new Room(5, "La Filmothèque du Quartier Latin","9 Rue Champollion, 75005 Paris, France", "https://www.pagesjaunes.fr/media/ugc/la_filmotheque_du_quartier_latin_07505600_174028275", 48.849578, 2.342828, false);
			Room C6 = new Room(6, "Le Brady","39 Boulevard de Strasbourg, 75010 Paris", "https://salles-cinema.com/wp-content/uploads/2009/09/cinema-le-brady_11.jpg",48.871777, 2.355446, false);
			Room r[]= {C1,C2,C3,C4,C5,C6};
			/*int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
			
			
			for (int i = 0; i < randomNum; i++) {
				
				int random = ThreadLocalRandom.current().nextInt(1, 6 + 1);
				System.out.println("C"+random);
				if(rooms.contains(r[random-1])!=true) {
					rooms.add(r[random-1]);
				}
				
			}
			*/

			int rand = (int) (Math.random() * 6);
			rooms.add(r[(rand+1)%6]);
			rooms.add(r[rand]);
			
			return rooms;		
		}


		
		public static  String getMovieVideo(String url) throws IOException {
			String video ="null";
			
			try {
				JSONObject object = GetMyJson(url);
				JSONArray serieArray = object.getJSONArray("results");
				if (serieArray != null) {
					if(serieArray.length()>0) {
					JSONObject obj =(JSONObject) serieArray.get(0);
					video = obj.getString("key");}
					
				}
			} catch (Exception e) {
				
			}
			
			return video;
		}
		




}

