package com.sciruse.contoler;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sciruse.models.Actors;
import com.sciruse.models.Film;
import com.sciruse.repository.ActorRepository;
import com.sciruse.test.ImportFunctions;

@RestController
public class ActorController {

	private static String Base_url="https://api.themoviedb.org/3/";
	private static String API_Key="94327dc22a17d2c12b806d241682cd96";
	ImportFunctions t;
	@Autowired
	ActorRepository ActorRep;
	
	
	@RequestMapping("/getActorPopular")
	public List<Actors> getActorPopular() throws IOException
	{			
		return  ActorRep.getActorPopular();
	}
	
	
	@RequestMapping("/getActor/{id}")
	public Actors getActor(@PathVariable Integer id) throws IOException
	{			
		return  ActorRep.getActor(id);
	}
	
	
	@RequestMapping("/getActorFilmo/{id}")
	public List<Film> getActorFilmo(@PathVariable Integer id) throws IOException
	{			
		return  ActorRep.getActor(id).getFilmographie();
	}
	
	@RequestMapping("/getActorFilm/{id}")
	public List<Film> getActorFilm(@PathVariable Integer id) throws IOException
	{			
		List<Film> films = t.getFilmBiblio(Base_url+"person/"+id +"/movie_credits?api_key="+API_Key+"&language=en-US&page=1");
		System.out.println(films);
		if(films.size()>5) {
			return films.subList(0, 4);
		}else {
			return films;
		}
	}
}

