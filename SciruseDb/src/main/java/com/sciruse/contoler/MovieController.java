package com.sciruse.contoler;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciruse.models.Actors;
import com.sciruse.models.Comments;
import com.sciruse.models.Film;
import com.sciruse.models.Genre;
import com.sciruse.models.Room;
import com.sciruse.repository.ActorRepository;
import com.sciruse.repository.FilmRepository;
import com.sciruse.repository.RoomRepository;

@RestController
public class MovieController {

	@Autowired
	FilmRepository movieRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	
	@RequestMapping("/getPopular")
	public List<Film> getPopular() throws IOException
	{			
		return (List<Film>) movieRepository.getpopular();
	}
	
	
	@RequestMapping("/getlast")
	public List<Film> getNew() throws IOException
	{			
		return (List<Film>) movieRepository.getLast();
	}
	
	@RequestMapping("/getTopRated")
	public List<Film> getTopRated() throws IOException
	{			
		return (List<Film>) movieRepository.getTopRated();
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
	
	@RequestMapping("/getFilmLie/{id}")
	public List<Film> getFilmLie(@PathVariable Integer id) throws IOException
	{
		
		return movieRepository.getMovie(id).getFilmsLiees();
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
	
	@RequestMapping("/getRoom/{id}")
	public List<Room> getRoom(@PathVariable Integer id) throws IOException
	{
		
		return movieRepository.getMovie(id).getRooms();
		
	}
	
	@RequestMapping("/getAllRooms")
	public List<Room> getAllRooms() throws IOException
	{
		
		return roomRepository.getAllRooms();
		
	}
	
}
