package in.stackroute.ms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","name","author","title","description","urlother","urlToImage","publishedAt","content"})
public class Articles {
    
    
    
    @JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("author")
	private String author;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("description")
	private String description;

	@JsonProperty("urlother")
	private String urlother;
	
	@JsonProperty("urlToImage")
	private String urlToImage;
	
	@JsonProperty("publishedAt")
	private String publishedAt;
	
	@JsonProperty("content")
	private String content;
	
	
	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlOther() {
		return urlother;
	}

	public void setUrlOther(String urlother) {
		this.urlother = urlother;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Articles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Articles(String id, String name, String author, String title, String description, String urlother,
			String urlToImage, String publishedAt, String content) {
		super();
		
		this.id = id;
		this.name = name;
		this.author = author;
		this.title = title;
		this.description = description;
		this.urlother = urlother;
		this.urlToImage = urlToImage;
		this.publishedAt = publishedAt;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Articles [ id=" + id + ", name=" + name + ", author=" + author + ", title=" + title
				+ ", description=" + description + ", urlother=" + urlother + ", urlToImage=" + urlToImage
				+ ", publishedAt=" + publishedAt + ", content=" + content + "]";
	}

	
	
}