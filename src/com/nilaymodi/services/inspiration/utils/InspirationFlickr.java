package com.nilaymodi.services.inspiration.utils;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.Transport;
import com.flickr4java.flickr.photos.PhotosInterface;

public class InspirationFlickr extends Flickr {

	public InspirationFlickr(String apiKey, String sharedSecret, Transport transport) {
		super(apiKey, sharedSecret, transport);
	}

	@Override
	public PhotosInterface getPhotosInterface() {
		return new InspirationPhotosInterface(getApiKey(), getSharedSecret(), getTransport());
	}

}
