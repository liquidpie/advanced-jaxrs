package com.vivek.rest.body.writer;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("curdate")
public class CurrentDateResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
	
}
