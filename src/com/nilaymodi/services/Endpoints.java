package com.nilaymodi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.nilaymodi.services.inspiration.Inspiration;
import com.nilaymodi.services.inspiration.InspirationFactory;

@Path("/inspiration")
public class Endpoints {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInspiration() {
		Inspiration inspiration = InspirationFactory.generate();

		Gson gson = new Gson();
		String result = gson.toJson(inspiration, Inspiration.class);

		return Response.ok(result, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public Response testConnection() {
		return Response.ok("Sucess! Service is running.", MediaType.TEXT_PLAIN).build();
	}
}
