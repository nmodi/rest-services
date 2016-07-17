package com.nilaymodi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.nilaymodi.services.inspiration.Inspiration;
import com.nilaymodi.services.inspiration.InspirationFactory;

@Path("/inspiration")
public class Endpoints {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getInspiration() {
		Inspiration inspiration = InspirationFactory.generate();

		Gson gson = new Gson();
		String response = gson.toJson(inspiration, Inspiration.class);

		return response;
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String testConnection() {
		return "Success! Service is online.";
	}
}
