package in.stackroute.ms.servicetest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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
public class UserServiceTest {

	@MockBean
	private UserRepository repo;
	
	@Autowired
	private UserService service;
	
	@MockBean
	private User user;
	
	@Test
	public void serviceTest() {
		UserService service = Mockito.mock(UserService.class);
		assertNotNull(service);
	}
	
	@Test
	  public void testGetUserByUsername() {
		
		//User(Long userId, String username, String password, List<Favourites> favourites)
		User u=new User(1l,"ABC","123");
		String name="ABC";
		Mockito.when(repo.findByUsername(ArgumentMatchers.anyString())).thenReturn(u);
		assertEquals(service.getUserByUsername(name).getPassword(), "123");

	}
	
	@Test
	  public void NegtestGetUserByUsername() {
		User u=new User(1l,"ABC","123");
		String name="ABC";
		Mockito.when(repo.findByUsername(ArgumentMatchers.anyString())).thenReturn(u);
		assertNotEquals(service.getUserByUsername(name).getPassword(), "12345");

	}
	
	@Test
	  public void testCreateUser() {

		User u=new User(1l,"ABC","123");
		Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(u);
		assertEquals(service.createUser(u).getUsername(), "ABC");
	}
	
	@Test
	  public void NegtestCreateUser() {

		User u=new User(1l,"ABC","123");
		Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(u);
		assertNotEquals(service.createUser(u).getUsername(), "123");
	}
	
	
	@Test
	  public void testUpdateUser() {
		User u=new User(1l,"ABC","123");
		Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(u);
		assertEquals(service.updateUser(u).getUserId(), 1);
	}
	
	@Test
	  public void NegtestUpdateUser() {
		User u=new User(1l,"ABC","123");
		User A=new User();
		Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(u);
		assertNotEquals(service.updateUser(u), A);
	}
	
	@Test
	  public void testDeleteUser() {
		User u=new User(1l,"ABC","123");
		String name="ABC";
		Mockito.when(repo.findByUsername(u.getUsername())).thenReturn(u);
		//Mockito.when(user.getUserId()).thenReturn(1l);
		assertTrue(service.deleteUser(name));
	}

}
