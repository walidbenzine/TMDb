package com.sciruse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sciruse.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	
	@Query(value = "SELECT  * FROM  sciruse.user where `login` = ?1 AND `password` = ?2 ;", nativeQuery = true)
	List<User> getUser(String login,String password);
	
	@Query(value = "SELECT  * FROM  sciruse.user where `id` = ?1 ;", nativeQuery = true)
	User getUser(Integer login);
	
}
