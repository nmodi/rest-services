package com.nilaymodi.services.inspiration;

public class InspirationOptions {

	private InspirationOptions instance;

	private InspirationOptions() {
		init();
	}

	public InspirationOptions getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return new InspirationOptions();
		}
	}

	private void init() {
		// TODO
	}

}
