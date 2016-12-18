package de.sambalmueslie.phonebook.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
@Produces(MediaType.TEXT_PLAIN)
public class HelloWorld {

	@GET
	public String hello() {
		return "Hello World";
	}

}
