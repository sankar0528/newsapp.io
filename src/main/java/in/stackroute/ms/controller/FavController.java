package in.stackroute.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import in.stackroute.ms.model.Favourites;
import in.stackroute.ms.security.JwtUtil;
import in.stackroute.ms.service.FavService;

@RestController
public class FavController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	public FavService service;
	
	@PostMapping("/favourite")
	public ResponseEntity<?> addToFavourite(@RequestBody Favourites article/*,
			@RequestHeader("Authorization") String authorizationHeader*/) {
		String username = "sankar1";//getUsername(authorizationHeader);
		return new ResponseEntity<Favourites>(service.savefav(username, article), HttpStatus.OK);
	}

	
	@PutMapping("/favourites/{id}")
	public ResponseEntity<?> deleteFavourites(@PathVariable int id,
			@RequestHeader("Authorization") String authorizationHeader) {
		String username = getUsername(authorizationHeader);
		return new ResponseEntity<Favourites>(service.deleteFav(username, id), HttpStatus.OK);
	}


	@GetMapping("/favourite")
	public ResponseEntity<?> getFavourites(@RequestHeader("Authorization") String authorizationHeader) {
		String username = getUsername(authorizationHeader);
		List<Favourites> articles = service.getAllFav(username);
		return new ResponseEntity<List<Favourites>>(articles, HttpStatus.OK);
	}

	@DeleteMapping("/favourites")
	public ResponseEntity<?> emptyFav(@RequestHeader("Authorization") String authorizationHeader) {
		ResponseEntity<String> response;
		try {
			String username = getUsername(authorizationHeader);
			service.emptyFavourites(username);
			response = new ResponseEntity<String>("Favourites emptied successfully.", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<String>("Error occured while emptying the Favourites", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	
	public String getUsername(String authorizationHeader) {
		return jwtUtil.extractUsername(authorizationHeader.substring(7));
	}


}
