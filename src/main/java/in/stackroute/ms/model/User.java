package in.stackroute.ms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Comparable<User>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(unique = true)        //this the error
	private String username;
	private String password;
	
	private String token;
	
	@OneToMany(cascade=CascadeType.ALL)
    private List<Favourites> favourites;

	public List<Favourites> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Favourites> favourites) {
		this.favourites = favourites;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long userId, String username, String password, List<Favourites> favourites) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.favourites = favourites;
	}
	@Override
	public int compareTo(User otherUser) {
		// TODO Auto-generated method stub
		return username.compareTo(otherUser.getUsername());
	}

	public User(Long userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
