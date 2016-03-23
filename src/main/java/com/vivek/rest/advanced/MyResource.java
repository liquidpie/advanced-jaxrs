package com.vivek.rest.advanced;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
@Singleton
public class MyResource {
	
	private int count;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String welcome() {
		count = count + 1;
		return "Welcome to Advanced Jax-RS. This method is called " + count + " time(s)";
	}
}
