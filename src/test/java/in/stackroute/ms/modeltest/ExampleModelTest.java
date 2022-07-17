package in.stackroute.ms.modeltest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import in.stackroute.ms.model.Articles;
import in.stackroute.ms.model.Example;

@SpringBootTest
public class ExampleModelTest {
	
	private Example e;
    private Articles f;
	
	@BeforeEach
	public void preConfig() {
		e=new Example();
		
		e.setStatus("200");
		e.setTotalResults(100);
		
		f = new Articles();		
		f.setId("1");
		f.setName("testName");
		f.setAuthor("testAuthor");
		f.setTitle("testTitle");
		f.setDescription("test description");
		f.setUrlOther("url other");
		f.setUrlToImage("url");
		f.setPublishedAt("published");
		f.setContent("hello ");
		
		e.setArticles(f);
}
	@Test
	public void ExampleTest() {
		Example ex = Mockito.mock(Example.class);
		assertNotNull(ex);
	}
	
	@Test
	public void ExampleTestSuccecess() {
		assertEquals(e.getStatus(), "200");
		assertEquals(e.getTotalResults(),100);
		assertEquals(e.getArticles(),f);
		
	}
	@Test
	public void ExampleTestFailure() {
		assertNotEquals(e.getStatus(), "");
		assertNotEquals(e.getArticles(),new Articles());
		
	}
	
	

}
