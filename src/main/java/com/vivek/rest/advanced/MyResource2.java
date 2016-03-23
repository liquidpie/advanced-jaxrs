package com.vivek.rest.advanced;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

// Don't annotate a class with @Singleton, if you're using a param injection to member variables
@Path("{pathParam}/test")
public class MyResource2 {
	
	@PathParam("pathParam")
	private String pathParam;
	
	@QueryParam("queryParam")
	private String queryParam;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String welcome() {
		return "PathParam: " + pathParam + ", QueryParam: " + queryParam;
	}

}
