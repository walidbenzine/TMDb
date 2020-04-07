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

@RestController
public class ActorController {

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
}
