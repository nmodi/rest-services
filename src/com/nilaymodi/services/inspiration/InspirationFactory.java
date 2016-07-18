package com.nilaymodi.services.inspiration;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

public class InspirationFactory {

	public static Inspiration generate() {
		InspirationOptions options = InspirationOptionsHandler.get().getOptions();
		Inspiration inspiration = new Inspiration();

		setSubjectOrConcept(options, inspiration);

		inspiration.setLensType(
				chooseOption(options.getLensTypes(), InspirationConstants.SKIP_RATE_LOW));
		inspiration
				.setTime(chooseOption(options.getTimes(), InspirationConstants.SKIP_RATE_DEFAULT));
		inspiration
				.setLight(chooseOption(options.getLights(), InspirationConstants.SKIP_RATE_MEDIUM));
		inspiration.setModifier(
				chooseOption(options.getModifiers(), InspirationConstants.SKIP_RATE_HIGH));
		inspiration
				.setColor(chooseOption(options.getColors(), InspirationConstants.SKIP_RATE_HIGH));

		inspiration.setPhoto(retrievePhoto(inspiration.buildSearchTags()));
		inspiration.setSentence(buildSentence(inspiration));

		return inspiration;
	}

	private static void setSubjectOrConcept(InspirationOptions options, Inspiration inspiration) {

		double d = ThreadLocalRandom.current().nextDouble();

		if (d < 0.50) {
			inspiration.setSubject(getRandomStringFromList(options.getSubjects()));
		} else if (d < 0.75) {
			inspiration.setConcept(getRandomStringFromList(options.getConcepts()));
		} else {
			// Do nothing
		}
	}

	private static String chooseOption(List<String> choices, double skipRate) {
		String ret = null;
		if (ThreadLocalRandom.current().nextDouble() > skipRate) {
			ret = getRandomStringFromList(choices);
		}
		return ret;
	}

	private static String getRandomStringFromList(List<String> list) {
		return list.get(ThreadLocalRandom.current().nextInt(list.size()));
	}

	private static Image retrievePhoto(String[] tags) {

		Flickr f = new Flickr(InspirationConstants.FLICKR_KEY, InspirationConstants.FLICKR_SECRET,
				new REST());

		SearchParameters params = new SearchParameters();
		params.setTags(tags);

		PhotoList<Photo> results = null;
		Photo topResult = null;

		try {
			results = f.getPhotosInterface().search(params, 100, 1);
			topResult = results.get(0);
		} catch (FlickrException e) {
			e.printStackTrace();
		}

		Image image = new Image();
		image.setTitle(topResult.getTitle());
		image.setPageUrl(topResult.getUrl());
		image.setImageUrl(topResult.getLargeUrl());

		return image;
	}

	private static String buildSentence(Inspiration inspiration) {
		StringBuilder sb = new StringBuilder();

		sb.append("Take a picture ");

		if (inspiration.getSubject() != null || inspiration.getConcept() != null) {
			sb.append("of");
			sb.append(getSubjectConceptArticle(inspiration));
			sb.append(" ");

			if (inspiration.getSubject() != null) {
				sb.append(inspiration.getSubject());
			} else {
				sb.append(inspiration.getConcept());
			}
			sb.append(" ");
		}

		if (inspiration.getLight() != null) {
			sb.append("using ");
			sb.append(inspiration.getLight());
			sb.append(" lighting ");
		}

		if (inspiration.getTime() != null) {
			sb.append("at ");
			sb.append(inspiration.getTime());
			sb.append(" ");
		}

		if (inspiration.getModifier() != null) {
			sb.append("with");
			sb.append(getArticle(inspiration.getModifier()));
			sb.append(" ");
			sb.append(inspiration.getModifier());
			sb.append(" ");
		}

		if (inspiration.getColor() != null) {
			sb.append("evoking the color ");
			sb.append(inspiration.getColor());
		}

		if (sb.charAt(sb.length() - 1) == ' ') {
			sb.setCharAt(sb.length() - 1, '.');
		} else {
			sb.append('.');
		}

		return sb.toString();
	}

	private static String getSubjectConceptArticle(Inspiration inspiration) {
		if (inspiration.getSubject() == null && inspiration.getConcept() == null) {
			return "";
		}

		return getArticle(inspiration.getSubject());
	}

	private static String getArticle(String word) {
		if (word != null) {
			if (checkForVowel(word)) {
				return " an";
			} else {
				return " a";
			}
		}

		return "";
	}

	private static boolean checkForVowel(String word) {
		for (char vowel : InspirationConstants.VOWELS) {
			if (Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(vowel)) {
				return true;
			}
		}

		return false;
	}
}
