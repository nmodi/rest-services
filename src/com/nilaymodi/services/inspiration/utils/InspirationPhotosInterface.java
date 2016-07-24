package com.nilaymodi.services.inspiration.utils;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.Response;
import com.flickr4java.flickr.Transport;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotoUtils;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;

public class InspirationPhotosInterface extends PhotosInterface {

	protected String	apiKeyInsp;
	protected String	sharedSecretInsp;
	protected Transport	transportInsp;

	public InspirationPhotosInterface(String apiKey, String sharedSecret, Transport transport) {
		super(apiKey, sharedSecret, transport);

		this.apiKeyInsp = apiKey;
		this.sharedSecretInsp = sharedSecret;
		this.transportInsp = transport;
	}

	@Override
	public PhotoList<Photo> search(SearchParameters params, int perPage, int page)
			throws FlickrException {
		PhotoList<Photo> photos = new PhotoList<Photo>();

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("method", METHOD_SEARCH);

		parameters.putAll(params.getAsParameters());

		if (perPage > 0) {
			parameters.put("per_page", "" + perPage);
		}
		if (page > 0) {
			parameters.put("page", "" + page);
		}

		parameters.put("safe_search", "1");
		parameters.put("content_type", "1");

		Response response = transportInsp.get(transportInsp.getPath(), parameters, apiKeyInsp,
				sharedSecretInsp);
		if (response.isError()) {
			throw new FlickrException(response.getErrorCode(), response.getErrorMessage());
		}
		Element photosElement = response.getPayload();
		photos.setPage(photosElement.getAttribute("page"));
		photos.setPages(photosElement.getAttribute("pages"));
		photos.setPerPage(photosElement.getAttribute("perpage"));
		photos.setTotal(photosElement.getAttribute("total"));

		NodeList photoNodes = photosElement.getElementsByTagName("photo");
		for (int i = 0; i < photoNodes.getLength(); i++) {
			Element photoElement = (Element) photoNodes.item(i);
			photos.add(PhotoUtils.createPhoto(photoElement));
		}
		return photos;
	}

}
