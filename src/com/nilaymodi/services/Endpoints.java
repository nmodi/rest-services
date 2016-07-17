package com.nilaymodi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/inspiration")
public class Endpoints {

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String testConnection() {
		return "Success! Service is online.";
	}

}
