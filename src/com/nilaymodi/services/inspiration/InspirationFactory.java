package com.nilaymodi.services.inspiration;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.nilaymodi.services.inspiration.objects.Image;
import com.nilaymodi.services.inspiration.objects.Inspiration;
import com.nilaymodi.services.inspiration.options.InspirationOptions;
import com.nilaymodi.services.inspiration.options.InspirationOptionsHandler;
import com.nilaymodi.services.inspiration.utils.Constants;
import com.nilaymodi.services.inspiration.utils.InspirationFlickr;
import com.nilaymodi.services.inspiration.utils.InspirationStringUtils;

public class InspirationFactory {

	/**
	 * Generates a semi-random Inspiration object
	 * 
	 * @return
	 */
	public static Inspiration generate() {
		InspirationOptions options = InspirationOptionsHandler.get().getOptions();
		Inspiration inspiration = new Inspiration();

		setSubject(options, inspiration);
		inspiration.setLensType(chooseOption(options.getLensTypes(), Constants.SKIP_RATE_LOW));
		inspiration.setTime(chooseOption(options.getTimes(), Constants.SKIP_RATE_DEFAULT));
		inspiration.setLight(chooseOption(options.getLights(), Constants.SKIP_RATE_MEDIUM));
		inspiration.setModifier(chooseOption(options.getModifiers(), Constants.SKIP_RATE_HIGH));
		inspiration.setColor(chooseOption(options.getColors(), Constants.SKIP_RATE_HIGH));

		inspiration.setPhoto(retrievePhoto(InspirationStringUtils.buildSearchTags(inspiration)));
		inspiration.setSentence(InspirationStringUtils.buildSentence(inspiration));

		return inspiration;
	}

	/**
	 * Sets the subject or concept for the Inspiration. Inspiration can have a
	 * subject, concept, or neither.
	 * 
	 * @param options
	 * @param inspiration
	 */
	private static void setSubject(InspirationOptions options, Inspiration inspiration) {

		double d = ThreadLocalRandom.current().nextDouble();

		if (d < 0.50) {
			inspiration.setSubject(getRandomStringFromList(options.getSubjects()));
		} else if (d < 0.75) {
			inspiration.setPluralSubject(getRandomStringFromList(options.getPluralSubject()));
		} else {
			// Do nothing
		}
	}

	/**
	 * Randomly selects one of the choices from the list or skips the field
	 * entirely based on the skip rate.
	 * 
	 * @param choices
	 * @param skipRate
	 * @return
	 */
	private static String chooseOption(List<String> choices, double skipRate) {
		String ret = null;
		if (ThreadLocalRandom.current().nextDouble() > skipRate) {
			ret = getRandomStringFromList(choices);
		}
		return ret;
	}

	/**
	 * Randomly selects one of the choices from the list.
	 * 
	 * @param list
	 * @return
	 */
	private static String getRandomStringFromList(List<String> list) {
		return list.get(ThreadLocalRandom.current().nextInt(list.size()));
	}

	/**
	 * Calls Flickr API to generate an Image. Search uses fields of Inspiration
	 * object as tags.
	 * 
	 * @param tags
	 * @return
	 */
	public static Image retrievePhoto(String[] tags) {
		PhotoList<Photo> results = getResultsList(tags);

		Photo result = null;
		Image image = new Image();

		while (!resultIsValid(result)) {
			result = results.get(ThreadLocalRandom.current().nextInt(10));
		}

		image.setTitle(result.getTitle());
		image.setPageUrl(result.getUrl());
		image.setImageUrl(result.getLargeUrl());

		return image;
	}

	private static PhotoList<Photo> getResultsList(String[] tags) {
		InspirationFlickr f = new InspirationFlickr(Constants.FLICKR_KEY, Constants.FLICKR_SECRET,
				new REST());

		SearchParameters params = new SearchParameters();
		params.setTags(tags);

		PhotoList<Photo> results = null;

		try {
			results = f.getPhotosInterface().search(params, 100, 1);
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Checks if the given Photo file has
	 * 
	 * @param result
	 * @return
	 */
	private static boolean resultIsValid(Photo result) {
		return (result != null && null != result.getTitle() && !result.getTitle().isEmpty());
	}

}
