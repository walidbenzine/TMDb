package com.sciruse.contoler;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciruse.models.Actors;
import com.sciruse.models.Comments;
import com.sciruse.models.Film;
import com.sciruse.models.Genre;
import com.sciruse.repository.ActorRepository;
import com.sciruse.repository.FilmRepository;

@RestController
public class MovieController {

	@Autowired
	FilmRepository movieRepository;
	
	@Autowired
	ActorRepository ActorRep;
	
	@RequestMapping("/getPopular")
	public List<Film> getPopular() throws IOException
	{
				
		return (List<Film>) movieRepository.getpopular();
	}
	
	@RequestMapping("/getMovie/{id}")
	public Film addAlien(@PathVariable Integer id) throws IOException
	{
		
		return movieRepository.getMovie(id);
	}
	
	
	@RequestMapping("/getMovieActors/{id}")
	public List<Actors> getMovieActors(@PathVariable Integer id) throws IOException
	{
		
		return movieRepository.getMovie(id).getActors();
	}
	
	@RequestMapping("/getMovieComment/{id}")
	public List<Comments> getMovieComment(@PathVariable Integer id) throws IOException
	{
		
		return movieRepository.getMovie(id).getComments();
	}
	
	
	@RequestMapping("/getMovieGenre/{id}")
	public List<Genre> getMovieGenre(@PathVariable Integer id) throws IOException
	{
		
		return movieRepository.getMovie(id).getGenre();
	}
	
	
	@RequestMapping("/getActorFilmoGrah/{id}")
	public List<Film> getActorFilmoGrah(@PathVariable Integer id) throws IOException
	{
		
		return ActorRep.getActor(id).getFilmographie();
	}
	
	
	
	
}
