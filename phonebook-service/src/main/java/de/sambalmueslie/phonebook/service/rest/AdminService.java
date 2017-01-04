package de.sambalmueslie.phonebook.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import de.sambalmueslie.phonebook.rest.PhonebookLevel;
import de.sambalmueslie.phonebook.rest.request.CreateAttributeDefinitionRequest;
import de.sambalmueslie.phonebook.rest.request.CreatePhonebookRequest;
import de.sambalmueslie.phonebook.rest.request.CreateValidatorRequest;
import de.sambalmueslie.phonebook.rest.response.CreateResponse;
import de.sambalmueslie.phonebook.rest.rest.RestAdminService;
import de.sambalmueslie.phonebook.service.data.PAttributeDefinition;
import de.sambalmueslie.phonebook.service.data.PValidator;
import de.sambalmueslie.phonebook.service.data.Phonebook;
import de.sambalmueslie.phonebook.service.db.DAOProvider;
import de.sambalmueslie.phonebook.service.db.dao.AttributeDefinitionDAO;
import de.sambalmueslie.phonebook.service.db.dao.PhonebookDAO;
import de.sambalmueslie.phonebook.service.db.dao.ValidatorDAO;
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
		if (name == null || name.isEmpty()) return null;
		final Long validatorId = request.getValidatorId();

		final PValidator validator = validatorId == null ? null : daoProvider.getValidatorDAO().findById(validatorId);

		final AttributeDefinitionDAO dao = daoProvider.getAttributeDefinitionDAO();
		final long id = dao.create(new PAttributeDefinition(name, validator));
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

	private final DAOProvider daoProvider;
}
