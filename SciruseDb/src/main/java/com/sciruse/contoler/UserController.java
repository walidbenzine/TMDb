package com.sciruse.contoler;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sciruse.models.Film;
import com.sciruse.models.Genre;
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
	
	
	@RequestMapping("/changeinfo/{id}/{Fn}/{Ln}/{email}/{addr}/{phone}/{login}")
	public String changeinfo(@PathVariable Integer id,@PathVariable String Fn,@PathVariable String Ln,@PathVariable String email,@PathVariable String addr,@PathVariable String phone,@PathVariable String login) throws IOException
	{			
		userRepository.changeinfo( id, Fn, Ln, email,  addr,  phone,  login);
		return "change info user reussi";
	}
	
	@RequestMapping("/changepass/{id}/{pass}")
	public String changepass(@PathVariable Integer id,@PathVariable String pass) throws IOException
	{
		userRepository.changepass(id, pass);
		return "update password reussi";
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
	

	@RequestMapping("/addcomm/{id}/{comm}/{type}/{user}")
	public String addcomm(@PathVariable Integer id,@PathVariable String comm,@PathVariable String type, @PathVariable String user) throws IOException
	{
		userRepository.addcomm( id, comm, type, user);

		return "Ajout du commentaire reussi";
	}
	
	@RequestMapping("/addcommfilm/{iduser}/{idcomm}")
	public String addcommfilm(@PathVariable Integer iduser,@PathVariable Integer idcomm) throws IOException
	{
		 userRepository.addcommfilm( iduser,idcomm);
		return "Ajout du commentaire film reussi";

	}
	
	@RequestMapping("/addgenreuser/{iduser}/{idgenre}")
	public String addgenreuser(@PathVariable Integer iduser, @PathVariable Integer idgenre) throws IOException
	{			
		userRepository.addgenreuser(iduser,idgenre);
		return "add genre user reussi";
		}
	
	@RequestMapping("/addcommserie/{iduser}/{idcomm}")
	public String addcommserie(@PathVariable Integer iduser,@PathVariable Integer idcomm) throws IOException
	{
		 userRepository.addcommserie( iduser,idcomm);
		return "Ajout du commentaire serie reussi";

	}
	
	@RequestMapping("/maxid")
	public Integer maxid() throws IOException
	{
		 
		return userRepository.maxid();

	}
	
	@RequestMapping("/getuserGenre/{id}")
	public List<Genre> getuserGenre(@PathVariable Integer id) throws IOException
	{
		
		return userRepository.getUser(id).getGenrePref();
	}
	

	
}
