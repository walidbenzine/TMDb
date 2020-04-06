package com.sciruse.repository;


import org.springframework.data.repository.CrudRepository;

import com.sciruse.models.Film;

public interface FilmRepository extends CrudRepository<Film, Long>{

}
