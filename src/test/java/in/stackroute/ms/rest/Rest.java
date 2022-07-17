package in.stackroute.ms.rest;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@SpringBootTest
@AutoConfigureMockMvc
public class Rest {
	
	@Test
	public void testNewsSuccess() {
		RequestSpecification request=RestAssured.given();
		Response response=request.get("http://localhost:8080/news");
		int code=response.getStatusCode();
		assertEquals(code, 200);
		System.out.println("1");
	}
	
	 @Test
		public void testNewsFailure() {
			RequestSpecification request=RestAssured.given();
			Response response=request.get("http://localhost:8080/abc");
			int code=response.getStatusCode();
			assertEquals(code, 403);
			System.out.println("2");
		}

	@Test
	public void testNewsData() {
		given()
		.get("http://localhost:8080/news")
		.then()
		.statusCode(200)
		.and()
		.body("status[0]", Matchers.equalTo("ok"));
		System.out.println("3");
	}
	
}