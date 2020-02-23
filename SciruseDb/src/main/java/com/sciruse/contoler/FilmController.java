package com.sciruse.contoler;


import java.io.IOException;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sciruse.models.Film;
import com.sciruse.repository.FilmRepository;
import com.sciruse.repository.GenreRepository;
import com.sciruse.test.ImportFunctions;

@RestController
public class FilmController {

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
	public Vector<Film> addAlien(Film film) throws IOException
	{
		 t =new  ImportFunctions();
		Vector<Film>v= t.MoviesPopular("https://api.themoviedb.org/3/movie/popular?api_key=94327dc22a17d2c12b806d241682cd96&language=fr&page=1");
		
		return v;
	}
	
	
	@RequestMapping("/addMovies")
	public String addMovies() throws IOException
	{
		 t =new  ImportFunctions();
		Vector<Film>v= t.MoviesPopular("https://api.themoviedb.org/3/movie/popular?api_key=94327dc22a17d2c12b806d241682cd96&language=fr&page=1");
		repo.saveAll(v);
		return "yes";
	}
	
	
}
