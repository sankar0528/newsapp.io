package in.stackroute.ms.restAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import in.stackroute.ms.model.Favourites;
import in.stackroute.ms.model.User;

public class FavControllerRestTest {

   private ObjectMapper mapper = new ObjectMapper();
   int id; 
	
	@Test
	@Order(1)
	public void testAddToFavourites() throws Exception {
		User user=new User();
		user.setUsername("username");
		user.setPassword("password");
		String userJson = mapper.writeValueAsString(user);
		
		String result = given().contentType("application/json").with().body(userJson).when().contentType("application/json")
				.post("http://localhost:8080/authenticate").then().assertThat().statusCode(200).extract().asString();
				
		String jwt = JsonPath.read(result, "$.jwt");
		
	
		Favourites f=new Favourites();
		f.setName("Test add");
		
		id=given().header("Authorization", "Bearer " + jwt).with().body(f).when()
		.contentType("application/json").post("http://localhost:8080/favourite").then().statusCode(200).extract().path("favoriteId");
	    
	}
	@Test
	@Order(2)
	public void testdeleteFavourites() throws Exception {
		User user=new User();
		user.setUsername("username");
		user.setPassword("password");
		String userJson = mapper.writeValueAsString(user);
		
		String result = given().contentType("application/json").with().body(userJson).when().contentType("application/json")
				.post("http://localhost:8080/authenticate").then().assertThat().statusCode(200).extract().asString();
				
		String jwt = JsonPath.read(result, "$.jwt");
		given().header("Authorization", "Bearer " + jwt).when().contentType("application/json")
		.put("http://localhost:8080/favourites/{id}",id).then().statusCode(200);
	}
	@Test
	@Order(3)
	public void testGetFavourites() throws Exception {
		User user=new User();
		user.setUsername("username");
		user.setPassword("password");
		String userJson = mapper.writeValueAsString(user);
		
		String result = given().contentType("application/json").with().body(userJson).when().contentType("application/json")
				.post("http://localhost:8080/authenticate").then().assertThat().statusCode(200).extract().asString();
				
		String jwt = JsonPath.read(result, "$.jwt");
		given().header("Authorization", "Bearer " + jwt).when().contentType("application/json")
		.get("http://localhost:8080/favourite").then().statusCode(200);
	}
	@Test
	@Order(4)
	public void testEmptyFavourites() throws Exception {
		User user=new User();
		user.setUsername("username");
		user.setPassword("password");
		String userJson = mapper.writeValueAsString(user);
		
		String result = given().contentType("application/json").with().body(userJson).when().contentType("application/json")
				.post("http://localhost:8080/authenticate").then().assertThat().statusCode(200).extract().asString();
				
		String jwt = JsonPath.read(result, "$.jwt");
		given().header("Authorization", "Bearer " + jwt).when().contentType("application/json")
		.delete("http://localhost:8080/favourites").then().statusCode(200);
	}
}
