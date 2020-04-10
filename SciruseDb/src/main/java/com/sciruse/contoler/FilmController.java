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


/* to  load movies call
 * 1) /addMovies
 * 2) /addliee
 * 3) /addMovieVideo
 * 4) /addRoom
 * and u're good to go 
 * nb: do this just one time 
 * 
 * to load series call
 * 1)/addTv
 * 2)/addSerieliee
 * 3)/addserieVideo
 * */
@RestController
public class FilmController {
	private static String Base_url="https://api.themoviedb.org/3/";
	private static String API_Key="94327dc22a17d2c12b806d241682cd96";
	ImportFunctions t;
	@Autowired
	FilmRepository filmRepository;
	@Autowired
	SerieRepository serieRepository;

	@Autowired
	GenreRepository repoGenre;

	@RequestMapping("/lola")
	@ResponseBody
	public void home() {
		System.out.println("Test Working");
	}

	@RequestMapping("/getMovies")
	public List<Film> addAlien(Film film) throws IOException
	{
		t =new  ImportFunctions();
		return (List<Film>) filmRepository.findAll();
	}

	@RequestMapping("/getTv")
	public List<Serie> addAlien(Serie serie) throws IOException
	{
		t =new  ImportFunctions();
		return t.Serie("https://api.themoviedb.org/3/tv/popular?api_key="+API_Key+"&language=en-US&page=1");
	}

	//add popular movies include add comments actors each actor has list of movies that we also added them************************************
	@RequestMapping("/addMovies")
	public String addMovies() throws IOException
	{
		t =new  ImportFunctions();
		Vector<Film>films= t.MoviesPopular("https://api.themoviedb.org/3/movie/popular?api_key="+API_Key+"&language=en-US&page=1");

		for (Film film : films) {
			//add actors to  movie
			List<Actors> act = t.Actors(Base_url+"movie/"+film.getID() +"/credits?api_key="+API_Key+"&language=en-US");
			film.setActors(act);
			//add movie to actors
			for (Actors actor: act) {
				actor.setFilmographie(t.getFilmBiblio(Base_url+"person/"+actor.getId()+"/movie_credits?api_key="+API_Key+"&language=en-US"));
			}
		}
		filmRepository.saveAll(films);

		return "yes";
	}

	// add list of similar movies to  one movie 
	@RequestMapping("/addliee")
	public String getFilmliee() throws IOException
	{
		t =new  ImportFunctions();
		List<Film>films = (List<Film>) filmRepository.getpopular();
		for (Film film : films) {
			List<Film>liee= t.getFilmLiee(Base_url+"movie/"+film.getID() +"/similar?api_key="+API_Key+"&language=en-US&page=1");
			film.setFilmsLiees(liee);
			filmRepository.save(film);
		}

		return "done";
	}

	// add Rooms to  popular movies 
	@RequestMapping("/addRoom")
	public String addRoom() throws IOException
	{
		t =new  ImportFunctions();
		List<Film>films = (List<Film>) filmRepository.getLast();
		for (Film film : films) {

			film.setRooms(t.addRoom());
			filmRepository.save(film);
		}

		return "done";
	}


	
	@RequestMapping("/addMovieVideo")
	public String addMovieVideo() throws IOException
	{
		t =new  ImportFunctions();
		List<Film>films = (List<Film>) filmRepository.findAll();
		for (Film film : films) {

			film.setVideo(t.getMovieVideo(Base_url+"movie/"+film.getID()+"/videos?api_key="+API_Key+"&language=en-US"));
			filmRepository.save(film);

		}

		return "done";
	}

	//          1
	@RequestMapping("/addTv")
	public String addTv() throws IOException
	{
		t =new  ImportFunctions();
		Vector<Serie> series= (Vector<Serie>) t.Serie(Base_url+"tv/popular?api_key="+API_Key+"&language=en-US&page=1");

		for (Serie serie : series) {

			List<Actors> act = t.Actors(Base_url+"tv/"+serie.getId() +"/credits?api_key="+API_Key+"&language=en-US");
			serie.setActors(act);

			for (Actors actor: act) {
				actor.setSeriegraphie(t.getSerieBiblio(Base_url+"person/"+actor.getId()+"/tv_credits?api_key="+API_Key+"&language=en-US"));
			}
		}
		serieRepository.saveAll(series);

		return "Series ajoutées !";
	}
//            2
	@RequestMapping("/addSerieliee")
	public String addSerieliee() throws IOException
	{
		t =new  ImportFunctions();
		List<Serie>series = (List<Serie>)serieRepository.getSeriePopular();
		for (Serie serie : series) {
			serie.setSeriesLiees(t.getSerieLiee( Base_url+"tv/"+serie.getId()+"/similar?api_key="+API_Key+"&language=en-US"));
			serieRepository.save(serie);
		}

		return "done";
	}
	
//            3
		@RequestMapping("/addserieVideo")
		public String addserieVideo() throws IOException
		{
			t =new  ImportFunctions();
			List<Serie>series = (List<Serie>) serieRepository.findAll();
			for ( Serie serie : series) {

				serie.setVideo(t.getMovieVideo(Base_url+"movie/"+serie.getId()+"/videos?api_key="+API_Key+"&language=en-US"));
				serieRepository.save(serie);
			}

			return "done";
		}



}

