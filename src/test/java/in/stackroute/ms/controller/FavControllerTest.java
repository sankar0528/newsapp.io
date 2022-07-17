package in.stackroute.ms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import in.stackroute.ms.model.Favourites;
import in.stackroute.ms.model.User;
import in.stackroute.ms.security.MyUserDetailsService;
import in.stackroute.ms.service.FavService;
import in.stackroute.ms.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class FavControllerTest {

	@Autowired
	private MockMvc mockmvc;
	@MockBean
	private UserService userService;
	@MockBean
	private FavService favService;
	@MockBean
	private MyUserDetailsService udService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private static long randomId;
	private static int randomId1;
	private static String randomString;
	private static User user;
    private static Favourites fav;
    private static List<Favourites> articles;
    
    private ObjectMapper mapper = new ObjectMapper();
   
    @BeforeAll
	public  static void setUp() {
    	randomId = new Random().nextLong();
    	randomId1 = new Random().nextInt();
		randomString = RandomStringUtils.randomAlphabetic(10);

		user = new User();
		user.setUserId(randomId);
		user.setUsername(randomString);
		user.setPassword(randomString);
        fav = new Favourites();
        fav.setFavoriteId(randomId1);
        fav.setId(randomString);
        fav.setName("Favourite_"+randomString);
        fav.setAuthor(randomString);
        fav.setTitle(randomString);
        fav.setContent(randomString);
        fav.setDescription(randomString);
        fav.setPublishedAt(randomString);
        fav.setUrlother(randomString);
        fav.setUrlToImage(randomString);
        articles = new ArrayList<Favourites>();
        articles.add(fav);
        user.setFavourites(articles);
        System.out.print("ok");
    }
   

    @Test
    public void testGetFavourites() throws Exception {
    	
		String userJson = mapper.writeValueAsString(user);

		org.springframework.security.core.userdetails.User userT = new org.springframework.security.core.userdetails.User(
				randomString, passwordEncoder.encode(randomString), new ArrayList<>());

		Mockito.when(udService.loadUserByUsername(randomString)).thenReturn(userT);

		MvcResult result = mockmvc
				.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(jsonPath("$.jwt", Matchers.notNullValue())).andExpect(status().isOk()).andReturn();

		String jwt = JsonPath.read(result.getResponse().getContentAsString(), "$.jwt");

		Mockito.when(userService.getUserByUsername(randomString)).thenReturn(user);
		Mockito.when(favService.getAllFav(randomString)).thenReturn(articles);

		mockmvc.perform(MockMvcRequestBuilders.get("/favourite").header("Authorization", "Bearer " + jwt))
				.andExpect(status().isOk());
	}

    @Test
	public void testAddToFavorites() throws Exception {

		String userJson = mapper.writeValueAsString(user);

		org.springframework.security.core.userdetails.User userT = new org.springframework.security.core.userdetails.User(
				randomString, passwordEncoder.encode(randomString), new ArrayList<>());

		Mockito.when(udService.loadUserByUsername(randomString)).thenReturn(userT);

		MvcResult result = mockmvc
				.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(jsonPath("$.jwt", Matchers.notNullValue())).andExpect(status().isOk()).andReturn();

		String jwt = JsonPath.read(result.getResponse().getContentAsString(), "$.jwt");

		Favourites f=new Favourites();
		f.setName("Test add");

		Mockito.when(favService.savefav(randomString, f)).thenReturn(f);

		articles.add(f);
		Mockito.when(userService.getUserByUsername(randomString)).thenReturn(user);
		Mockito.when(favService.getAllFav(randomString)).thenReturn(articles);

		String favouriteJson = mapper.writeValueAsString(f);

		mockmvc.perform(MockMvcRequestBuilders.post("/favourite").header("Authorization", "Bearer " + jwt)
				.contentType(MediaType.APPLICATION_JSON).content(favouriteJson)).andExpect(status().isOk()).andDo(res -> {
					System.out.println(res.getResponse().getContentAsString());
				});
	}

    @Test
	public void testRemoveFromFavourites() throws Exception {

		String userJson = mapper.writeValueAsString(user);

		org.springframework.security.core.userdetails.User userT = new org.springframework.security.core.userdetails.User(
				randomString, passwordEncoder.encode(randomString), new ArrayList<>());

		Mockito.when(udService.loadUserByUsername(randomString)).thenReturn(userT);

		MvcResult result = mockmvc
				.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(jsonPath("$.jwt", Matchers.notNullValue())).andExpect(status().isOk()).andReturn();

		String jwt = JsonPath.read(result.getResponse().getContentAsString(), "$.jwt");

		Mockito.when(favService.deleteFav(randomString, randomId1)).thenReturn(fav);
		
		Mockito.when(userService.getUserByUsername(randomString)).thenReturn(user);
		Mockito.when(favService.getAllFav(randomString)).thenReturn(articles);
		mockmvc.perform(MockMvcRequestBuilders.put("/favourites/"+randomId1).header("Authorization", "Bearer " + jwt)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(res -> {
					System.out.println(res.getResponse().getContentAsString());
				});
	}
    @Test
	public void testEmptyFavourites() throws Exception {

		String userJson = mapper.writeValueAsString(user);

		org.springframework.security.core.userdetails.User userT = new org.springframework.security.core.userdetails.User(
				randomString, passwordEncoder.encode(randomString), new ArrayList<>());

		Mockito.when(udService.loadUserByUsername(randomString)).thenReturn(userT);

		MvcResult result = mockmvc
				.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(jsonPath("$.jwt", Matchers.notNullValue())).andExpect(status().isOk()).andReturn();

		String jwt = JsonPath.read(result.getResponse().getContentAsString(), "$.jwt");

		
		Mockito.when(userService.getUserByUsername(randomString)).thenReturn(user);
		Mockito.when(favService.getAllFav(randomString)).thenReturn(articles);

		mockmvc.perform(MockMvcRequestBuilders.delete("/favourites").header("Authorization", "Bearer " + jwt)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(res -> {
					String response = (res.getResponse().getContentAsString());
					Assertions.assertTrue(response.contains("Favourites emptied successfully."));
				});
	}


}
