package in.stackroute.ms.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import in.stackroute.ms.model.Example;
import in.stackroute.ms.service.NewsService;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsServiceTest {
	
	@MockBean
	private NewsService s;
	
	@Test
	public void testRefinedUpdate() throws IOException {
		 List<Example> newsList = new ArrayList<Example>();
		 assertEquals(s.sendRefinedUpdate(), newsList);
	}
	
	@Test
	public void NegRefinedUpdate() throws IOException {
		 List<Example> newsList = new ArrayList<Example>();
		 assertNotNull(s.sendRefinedUpdate());
	}
	

}