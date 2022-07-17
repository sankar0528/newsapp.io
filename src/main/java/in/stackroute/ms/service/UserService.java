package in.stackroute.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.stackroute.ms.model.User;
import in.stackroute.ms.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    
	public UserService(@Lazy PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User createUser(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}


	public Boolean deleteUser(String username) {
		User u = userRepository.findByUsername(username);
		Long userId = u.getUserId();
		Boolean response = Boolean.FALSE;
		try {
			userRepository.deleteById(userId);
			response = Boolean.TRUE;
		} catch (Exception e) {

		}
		return response;
	}

}
