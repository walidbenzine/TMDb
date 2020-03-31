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
		t =new  ImportFunctions();
		return (List<Film>) repo.findAll();
	}

	@RequestMapping("/getTv")
	public List<Serie> addAlien(Serie serie) throws IOException
	{
		t =new  ImportFunctions();

		return t.Serie("https://api.themoviedb.org/3/tv/popular?api_key="+API_Key+"&language=en-US&page=1");
	}



	@RequestMapping("/addMovies")
	public String addMovies() throws IOException
	{
		t =new  ImportFunctions();
		Vector<Film>films= t.MoviesPopular("https://api.themoviedb.org/3/movie/popular?api_key="+API_Key+"&language=en-US&page=1");

		for (Film film : films) {


			List<Actors> act = t.Actors(Base_url+"movie/"+film.getID() +"/credits?api_key="+API_Key+"&language=en-US");
			film.setActors(act);

			//List<Film>liee= t.getFilmLiee(Base_url+"movie/"+film.getID() +"/similar?api_key="+API_Key+"&language=en-US&page=1");
			//film.setFilmsLiees(liee);

			//film.setFilmsLiees(t.getFilmLiee(Base_url+"movie/"+film.getID()+"/similar?api_key="+API_Key+"&language=en-US&page=1"));
			for (Actors actor: act) {
				actor.setFilmographie(t.getFilmBiblio(Base_url+"person/"+actor.getId()+"/movie_credits?api_key="+API_Key+"&language=en-US"));
			}

		}

		repo.saveAll(films);

		return "yes";
	}


	@RequestMapping("/addliee")
	public String getFilmliee() throws IOException
	{
		t =new  ImportFunctions();
		List<Film>films = (List<Film>) repo.findAll();
		for (Film film : films) {
			List<Film>liee= t.getFilmLiee(Base_url+"movie/"+film.getID() +"/similar?api_key="+API_Key+"&language=en-US&page=1");
			film.setFilmsLiees(liee);
			repo.save(film);
		}

		return "done";

	}




}

