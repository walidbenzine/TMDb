package com.sciruse.contoler;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciruse.models.Actors;
import com.sciruse.models.Comments;
import com.sciruse.models.Episode;
import com.sciruse.models.Film;
import com.sciruse.models.Genre;
import com.sciruse.models.Saison;
import com.sciruse.models.Serie;
import com.sciruse.repository.ActorRepository;
import com.sciruse.repository.FilmRepository;
import com.sciruse.repository.SerieRepository;
import com.sciruse.test.ImportFunctions;


/*
 To load series, saisons, series liées, actors etc..
 Just do /addTv 
  
 */
@RestController
public class SerieController {
	
	private static String Base_url="https://api.themoviedb.org/3/";
	private static String API_Key="94327dc22a17d2c12b806d241682cd96";
	ImportFunctions t;
	@Autowired
	SerieRepository serieRepository;
	
	@Autowired
	ActorRepository ActorRep;
	

	@RequestMapping("/getSeriePopular")
	public List<Serie> getSeriePopular() throws IOException
	{
				
		return (List<Serie>) serieRepository.getSeriePopular();
	}
	
	@RequestMapping("/getSerie/{id}")
	public Serie addAlien(@PathVariable Integer id) throws IOException
	{
		
		return serieRepository.getSerie(id);
	}
	
	
	@RequestMapping("/getSerieActors/{id}")
	public List<Actors> getSerieActors(@PathVariable Integer id) throws IOException
	{
		
		return serieRepository.getSerie(id).getActors();
	}
	
	@RequestMapping("/getSerieComment/{id}")
	public List<Comments> getSerieComment(@PathVariable Integer id) throws IOException
	{
		
		return serieRepository.getSerie(id).getComments();
	}
	
	
	@RequestMapping("/getSerieGenre/{id}")
	public List<Genre> getSerieGenre(@PathVariable Integer id) throws IOException
	{
		
		return serieRepository.getSerie(id).getGenre();
	}
	
	
	@RequestMapping("/getSerieLiees/{id}")
	public List<Serie> getSerieLiees(@PathVariable Integer id) throws IOException
	{
		
		return serieRepository.getSerie(id).getSeriesLiees();
	}
	
	@RequestMapping("/getSerieLi/{id}")
	public List<Serie> getSerieLi(@PathVariable Integer id) throws IOException
	{
		
		return t.getSerieLiee(Base_url+"tv/"+id+"/similar?api_key="+API_Key+"&language=en-US");
	}
	
	@RequestMapping("/getSerieSais/{id}/{number}")
	public Saison getSerieSais(@PathVariable Integer id,@PathVariable Integer number) throws IOException
	{
		
		return t.getSaisonInfo(Base_url+"tv/"+id+"/season/"+number+"?api_key="+API_Key+"&language=en-US");
	}
	
	@RequestMapping("/getCSer/{id}")
	public List<Comments> getC(@PathVariable Integer id) throws IOException
	{		
		List<Comments> com = t.Comment(Base_url+"tv/"+id+"/reviews?api_key="+API_Key+"&language=en-US&page=1");
		if(com.size()>5) {
			return com.subList(0, 4);
		}else {
			return com;
		}
	}
	
	@RequestMapping("/getSaisonActs/{id}/{number}")
	public List<Actors> getSaisonActs(@PathVariable Integer id,@PathVariable Integer number) throws IOException
	{
		return t.Actors(Base_url+"tv/"+id+"/season/"+number+"/credits?api_key="+API_Key+"&language=en-US");
	}
	
	
	
		
	@RequestMapping("/getSerieLast")
	public List<Serie> getSerieLast() throws IOException
	{
				
		return (List<Serie>) serieRepository.getSerieLast();
	}
	
	@RequestMapping("/getSerieSaisons/{id}")
	public List<Saison> getSerieSaisons(@PathVariable Integer id) throws IOException
	{
				
		return  serieRepository.getSerie(id).getSaisons();
	}
	
	@RequestMapping("/getSaison/{id}")
	public Saison getSaison(@PathVariable Integer id) throws IOException
	{
				
		return  serieRepository.getSaison(id);
	}
	
	@RequestMapping("/getSaisonEpisodes/{id}")
	public List<Episode> getSaisonEpisodes(@PathVariable Integer id) throws IOException
	{
		return  serieRepository.getSaison(id).getListEpisodes();
	}
	
	
	
	@RequestMapping("/addTv")
	public String addTv() throws IOException
	{
		 t =new  ImportFunctions();
		
				 
		 Vector<Serie> series= (Vector<Serie>) t.Serie(Base_url+"tv/popular?api_key="+API_Key+"&language=en-US&page=1");

			for (Serie serie : series) {


				List<Actors> act = t.Actors(Base_url+"tv/"+serie.getId() +"/credits?api_key="+API_Key+"&language=en-US");
				serie.setActors(act);

				for (Actors actor: act) {
					actor.setSeriegraphie(t.getSerieBiblio(Base_url+"person/"+actor.getId()+"/tv_credits?api_key="+API_Key+"&language=en-US"));
				}

			}

			serieRepository.saveAll(series);

		 
	return "Series ajoutées !";
	}
	
	@RequestMapping("/addserieVideo")
	public String addserieVideo() throws IOException
	{
		t =new  ImportFunctions();
		List<Serie>series = (List<Serie>) serieRepository.findAll();
		for ( Serie serie : series) {

			serie.setVideo(t.getMovieVideo(Base_url+"tv/"+serie.getId()+"/videos?api_key="+API_Key+"&language=en-US"));
			serieRepository.save(serie);
		}

		return "done";
	}

}
