package in.stackroute.ms.rest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerRest {
	
	static String token;
	static String randomString=RandomStringUtils.randomAlphabetic(10);
	
	
	@Test//(priority = 1)
	@Order(1)
	public void getSaveSuccess() throws JSONException  {
		
		RequestSpecification request=RestAssured.given();
		request.header("Content-Type","application/json");
		JSONObject json=new JSONObject();
		json.put("username",randomString);
		json.put("password","111");
		request.body(json.toString());
		Response r=request.post("http://localhost:8080/user/register");
		int code=r.getStatusCode();
		assertEquals(code, 200);
		assertEquals(r.jsonPath().getString("username"), randomString);
	}
	
	@Test
	public void getSaveFailure() throws JSONException  {
		
		RequestSpecification request=RestAssured.given();
		request.header("Content-Type","application/json");
		JSONObject json=new JSONObject();
		json.put("username","");
		json.put("password","");
		request.body(json.toString());
		Response r=request.post("http://localhost:8080/user/reg");
		int code=r.getStatusCode();
		assertEquals(code, 403);
	}
	
	
	@Test//(priority = 2)
	@Order(2)
	public void getAuthenticateSuccess() throws JSONException {
		
		RequestSpecification request=RestAssured.given();
		JSONObject json=new JSONObject();
		json.put("username",randomString);
		json.put("password","111");
		request.header("Content-Type","application/json");
		request.body(json.toString());
		Response r=request.post("http://localhost:8080/authenticate");
		String jwt=r.jsonPath().getString("jwt");
		token=jwt;
		assertEquals(r.getStatusCode(), 200);
		assertNotNull(jwt);
		System.out.print(r.prettyPrint());
		System.out.println(jwt);	
	}
	
	@Test//(priority = 2)
	public void getAuthenticateFailure() throws JSONException {
		
		RequestSpecification request=RestAssured.given();
		JSONObject json=new JSONObject();
		json.put("username","");
		json.put("password","");
		request.header("Content-Type","application/json");
		request.body(json.toString());
		Response r=request.post("http://localhost:8080/authenticate");
		String j=r.jsonPath().getString("jwt");
		assertNull(j);	
	}
	
	@Test//(priority = 3) //disable if u get ex
	@Order(3)
     public void GetUserSuccess() throws JSONException {
			
			RequestSpecification request2=RestAssured.given();
			request2.header("Content-Type","application/json");
			request2.header("Authorization","Bearer "+token);
			Response r2=request2.get("http://localhost:8080/user/");
			assertEquals(r2.getStatusCode(), 200);
			assertEquals(r2.jsonPath().getString("username"), randomString);
			System.out.println(r2.prettyPrint());
	}

		@Test
	     public void GetUserFailure() throws JSONException {
			
				RequestSpecification request=RestAssured.given();
				request.header("Content-Type","application/json");
				Response r=request.get("http://localhost:8080/user/");
				assertEquals(r.getStatusCode(), 403);
				r.then().body("message", Matchers.equalTo("Access Denied"));
				System.out.print(r.prettyPrint());
		}
		
		 @Test
	     public void GetUpdateSuccess() throws JSONException {
			 
				RequestSpecification request2=RestAssured.given();
				request2.header("Content-Type","application/json");
				request2.header("Authorization","Bearer "+token);
				Response r2=request2.put("http://localhost:8080/user/");
				assertEquals(r2.getStatusCode(), 403);
				System.out.println(r2.prettyPrint());
		}
		
		
	 
	 @Test  //(priority = 4)
	 @Order(4)
     public void DeleteUserSuccess() throws JSONException {
			RequestSpecification request2=RestAssured.given();
			request2.header("Content-Type","application/json");
			request2.header("Authorization","Bearer "+token);
			Response r2=request2.delete("http://localhost:8080/user/");
			assertEquals(r2.getStatusCode(), 200);
			System.out.println(r2.prettyPrint());
	}
	 

}
