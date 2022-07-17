package in.stackroute.ms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.Assertions;
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

import in.stackroute.ms.model.User;
import in.stackroute.ms.security.MyUserDetailsService;
import in.stackroute.ms.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private UserService userService;

	@MockBean
	private MyUserDetailsService udService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	private static long randomId;
	private static String randomString;
	private static User user;

	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testCreateUser() throws Exception {
		randomId = new Random().nextLong();
		randomString = RandomStringUtils.randomAlphabetic(10);

		user = new User();
		user.setUserId(randomId);
		user.setUsername(randomString);
		user.setPassword(randomString);

		String userJson = mapper.writeValueAsString(user);
		Mockito.when(userService.createUser(user)).thenReturn(user);
		mockmvc.perform(MockMvcRequestBuilders.post("/user/register").contentType(MediaType.APPLICATION_JSON).content(userJson))
		.andExpect(status().isOk());

	}
	
	@Test
	public void testGetOwnData() throws Exception {
		randomId = new Random().nextLong();
		randomString = RandomStringUtils.randomAlphabetic(10);

		user = new User();
		user.setUserId(randomId);
		user.setUsername(randomString);
		user.setPassword(randomString);

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

		mockmvc.perform(MockMvcRequestBuilders.get("/user/").header("Authorization", "Bearer " + jwt))
				.andExpect(status().isOk()).andExpect(jsonPath("$.username", Matchers.equalTo(randomString)));

	}
	@Test
	public void testDeleteUser() throws Exception {
		randomId = new Random().nextLong();
		randomString = RandomStringUtils.randomAlphabetic(10);

		user = new User();
		user.setUserId(randomId);
		user.setUsername(randomString);
		user.setPassword(randomString);
		
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
		Mockito.when(userService.deleteUser(randomString)).thenReturn(true);

		mockmvc.perform(MockMvcRequestBuilders.delete("/user/").header("Authorization", "Bearer " + jwt))
				.andExpect(status().isOk()).andDo(mvcResult -> {
					Assertions.assertEquals("true", mvcResult.getResponse().getContentAsString());
				});
	}

	
	
	  @Test 
	  public void testUpdateUser() throws Exception {
		  randomId = new Random().nextLong();
			randomString = RandomStringUtils.randomAlphabetic(10);

			user = new User();
			user.setUserId(randomId);
			user.setUsername(randomString);
			user.setPassword(randomString);
	  
	     String userJson = mapper.writeValueAsString(user);
	  
	     org.springframework.security.core.userdetails.User userT = new
	       org.springframework.security.core.userdetails.User( randomString,
	             passwordEncoder.encode(randomString), new ArrayList<>());
	  
	     Mockito.when(udService.loadUserByUsername(randomString)).thenReturn(userT);
	  
	   MvcResult result = mockmvc
	    .perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON) 
	    .content(userJson)).andExpect(jsonPath("$.jwt",Matchers.notNullValue())).andExpect(status().isOk()).andReturn();
	  
	  String jwt = JsonPath.read(result.getResponse().getContentAsString(),"$.jwt");
	  
	  user.setUsername("Updated Name"); userJson = mapper.writeValueAsString(user);
	  Mockito.when(userService.getUserByUsername(randomString)).thenReturn(user);
	  Mockito.when(userService.updateUser(user)).thenReturn(user);
	  Mockito.when(userService.getUserByUsername(randomString)).thenReturn(user);
	  result = mockmvc.perform(MockMvcRequestBuilders.put("/user/").header("Authorization","Bearer " + jwt).contentType(MediaType.APPLICATION_JSON)
			  .content(userJson)).andExpect(status().isOk()).andDo(print()).andReturn();
	  
	  }
	 
	

}
