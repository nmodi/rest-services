package com.nilaymodi.services.inspiration;

import com.google.gson.Gson;
import com.nilaymodi.services.inspiration.objects.Inspiration;

public class InspirationGeneratorService {

	public String getJsonResult() {
		return new Gson().toJson(InspirationFactory.generate(), Inspiration.class);
	}

}
