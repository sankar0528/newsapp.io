package in.stackroute.ms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Favourites {
	
	@Id
	@Column(name="favorite_id",nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  favoriteId;
	
	private String id;

	private String name;

	private String author;
	
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String description;

	private String urlother;
	
	private String urlToImage;
	
	private String publishedAt;
	
	@Column(columnDefinition="TEXT")
	private String content;

	public int getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
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

	public String getUrlother() {
		return urlother;
	}

	public void setUrlother(String urlother) {
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
	

	public Favourites() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Favourites(int favoriteId, String id, String name, String author, String title, String description,
			String urlother, String urlToImage, String publishedAt, String content) {
		super();
		this.favoriteId = favoriteId;
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
	
	
}
