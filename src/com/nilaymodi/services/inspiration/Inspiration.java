package com.nilaymodi.services.inspiration;

public class Inspiration {

	private String		subject;
	private String		lensType;
	private String		time;
	private String		light;
	private String		modifier;
	private String		color;
	private String		concept;
	private String		sentence;
	private FlickrImage	image;

	public String buildSearchString() {
		StringBuilder sb = new StringBuilder();

		if (subject != null) {
			sb.append(subject);
			sb.append(" ");
		}

		if (lensType != null) {
			sb.append(lensType);
			sb.append(" ");
		}

		if (time != null) {
			sb.append(time);
			sb.append(" ");
		}

		if (light != null) {
			sb.append(light);
			sb.append(" lighting ");
		}

		if (color != null) {
			sb.append(color);
			sb.append(" ");
		}

		if (concept != null) {
			sb.append(concept);
			sb.append(" ");
		}
		return sb.toString();
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

	public FlickrImage getImage() {
		return image;
	}

	public void setImage(FlickrImage image) {
		this.image = image;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
}
