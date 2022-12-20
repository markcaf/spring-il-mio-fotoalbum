package org.generation.italy.demo.pojo;

import org.hibernate.validator.constraints.URL;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "The title cannot be null")
	@NotEmpty(message = "The title cannot be empty")
	private String title;
	
	@Lob
	@Nullable
	@Column(name="description")
	private String description;
	
	@URL(message="The image path is not valid")
	@Column(name="image_url")
	private String imageUrl;
	
	@Nullable
	@Column(name="tag")
	private String tag;
	
	
	@Column(name="visibility")
	@NotNull(message = "The visibility cannot be null")
	private boolean visible;
	
	public Photo() {}
	public Photo(String title, String description, String imageUrl, String tag, boolean visible) {
		setTitle(title);
		setDescription(description);
		setImageUrl(imageUrl);
		setTag(tag);
		setVisible(visible);
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	@Override
	public String toString() {
		return "ID: " + getId()
				+ "\nTitle: " + getTitle()
				+ "\nDescription: " + getDescription()
				+ "\nImage URL: " + getImageUrl()
				+ "\nTag: " + getTag()
				+ "\nVisible: " + isVisible();
	}
	
}
