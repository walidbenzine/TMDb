package com.sciruse.contoler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sciruse.models.Film;
import com.sciruse.repository.FilmRepository;

@Controller
public class FilmController {

	@Autowired
	FilmRepository repo;
	
	@RequestMapping("/lola")
	@ResponseBody
	public void home() {
		System.out.println("looooooooooooooooooooooola");
		
	}
	
	@RequestMapping("/getFilm")
	public String addAlien(Film film)
	{
		
		return "home.jsp";
	}
}
