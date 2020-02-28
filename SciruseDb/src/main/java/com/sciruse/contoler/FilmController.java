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
import com.sciruse.models.Serie;
import com.sciruse.repository.FilmRepository;
import com.sciruse.repository.GenreRepository;
import com.sciruse.repository.SerieRepository;
import com.sciruse.test.ImportFunctions;

@RestController
public class FilmController {
	private static String Base_url="https://api.themoviedb.org/3/";
	private static String API_Key="94327dc22a17d2c12b806d241682cd96";
	ImportFunctions t;
	@Autowired
	FilmRepository repo;
	@Autowired
	SerieRepository repo2;
	
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
	
	@RequestMapping("/getTv")
	public List<Serie> addAlien(Serie serie) throws IOException
	{
				
		return (List<Serie>) repo2.findAll();
	}
	

	
	@RequestMapping("/addMovies")
	public String addMovies() throws IOException
	{
		 t =new  ImportFunctions();
		Vector<Film>films= t.MoviesPopular("https://api.themoviedb.org/3/movie/popular?api_key="+API_Key+"&language=en-US&page=1");
		
		for (Film film : films) {
			
		List<Comments> com = t.Comment(Base_url+"movie/"+film.getID() +"/reviews?api_key="+API_Key+"&language=en-US");
		film.setComments(com);
		
		List<Actors> act = t.Actors(Base_url+"movie/"+film.getID() +"/credits?api_key="+API_Key+"&language=en-US");
		film.setActors(act);
		
		for (Actors actor: act) {
			actor.setFilmographie(t.getFilmBiblio(Base_url+"person/"+actor.getId()+"/movie_credits?api_key="+API_Key+"&language=fr"));
		}
		
		}
		
		repo.saveAll(films);

		return "yes";
	}
	
	@RequestMapping("/addTv")
	public String addTv() throws IOException
	{
		 Integer[] pages = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		 t =new  ImportFunctions();
		 Vector<Serie>series = new Vector<Serie>();
		 for(int i=0;i<pages.length;i++) {
		 series.addAll(t.Serie("https://api.themoviedb.org/3/tv/popular?api_key="+API_Key+"&language=en-US&page="+pages[i]));
		 }
		 for (Serie serie : series) {
		 List<Actors> act = t.Actors(Base_url+"tv/"+serie.getId() +"/credits?api_key="+API_Key+"&language=en-US");
			serie.setActors(act);
		 }
		 repo2.saveAll(series);
	
	return "yes";
	}
	
}
