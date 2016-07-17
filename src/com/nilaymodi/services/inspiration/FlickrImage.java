package com.nilaymodi.services.inspiration;

public class FlickrImage {
	private final String	url;
	private final String	author;
	private final String	title;

	public FlickrImage(String url, String author, String title) {
		this.url = url;
		this.author = author;
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}
}
