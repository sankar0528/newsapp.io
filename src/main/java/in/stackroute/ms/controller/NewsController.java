package in.stackroute.ms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.stackroute.ms.model.Example;
import in.stackroute.ms.service.NewsService;


@RestController
public class NewsController {
	
	@Autowired
	private NewsService service;
	
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/news")
	public List<Example> sendRefinedUpdate() throws ParseException, IOException {
		return service.sendRefinedUpdate();	
	} 
}
	

