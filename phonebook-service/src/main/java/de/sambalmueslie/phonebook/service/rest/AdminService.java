package de.sambalmueslie.phonebook.service.rest;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import de.sambalmueslie.phonebook.rest.PhonebookLevel;
import de.sambalmueslie.phonebook.rest.request.CreateAttributeDefinitionRequest;
import de.sambalmueslie.phonebook.rest.request.CreateEntryRequest;
import de.sambalmueslie.phonebook.rest.request.CreatePhonebookRequest;
import de.sambalmueslie.phonebook.rest.request.CreateValidatorRequest;
import de.sambalmueslie.phonebook.rest.response.CreateResponse;
import de.sambalmueslie.phonebook.rest.rest.RestAdminService;
import de.sambalmueslie.phonebook.service.data.*;
import de.sambalmueslie.phonebook.service.db.DAOProvider;
import de.sambalmueslie.phonebook.service.db.dao.*;
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
	@POST
	@Path(PATH_ATTRIBUTE_DEFINITION_CREATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed
	@UnitOfWork
	public CreateResponse createAttributeDefinition(CreateAttributeDefinitionRequest request) {
		if (request == null) return new CreateResponse();

		final String name = request.getName();
		if (name == null || name.isEmpty()) return new CreateResponse();
		final Long validatorId = request.getValidatorId();

		final PValidator validator = validatorId == null ? null : daoProvider.getValidatorDAO().findById(validatorId);

		final AttributeDefinitionDAO dao = daoProvider.getAttributeDefinitionDAO();
		final long id = dao.create(new PAttributeDefinition(name, validator));
		return id < 0 ? new CreateResponse() : new CreateResponse(id);
	}

	@Override
	@POST
	@Path(PATH_ENTRY_CREATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed
	@UnitOfWork
	public CreateResponse createEntry(CreateEntryRequest request) {
		if (request == null) return new CreateResponse();

		final long phonebookId = request.getPhonebookId();

		final Stream<Entry<String, String>> stream = request.getAttributes().entrySet().stream();
		final AttributeDefinitionDAO defDao = daoProvider.getAttributeDefinitionDAO();
		final List<PAttribute> attributes = stream.map(e -> convert(defDao, e)).filter(e -> e != null).collect(Collectors.toList());

		final AttributeDao attrDao = daoProvider.getAttributeDao();
		attributes.forEach(a -> attrDao.create(a));

		final String name = request.getName();
		if (name == null || name.isEmpty()) return new CreateResponse();

		final PEntry entry = new PEntry(name, attributes);

		final PhonebookDAO dao = daoProvider.getPhonebookDAO();
		final Phonebook phonebook = dao.findById(phonebookId);
		if (phonebook == null) return new CreateResponse();

		final EntryDAO entryDao = daoProvider.getEntryDAO();
		entryDao.create(entry);

		phonebook.addEntry(entry);
		dao.update(phonebook);

		final long id = entry.getId();
		return id < 0 ? new CreateResponse() : new CreateResponse(id);
	}

	@Override
	@POST
	@Path(PATH_PHONEBOOK_CREATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed
	@UnitOfWork
	public CreateResponse createPhonebook(CreatePhonebookRequest request) {
		if (request == null) return new CreateResponse();

		final String name = request.getName();
		final PhonebookLevel level = request.getLevel();
		if (name == null || name.isEmpty()) return null;
		if (level == null) return null;

		final PhonebookDAO dao = daoProvider.getPhonebookDAO();
		final long id = dao.create(new Phonebook(name, level));
		return id < 0 ? new CreateResponse() : new CreateResponse(id);
	}

	@Override
	@POST
	@Path(PATH_VALIDATOR_CREATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed
	@UnitOfWork
	public CreateResponse createValidator(CreateValidatorRequest request) {
		if (request == null) return new CreateResponse();

		final String expression = request.getExpression();
		if (expression == null || expression.isEmpty()) return new CreateResponse();

		final ValidatorDAO dao = daoProvider.getValidatorDAO();
		final long id = dao.create(new PValidator(expression));
		return id < 0 ? new CreateResponse() : new CreateResponse(id);
	}

	private PAttribute convert(AttributeDefinitionDAO defDao, Entry<String, String> entry) {
		final PAttributeDefinition definition = defDao.findByName(entry.getKey());
		if (definition == null) return null;
		return new PAttribute(definition, entry.getValue());
	}

	private final DAOProvider daoProvider;
}
