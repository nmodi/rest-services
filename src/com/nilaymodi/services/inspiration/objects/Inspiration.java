package com.nilaymodi.services.inspiration.objects;

public class Inspiration {

	private String	subject;
	private String	lensType;
	private String	time;
	private String	light;
	private String	modifier;
	private String	color;
	private String	pluralSubject;
	private String	sentence;
	private Image	photo;

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

	public String getPluralSubject() {
		return pluralSubject;
	}

	public void setPluralSubject(String pluralSubject) {
		this.pluralSubject = pluralSubject;
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
