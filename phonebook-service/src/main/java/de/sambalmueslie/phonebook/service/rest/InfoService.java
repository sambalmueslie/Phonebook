package de.sambalmueslie.phonebook.service.rest;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import de.sambalmueslie.phonebook.rest.PhonebookInfo;
import de.sambalmueslie.phonebook.service.data.Phonebook;
import de.sambalmueslie.phonebook.service.db.DAOProvider;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/phonebook")
@Produces(MediaType.APPLICATION_JSON)
public class InfoService implements RestService {

	/**
	 * Constructor.
	 *
	 * @param daoProvider
	 */
	InfoService(DAOProvider daoProvider) {
		this.daoProvider = daoProvider;
	}

	@GET
	@Path("/getbyid")
	@Timed
	@UnitOfWork
	public PhonebookInfo getPhonebook(@QueryParam("id") long id) {
		final Phonebook phonebook = daoProvider.getPhonebookDAO().findById(id);
		return map(phonebook);
	}

	@GET
	@Path("/getbyname")
	@Timed
	@UnitOfWork
	public PhonebookInfo getPhonebook(@QueryParam("name") String name) {
		final Phonebook phonebook = daoProvider.getPhonebookDAO().findByName(name);
		return map(phonebook);
	}

	@GET
	@Path("/phonebooks")
	@Timed
	@UnitOfWork
	public List<PhonebookInfo> getPhonebooks() {
		final List<Phonebook> phonebooks = daoProvider.getPhonebookDAO().getAll();
		if (phonebooks == null || phonebooks.isEmpty()) return new LinkedList<>();

		return phonebooks.stream().map(this::map).collect(Collectors.toList());
	}

	private PhonebookInfo map(Phonebook phonebook) {
		if (phonebook == null) return null;
		return new PhonebookInfo(phonebook.getId(), phonebook.getName(), phonebook.getLevel());
	}

	private final DAOProvider daoProvider;
}
