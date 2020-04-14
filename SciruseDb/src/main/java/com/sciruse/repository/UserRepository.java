package com.sciruse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sciruse.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	
	@Query(value = "SELECT  * FROM  sciruse.user where `login` = ?1 AND `password` = ?2 ;", nativeQuery = true)
	List<User> getUser(String login,String password);
	
	@Query(value = "SELECT  * FROM  sciruse.user where `id` = ?1 ;", nativeQuery = true)
	User getUser(Integer login);
	
	@Modifying
	@Query(value = "INSERT INTO sciruse.user_serie_favoris VALUES( ?1 , ?2 );", nativeQuery = true)
	@Transactional
	void addFavSerie(Integer id, Integer idSerie);
	
	
	@Modifying
	@Query(value = "INSERT INTO sciruse.user_film_favoris VALUES( ?1 , ?2 );", nativeQuery = true)
	@Transactional
	void addFavFilm(Integer id, Integer idFilm);
	
	@Modifying
	@Query(value = "INSERT INTO sciruse.user(email,jeton,  login,  nom,  password,  picture,  prenom,  tel, adresse) VALUES(?1 , ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9);", nativeQuery = true)
	@Transactional
	void addUser(String email, Integer jeton, String login, String nom, String password, String picture, String prenom, String tel, String adresse);
	
	
	@Modifying
	@Query(value = "INSERT INTO sciruse.user_seriehistory VALUES( ?1 , ?2 );", nativeQuery = true)
	@Transactional
	void addUserHistSerie(Integer user_id, Integer seriehistory);
	
	@Modifying
	@Query(value = "INSERT INTO sciruse.user_filmhistory VALUES( ?1 , ?2 );", nativeQuery = true)
	@Transactional
	void addUserHistFilm(Integer user_id, Integer filmhistory);
	
	@Modifying
	@Query(value = "UPDATE sciruse.user SET `password`= ?2 WHERE `id`= ?1 ;", nativeQuery = true)
	@Transactional
	void changepass(Integer id,String password);
	
	@Modifying
	@Query(value = "UPDATE sciruse.user SET `password`= ?2 WHERE `id`= ?1 ;", nativeQuery = true)
	@Transactional
	void changeinfo(Integer id,String password);
	
}
