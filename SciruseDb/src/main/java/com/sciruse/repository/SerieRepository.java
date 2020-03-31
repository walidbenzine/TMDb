package com.sciruse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sciruse.models.Serie;


public interface SerieRepository extends CrudRepository<Serie, Long>{
	
	@Query(value = "SELECT  * FROM  sciruse.serie where `image` !='null' LIMIT 20;", nativeQuery = true)
	List<Serie> getSeriePopular();
	
	@Query(value = "SELECT  * FROM  sciruse.serie where `image` !='null' AND `id` = ?1", nativeQuery = true)
	Serie getSerie(Integer id);
	
	@Query(value = "SELECT * FROM sciruse.serie where `image` !='null' order by `note` desc LIMIT 20 ", nativeQuery = true)
	List<Serie> getSerieTopRated();
	
	@Query(value = "SELECT * FROM sciruse.serie where `image` !='null' order by `date_sortie` desc LIMIT 20 ", nativeQuery = true)
	List<Serie> getSerieLast();

}
