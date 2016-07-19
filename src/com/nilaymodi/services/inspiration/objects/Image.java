package com.nilaymodi.services.inspiration.objects;

public class Image {
	private String	imageUrl;
	private String	pageUrl;
	private String	author;
	private String	title;

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getPageUrl() {
		return pageUrl;
	}
}
