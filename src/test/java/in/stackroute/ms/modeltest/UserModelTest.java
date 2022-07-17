package in.stackroute.ms.modeltest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import in.stackroute.ms.model.Favourites;
import in.stackroute.ms.model.User;

@SpringBootTest
public class UserModelTest {
	
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
	public void userTest() {
		User user = Mockito.mock(User.class);
		assertNotNull(user);
	}
	
	@Test
	public void userModelTestSuccess() {
		assertEquals(user.getUserId(), 1l);
		assertEquals(user.getUsername(),"testUser");
		assertEquals(user.getPassword(),"testPass");
		assertEquals(user.getFavourites(), favorite);
		
	}
	
	@Test
	public void userModelTestFailure() {
		assertNotEquals(user.getUserId(), "10l");
		assertNotEquals(user.getUsername(),"aa");
		assertNotEquals(user.getPassword(),"b");
		assertNotEquals(user.getFavourites(), "c");
		
	}
	

}
