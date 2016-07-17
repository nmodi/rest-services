package com.nilaymodi.services.inspiration;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InspirationFactory {

	public static Inspiration generate() {
		InspirationOptions options = InspirationOptionsHandler.get().getOptions();
		Inspiration inspiration = new Inspiration();

		setSubjectOrConcept(options, inspiration);
		inspiration.setLensType(chooseLensType(options));
		inspiration.setTime(chooseTime(options));
		inspiration.setLight(chooseLight(options));
		inspiration.setModifier(chooseModifier(options));
		inspiration.setColor(chooseColor(options));

		System.out.println(inspiration.buildSearchString());

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

	private static String chooseLensType(InspirationOptions options) {
		String ret = null;
		if (ThreadLocalRandom.current().nextDouble() > InspirationConstants.SKIP_RATE_LOW) {
			ret = getRandomStringFromList(options.getLensTypes());
		}
		return ret;
	}

	private static String chooseTime(InspirationOptions options) {
		String ret = null;
		if (ThreadLocalRandom.current().nextDouble() > InspirationConstants.SKIP_RATE_DEFAULT) {
			ret = getRandomStringFromList(options.getTimes());
		}
		return ret;
	}

	private static String chooseLight(InspirationOptions options) {
		String ret = null;
		if (ThreadLocalRandom.current().nextDouble() > InspirationConstants.SKIP_RATE_MEDIUM) {
			ret = getRandomStringFromList(options.getLights());
		}
		return ret;
	}

	private static String chooseModifier(InspirationOptions options) {
		String ret = null;
		if (ThreadLocalRandom.current().nextDouble() > InspirationConstants.SKIP_RATE_HIGH) {
			ret = getRandomStringFromList(options.getModifiers());
		}
		return ret;
	}

	private static String chooseColor(InspirationOptions options) {
		String ret = null;
		if (ThreadLocalRandom.current().nextDouble() > InspirationConstants.SKIP_RATE_HIGH) {
			ret = getRandomStringFromList(options.getColors());
		}
		return ret;
	}

	private static String getRandomStringFromList(List<String> list) {
		return list.get(ThreadLocalRandom.current().nextInt(list.size()));
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
