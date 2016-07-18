package com.nilaymodi.services.inspiration;

import java.util.ArrayList;

public class Inspiration {

	private String	subject;
	private String	lensType;
	private String	time;
	private String	light;
	private String	modifier;
	private String	color;
	private String	concept;
	private String	sentence;
	private Image	photo;

	public String[] buildSearchTags() {
		ArrayList<String> tags = new ArrayList<String>();

		if (subject != null) {
			tags.add(subject);
		}

		if (lensType != null) {
			tags.add(lensType);
		}

		if (time != null) {
			tags.add(time);
		}

		if (light != null) {
			tags.add(light + " lighting");
		}

		if (color != null) {
			tags.add(color);
		}

		if (concept != null) {
			tags.add(concept);
		}

		String[] ret = new String[tags.size()];
		ret = tags.toArray(ret);

		return ret;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLensType() {
		return lensType;
	}

	public void setLensType(String lensType) {
		this.lensType = lensType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}
}
