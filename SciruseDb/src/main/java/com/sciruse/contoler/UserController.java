package com.sciruse.contoler;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sciruse.models.Film;
import com.sciruse.models.Serie;
import com.sciruse.models.User;
import com.sciruse.repository.SerieRepository;
import com.sciruse.repository.UserRepository;
import com.sciruse.test.ImportFunctions;

@RestController
public class UserController {

	private static String Base_url="https://api.themoviedb.org/3/";
	private static String API_Key="94327dc22a17d2c12b806d241682cd96";
	ImportFunctions t;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SerieRepository serieRepository;
	
	@RequestMapping("/getUser/{login}/{password}")
	public List<User> getUser(@PathVariable String login,@PathVariable String password) throws IOException
	{			
		return (List<User>) userRepository.getUser(login, password);
	}
	
	@RequestMapping("/getFavSerie/{id}")
	public List<Serie> getFavSerie(@PathVariable Integer id) throws IOException
	{
		return userRepository.getUser(id).getSerieFavoris();
	}
	
	@RequestMapping("/addFavSerie/{id}/{idSerie}")
	public String addFavSerie(@PathVariable Integer id, @PathVariable Integer idSerie) throws IOException
	{
		userRepository.addFavSerie(id,idSerie);
		return "Ajout de la série aux favoris réussi";
	}
	
	@RequestMapping("/getHistSerie/{id}")
	public List<Serie> getHistSerie(@PathVariable Integer id) throws IOException
	{
		return userRepository.getUser(id).getSeriehistory();
	}
	
	@RequestMapping("/getFavFilm/{id}")
	public List<Film> getFavFilm(@PathVariable Integer id) throws IOException
	{
		return userRepository.getUser(id).getFilmFavoris();
	}
	
	@RequestMapping("/getHistFilm/{id}")
	public List<Film> getHistFilm(@PathVariable Integer id) throws IOException
	{
		return userRepository.getUser(id).getFilmhistory();
	}
	
	@RequestMapping("/addFavFilm/{id}/{idFilm}")
	public String addFavFilm(@PathVariable Integer id,@PathVariable Integer idFilm) throws IOException
	{
		userRepository.addFavFilm(id,idFilm);
		return "Ajout du film aux favoris reussi";
	}
	
	
	

	
}
