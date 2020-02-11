package com.sciruse.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sciruse.models.Film;
import com.sciruse.models.Genre;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class test {


	private final static OkHttpClient httpClient = new OkHttpClient();

	private String API_Key="94327dc22a17d2c12b806d241682cd96";

	public static void main(String[] args) {
		Vector<Film>films;
		Vector<Genre>genres;
		try {
			 films = Movies("https://api.themoviedb.org/3/movie/popular?api_key=94327dc22a17d2c12b806d241682cd96&language=en-US&page=1");
			 genres= genre("https://api.themoviedb.org/3/genre/movie/list?api_key=94327dc22a17d2c12b806d241682cd96&language=fr");
			 
			System.out.println(genres);
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

	public static  Vector<Film> Movies(String url) throws IOException {
		
		Vector<Film>films =new Vector<Film>();
		ArrayList<String> listdata;      
		JSONObject movie;
		
		JSONObject object = GetMyJson(url);
		
		JSONArray liJsonArray = object.getJSONArray("results");
		Iterator<String> keyList = object.keys();
		Film a =null ;
		for (int i = 0; i < liJsonArray.length(); i++) {
			listdata= new ArrayList<String>();
			a= new Film();
			movie = (JSONObject)liJsonArray.get(i);		     
			a.setTitle(movie.get("title").toString());
			a.setID(movie.get("id").toString());
			a.setResume(movie.get("overview").toString());
			a.setDateSortie(movie.get("release_date").toString()); 
			
			JSONArray genreArr = movie.getJSONArray("genre_ids");
			//System.out.println(genreArr);
			//------------------------------------------------------------
			if (genreArr != null) { 
				   for (int j=0;j<genreArr.length();j++){ 
				    listdata.add(genreArr.get(j).toString());
				   } 
				} 
		
			a.setGenre(listdata);
			films.add(a);
			
		}
		return films; 
	}

	
	public static  void Comment(String url) throws IOException {
		
		JSONObject myobj;
		JSONObject object = GetMyJson(url);
		JSONArray obj = object.getJSONArray("results");
		Iterator<String> keyList = object.keys();
		myobj = (JSONObject)obj.get(1);
		Iterator<String> keyList2 =myobj.keys();
		System.out.println(obj.toString());

		while (keyList2.hasNext()){
			String key = keyList2.next();
			System.out.println(key);
		}
		System.out.println(obj.length());		

	}
	
	public static Vector<Genre> genre(String url) throws IOException {
		//https://api.themoviedb.org/3/genre/movie/list?api_key=94327dc22a17d2c12b806d241682cd96&language=fr
		Vector<Genre> genres=new Vector<Genre>(); 
		JSONObject object = GetMyJson(url);
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
}
