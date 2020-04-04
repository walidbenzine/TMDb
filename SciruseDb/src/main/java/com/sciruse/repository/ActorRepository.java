package com.sciruse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sciruse.models.Actors;

public interface ActorRepository extends CrudRepository<Actors, Long>{
	
	
	@Query(value = "SELECT * FROM sciruse.actors where id =?1", nativeQuery = true)
	Actors getActor(Integer id);
	
	@Query(value = "SELECT * FROM sciruse.actors where `photo` !='null' order by `popularite` desc LIMIT 20", nativeQuery = true)
	List<Actors> getActorPopular();

}

