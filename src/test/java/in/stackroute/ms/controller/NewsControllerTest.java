package in.stackroute.ms.controller;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import in.stackroute.ms.model.Articles;
import in.stackroute.ms.model.Example;
import in.stackroute.ms.service.NewsService;


@SpringBootTest()
@AutoConfigureMockMvc
public class NewsControllerTest {

	@Autowired
	private MockMvc mvc;
	@Mock
	private NewsService service;
	
	private List<Example> example;
	@Before
	public void setUp(){
		Example example1 = new Example();
		example1.setTotalResults(20);
		
		Articles firstArticle = new Articles();
		
		example1.setArticles(firstArticle);
	}
	@Test
	public void testGetNews() throws Exception {
		Mockito.when(service.sendRefinedUpdate()).thenReturn(example);
		MockHttpServletResponse resp = mvc.perform(
				MockMvcRequestBuilders.get("https://newsapi.org/v2/everything?q=tesla&from=2021-04-11&sortBy=publishedAt&apiKey=4f7aa805d4554730bdd04eae199d9519").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		String headResp =  resp.getContentAsString();
		
		assert(headResp!=null);
	}
}
