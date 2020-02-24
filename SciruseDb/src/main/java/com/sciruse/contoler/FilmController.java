package com.sciruse.contoler;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sciruse.models.Actors;
import com.sciruse.models.Comments;
import com.sciruse.models.Film;
import com.sciruse.repository.FilmRepository;
import com.sciruse.repository.GenreRepository;
import com.sciruse.test.ImportFunctions;

@RestController
public class FilmController {
	private static String Base_url="https://api.themoviedb.org/3/";
	private static String API_Key="94327dc22a17d2c12b806d241682cd96";
	ImportFunctions t;
	@Autowired
	FilmRepository repo;
	
	@Autowired
	GenreRepository repoGenre;
	
	@RequestMapping("/lola")
	@ResponseBody
	public void home() {
		System.out.println("looooooooooooooooooooooola");
		
	}
	
	@RequestMapping("/getMovies")
	public List<Film> addAlien(Film film) throws IOException
	{
		
		
		return (List<Film>) repo.findAll();
	}
	
	
	@RequestMapping("/addMovies")
	public String addMovies() throws IOException
	{
		 t =new  ImportFunctions();
		Vector<Film>v= t.MoviesPopular("https://api.themoviedb.org/3/movie/popular?api_key=94327dc22a17d2c12b806d241682cd96&language=en-US&page=1");
		
		for (Film film : v) {
			
		List<Comments> com = t.Comment(Base_url+"movie/"+film.getTmdb_id() +"/reviews?api_key="+API_Key+"&language=en-US");
		film.setComments(com);
		
		List<Actors> act = t.Actors(Base_url+"movie/"+film.getTmdb_id() +"/credits?api_key="+API_Key+"&language=en-US");
		
		for (Actors actor: act) {
			actor.setFilmographie(t.getFilmBiblio(Base_url+"person/287/movie_credits?api_key="+API_Key+"&language=fr"));
		}
		
		film.setActors(act);
		
		
		
		
		
		
		}
		repo.saveAll(v);
		return "yes";
	}
	
	
	@RequestMapping("/getmovies")
	public List<Film> addComment() throws IOException
	{
		
		
		 return  t.getFilmBiblio(Base_url+"person/287/movie_credits?api_key="+API_Key+"&language=fr");
		 
		
	}
}
