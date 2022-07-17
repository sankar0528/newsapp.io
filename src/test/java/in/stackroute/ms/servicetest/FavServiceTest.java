package in.stackroute.ms.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import in.stackroute.ms.model.Favourites;
import in.stackroute.ms.model.User;
import in.stackroute.ms.repository.UserRepository;
import in.stackroute.ms.service.FavService;
import in.stackroute.ms.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class FavServiceTest {
	
	@Autowired
	private FavService service;
	
	@Autowired
	@InjectMocks
	private UserService userService;
	
	@MockBean
	private UserRepository repo;
	
	private User user;
	private Favourites f;
	List<Favourites> favorite;
	
	
	@BeforeEach
	public void preConfig() {
		
		user = new User();
		user.setUserId(1l);
		user.setUsername("testUser");
		user.setPassword("testPass");
		
		f = new Favourites();
//		news.setId(1);
//		news.setAuthor("testAuthor");
//		news.setTitle("testTitle");
//		news.setUrl("testUrl");
//		news.setContent("this is a test content for news id 1");
//		news.setDescription("this is test description for news");
//		
		f.setFavoriteId(1);
		f.setId("1");
		f.setName("testName");
		f.setAuthor("testAuthor");
		f.setTitle("testTitle");
		f.setDescription("test description");
		f.setUrlother("url other");
		f.setUrlToImage("url");
		f.setPublishedAt("published");
		f.setContent("hello ");
		
		
		favorite = new ArrayList<Favourites>();
		favorite.add(f);
		user.setFavourites(favorite);
	}
	

	@Test
	public void serviceTest() {
		FavService service = Mockito.mock(FavService.class);
		assertNotNull(service);
	}
	
	@Test
	public void saveFavoriteSuccessTest() {
		
		Mockito.when(repo.findByUsername(user.getUsername())).thenReturn(user);
		Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(user);
		//Favourites testFav = service.savefav(user.getUsername(), f);   
		User testUser = userService.createUser(user);
		assertEquals(user, testUser);
		assertEquals(user.getFavourites(), testUser.getFavourites());
		assertEquals(service.savefav(user.getUsername(), f), f);
				
	}
	
	@Test
	public void saveFavoriteFailureTest() {
		
		Mockito.when(repo.findByUsername(user.getUsername())).thenReturn(user);
		Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(user);
		//Favourites testFav = service.savefav(user.getUsername(), f);   
		User testUser = userService.createUser(user);
		assertEquals(user, testUser);
		assertEquals(user.getFavourites(), testUser.getFavourites());
		
		assertNotEquals(service.savefav(user.getUsername(), f), new Favourites());
				
	}
	
	@Test
	public void getAllFavoriteSuccessTest() {
		Mockito.when(repo.findByUsername(user.getUsername())).thenReturn(user);
		List<Favourites> fav = user.getFavourites();
		assertEquals(service.getAllFav(user.getUsername()), favorite);
	}
	
	@Test
	public void getAllFavoriteFailureTest() {
		Mockito.when(repo.findByUsername(user.getUsername())).thenReturn(user);
		List<Favourites> fav = new ArrayList<Favourites>();
		assertNotEquals(service.getAllFav(user.getUsername()), fav);
	}
	
	@Test
	public void emptyFavourites() {
		Mockito.when(repo.findByUsername(user.getUsername())).thenReturn(user);
		Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(user); 
		User testUser = userService.createUser(user);
		service.emptyFavourites(user.getUsername());
		assertEquals(user, testUser);
	}
	

	@Test
	public void deleteFavSuccessTest() {
		Mockito.when(repo.findByUsername(user.getUsername())).thenReturn(user);
		assertEquals(service.deleteFav(user.getUsername(), 1), f);
	}
	
	@Test
	public void deleteFavFailureTest() {
		Mockito.when(repo.findByUsername(user.getUsername())).thenReturn(user);
		assertNotEquals(service.deleteFav(user.getUsername(), 2), f);
	}
	

}
