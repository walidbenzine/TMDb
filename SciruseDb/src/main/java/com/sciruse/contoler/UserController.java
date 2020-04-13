package com.sciruse.contoler;

import java.io.IOException;
import java.util.List;
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
	
	@RequestMapping("/addUser/{email}/{jeton}/{login}/{nom}/{password}/{picture}/{prenom}/{tel}/{adresse}")
	public String addUser(@PathVariable String email, @PathVariable Integer jeton, @PathVariable String login, @PathVariable String nom, @PathVariable  String password, @PathVariable String picture, @PathVariable String prenom, @PathVariable String tel, @PathVariable String adresse) throws IOException
	{			
		userRepository.addUser(email,jeton,  login,  nom,  password,  picture,  prenom,  tel, adresse);
		return "Ajout user reussi";
	}
	
	@RequestMapping("/addHistSerie/{user_id}/{seriehistory}")
	public String addHistSerie(@PathVariable Integer user_id, @PathVariable Integer seriehistory) throws IOException
	{
		userRepository.addUserHistSerie(user_id, seriehistory);
		return "Ajout history serie reussi";
		
	}
	
	
	@RequestMapping("/addHistFilm/{user_id}/{filmhistory}")
	public String addHistFilm(@PathVariable Integer user_id, @PathVariable Integer filmhistory) throws IOException
	{
		userRepository.addUserHistFilm(user_id,filmhistory);
		return "Ajout history film reussi";
	}
	
	

	
}
