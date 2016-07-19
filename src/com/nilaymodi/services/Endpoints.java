package com.nilaymodi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nilaymodi.services.inspiration.InspirationGeneratorService;

@Path("/")
public class Endpoints {

	@GET
	@Path("/inspiration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInspiration() {
		return Response
				.ok(new InspirationGeneratorService().getJsonResult(), MediaType.APPLICATION_JSON)
				.build();
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public Response testConnection() {
		return Response.ok("Success! Service is running.", MediaType.TEXT_PLAIN).build();
	}
}
