package in.stackroute.ms.modeltest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import in.stackroute.ms.model.Articles;
import in.stackroute.ms.model.Favourites;


@SpringBootTest
public class ArticlesModelTest {
	
private Articles f;
	
	@BeforeEach
	public void preConfig() {
		
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
}
	@Test
	public void FavTest() {
		Articles a = Mockito.mock(Articles.class);
		assertNotNull(a);
	}
	
	@Test
	public void FavModelTestSuccess() {
		assertEquals(f.getId(),"1");
		assertEquals(f.getName(),"testName");
		assertEquals(f.getAuthor(),"testAuthor");
		assertEquals(f.getTitle(),"testTitle");
		assertEquals(f.getDescription(),"test description");
		assertEquals(f.getUrlOther(),"url other");
		assertEquals(f.getUrlToImage(),"url");
		assertEquals(f.getPublishedAt(),"published");
		assertEquals(f.getContent(),"hello ");
		
	}
	@Test
	public void FavModelTestFailure() {
		assertNotEquals(f.getId(),"");
		assertNotEquals(f.getName(),"");
		assertNotEquals(f.getAuthor(),"");
		assertNotEquals(f.getTitle(),"");
		assertNotEquals(f.getDescription(),"");
		assertNotEquals(f.getUrlOther(),"");
		assertNotEquals(f.getUrlToImage(),"");
		assertNotEquals(f.getPublishedAt(),"");
		assertNotEquals(f.getContent(),"");
		
	}

}
