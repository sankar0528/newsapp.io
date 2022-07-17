package in.stackroute.ms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.stackroute.ms.model.Articles;
import in.stackroute.ms.model.Example;



@Service
public class NewsService extends MappingJackson2HttpMessageConverter {
	

	private static NewsService ourInstance = new NewsService();
	
	 public static NewsService getInstance() {
	        return ourInstance;
	    }
	 
	    private NewsService() {
	    	setPrettyPrint(true);
	    }  
	    public List<Example> sendRefinedUpdate() throws IOException {	
	    	 
	    	//String urlString = "https://newsapi.org/v2/everything?q=tesla&from=2021-04-13&sortBy=publishedAt&apiKey=4f7aa805d4554730bdd04eae199d9519";

	    	String urlString ="https://newsapi.org/v2/everything?q=ukraine&apiKey=710898b5aacf490a88d1eee4df077c81";
	    	 RestTemplate restTemplate = new RestTemplate();
	    	    String result = restTemplate.getForObject(urlString, String.class);	    	
	    		    	  
	    	    JSONObject root = new JSONObject(result); 
	    	    
	    		String status = null;
	            Integer totalResults = null;
	            String id = null;
	            String name = null;                
	            String author = null;         		
	    		String title = null;
	    		String description = null;
	    		String urlother = null;
	    		String urlToImage = null;
	    		String publishedAt = null;
	    		String content = null;
	            
	            List<Example> newsList = new ArrayList<>();
	            				
				status =  root.getString("status");
				totalResults =  root.getInt("totalResults");
				
				 JSONArray articlesObject = root.getJSONArray("articles");

			        for(int i = 0; i < articlesObject.length(); i++) {
			              	
			            JSONObject arrayElement = articlesObject.getJSONObject(i);
			           
			            JSONObject sourceother = arrayElement.getJSONObject("source");
			     	        
			            if(!sourceother.isNull("id")){ 
			            	id = sourceother.getString("id");
			            	}else {
			            		id=null;
			            	}   
			            
			            name =  sourceother.getString("name");
			            			          
			            if(!arrayElement.isNull("author")){ 
			            	author = arrayElement.getString("author");
			            	}else {
			            		author = null;
			            	}    
			            
			            title = arrayElement.getString("title");
			            			         
			            if(!arrayElement.isNull("description")){ 
			            	description = arrayElement.getString("description");
			            	}else {
			            		description = null;
			            	}    
			            
			            urlother = arrayElement.getString("url");
			            			        
			            if(!arrayElement.isNull("urlToImage")){ 
			            	urlToImage = arrayElement.getString("urlToImage");
			            	}else {
			            		urlToImage = null;
			            	}    
			            
			            publishedAt = arrayElement.getString("publishedAt");
			            			         
			           if(!arrayElement.isNull("content")){ 
			            	content = arrayElement.getString("content");
			            	}else {
			            		content = null;
			            	}    
			            			            
				         Example emp = new Example();				    	 
				    	Articles articles = new Articles();				    			
				    	
			            emp.setStatus(status);
			            emp.setTotalResults(totalResults);
			            articles.setId(id);
			            articles.setName(name);
			            articles.setAuthor(author);
			            articles.setContent(content);
			            articles.setDescription(description);
			            articles.setPublishedAt(publishedAt);
			            articles.setTitle(title);
			            articles.setUrlOther(urlother);
			            articles.setUrlToImage(urlToImage);
			            
			            emp.setArticles(articles);
			            
			            //System.out.println("hello"+emp.toString());
			            newsList.add(emp);
						
	    }    
			        
			        return newsList;
    }
}
	    
