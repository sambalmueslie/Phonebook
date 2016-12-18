package de.sambalmueslie.phonebook.service.rest;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/phonebook/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminService implements RestService {

}
