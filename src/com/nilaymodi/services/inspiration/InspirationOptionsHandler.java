package com.nilaymodi.services.inspiration;

import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class InspirationOptionsHandler {

	private static InspirationOptionsHandler	instance;
	private InspirationOptions					options;
	final static Logger							logger	= Logger
			.getLogger(InspirationOptionsHandler.class);

	private InspirationOptionsHandler() {
		loadOptions();
	}

	public static InspirationOptionsHandler get() {
		if (instance == null) {
			instance = new InspirationOptionsHandler();
		}

		return instance;
	}

	public void loadOptions() {
		Gson gson = new Gson();

		JsonReader reader = new JsonReader(new InputStreamReader(
				getClass().getResourceAsStream(InspirationConstants.OPTIONS_FILE_NAME)));

		options = gson.fromJson(reader, InspirationOptions.class);

		logger.info("Inspiration options loaded.");
	}

	public InspirationOptions getOptions() {
		return options;
	}
}
