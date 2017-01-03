package de.sambalmueslie.phonebook.service.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import de.sambalmueslie.phonebook.rest.Constants;
import de.sambalmueslie.phonebook.rest.PhonebookLevel;
import de.sambalmueslie.phonebook.rest.rest.RestAdminService;
import de.sambalmueslie.phonebook.service.data.Phonebook;
import de.sambalmueslie.phonebook.service.db.DAOProvider;
import io.dropwizard.hibernate.UnitOfWork;

@Path(AdminService.ADMIN_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class AdminService implements RestService, RestAdminService {

	/**
	 * Constructor.
	 *
	 * @param daoProvider
	 */
	AdminService(DAOProvider daoProvider) {
		this.daoProvider = daoProvider;
	}

	@Override
	@GET
	@Path(PATH_PHONEBOOK_CREATE)
	@Timed
	@UnitOfWork
	public Long createPhonebook(@QueryParam(Constants.PARAM_NAME) String name,
			@DefaultValue("GLOBAL") @QueryParam(Constants.PARAM_LEVEL) PhonebookLevel level) {
		if (name == null || name.isEmpty()) return null;
		if (level == null) return null;

		final Phonebook phonebook = new Phonebook(name, level);
		final long id = daoProvider.getPhonebookDAO().create(phonebook);
		return id;
	}

	private final DAOProvider daoProvider;
}
