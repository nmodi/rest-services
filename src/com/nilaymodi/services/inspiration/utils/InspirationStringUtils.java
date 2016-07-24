package com.nilaymodi.services.inspiration.utils;

import java.util.ArrayList;

import com.nilaymodi.services.inspiration.objects.Inspiration;

public class InspirationStringUtils {

	public static String[] buildSearchTags(Inspiration i) {
		ArrayList<String> tags = new ArrayList<String>();

		if (i.getSubject() != null) {
			tags.add(i.getSubject());
		}

		if (i.getLensType() != null) {
			tags.add(i.getLensType());
		}

		if (i.getTime() != null) {
			tags.add(i.getTime());
		}

		if (i.getLight() != null) {
			tags.add(i.getLight() + " lighting");
		}

		if (i.getColor() != null) {
			tags.add(i.getColor());
		}

		if (i.getPluralSubject() != null) {
			tags.add(i.getPluralSubject());
		}

		String[] ret = new String[tags.size()];
		ret = tags.toArray(ret);

		return ret;
	}

	public static String buildSentence(Inspiration inspiration) {
		StringBuilder sb = new StringBuilder();

		sb.append("Take a picture ");

		if (inspiration.getSubject() != null || inspiration.getPluralSubject() != null) {
			sb.append("of");
			sb.append(" ");
			sb.append(getSubjectArticle(inspiration));

			if (inspiration.getSubject() != null) {
				sb.append(inspiration.getSubject());
			} else {
				sb.append(inspiration.getPluralSubject());
			}
			sb.append(" ");
		}

		if (inspiration.getLight() != null) {
			sb.append("under ");
			sb.append(inspiration.getLight());
			sb.append(" lighting ");
		}

		if (inspiration.getTime() != null) {
			sb.append("at ");
			sb.append(inspiration.getTime());
			sb.append(" ");
		}

		if (inspiration.getModifier() != null) {
			sb.append("using");
			sb.append(" ");
			sb.append(getArticle(inspiration.getModifier()));
			sb.append(inspiration.getModifier());
			sb.append(" ");
		}

		if (inspiration.getColor() != null) {
			sb.append("evoking the color ");
			sb.append(inspiration.getColor());
			sb.append(" ");
		}

		if (inspiration.getLensType() != null) {
			sb.append("with");
			sb.append(" ");
			sb.append(getArticle(inspiration.getLensType()));
			sb.append(inspiration.getLensType());
			sb.append(" lens");
		}

		if (sb.charAt(sb.length() - 1) == ' ') {
			sb.setCharAt(sb.length() - 1, '.');
		} else {
			sb.append('.');
		}

		return sb.toString();
	}

	private static String getSubjectArticle(Inspiration inspiration) {
		if (inspiration.getSubject() == null && inspiration.getPluralSubject() == null) {
			return "";
		}

		return getArticle(inspiration.getSubject());
	}

	private static String getArticle(String word) {
		if (word != null) {
			if (checkForVowel(word)) {
				return "an ";
			} else {
				return "a ";
			}
		}

		return "";
	}

	private static boolean checkForVowel(String word) {
		for (char vowel : Constants.VOWELS) {
			if (Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(vowel)) {
				return true;
			}
		}

		return false;
	}
}
