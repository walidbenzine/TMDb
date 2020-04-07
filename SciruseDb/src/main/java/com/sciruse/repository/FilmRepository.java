package com.sciruse.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.sciruse.models.Film;

public interface FilmRepository extends CrudRepository<Film, Long>{
	
	@Query(value = "SELECT  * FROM  sciruse.film  LIMIT 20;", nativeQuery = true)
	List<Film> getpopular();
	
	@Query(value = "SELECT  * FROM  sciruse.film where id =?1", nativeQuery = true)
	Film getMovie(Integer id);
	
	@Query(value = "SELECT * FROM sciruse.film where image !='null' order by note desc ", nativeQuery = true)
	List<Film> getTopRated();
	
	@Query(value = "SELECT * FROM sciruse.film where image !='null' order by date_sortie desc ", nativeQuery = true)
	List<Film> getLast();
	
	
	
	
	

}
