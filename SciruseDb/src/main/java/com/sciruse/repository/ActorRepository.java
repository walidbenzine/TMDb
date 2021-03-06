package com.sciruse.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sciruse.models.Actors;
import com.sciruse.models.Film;

public interface ActorRepository extends CrudRepository<Actors, Long>{
	
	
	@Query(value = "SELECT * FROM sciruse.actors where id =?1", nativeQuery = true)
	Actors getActor(Integer id);

}

