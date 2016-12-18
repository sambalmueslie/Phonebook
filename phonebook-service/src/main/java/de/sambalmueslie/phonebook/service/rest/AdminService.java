package de.sambalmueslie.phonebook.service.rest;

import java.util.Optional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import de.sambalmueslie.phonebook.rest.PhonebookLevel;
import de.sambalmueslie.phonebook.service.data.Phonebook;
import de.sambalmueslie.phonebook.service.db.DAOProvider;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/phonebook/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminService implements RestService {

	/**
	 * Constructor.
	 *
	 * @param daoProvider
	 */
	AdminService(DAOProvider daoProvider) {
		this.daoProvider = daoProvider;
	}

	@GET
	@Path("/create_phonebook")
	@Timed
	@UnitOfWork
	public Optional<Long> createPhonebook(@QueryParam("name") String name, @DefaultValue("GLOBAL") @QueryParam("level") PhonebookLevel level) {
		if (name == null || name.isEmpty()) return Optional.empty();
		if (level == null) return Optional.empty();

		final Phonebook phonebook = new Phonebook(name, level);
		final long id = daoProvider.getPhonebookDAO().create(phonebook);
		return Optional.of(id);
	}

	private final DAOProvider daoProvider;
}
