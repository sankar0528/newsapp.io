package in.stackroute.ms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.stackroute.ms.model.User;
import in.stackroute.ms.security.JwtUtil;
import in.stackroute.ms.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;


//	@PostMapping("/login")
//	@CrossOrigin(origins = "*")
//	public String login(String username, String password) {
//		return username+"-"+password;
//		//return userService.getUserByUsername(getUsername(authorizationHeader));
//	}

	@PostMapping("/")
	@CrossOrigin(origins = "*")
	public User getOwnData(@RequestHeader("Authorization") String authorizationHeader) {
		return userService.getUserByUsername(getUsername(authorizationHeader));
	}

	@PostMapping("/register")
	@CrossOrigin(origins = "*")
	public User createUser(@RequestBody User user) {

		
		User createdUser = userService.createUser(user);
		return createdUser;
	}

	@PutMapping("/")
	public User updateUser(@RequestHeader("Authorization") String authorizationHeader, @RequestBody User user) {
		String username = getUsername(authorizationHeader);
		user.setUsername(username);
		return userService.updateUser(user);
	}

	@DeleteMapping("/")
	public Boolean deleteUser(@RequestHeader("Authorization") String authorizationHeader) {
		String username = getUsername(authorizationHeader);
		return userService.deleteUser(username);
	}

	
	public String getUsername(String authorizationHeader) {
		return jwtUtil.extractUsername(authorizationHeader.substring(7));
	}

}
