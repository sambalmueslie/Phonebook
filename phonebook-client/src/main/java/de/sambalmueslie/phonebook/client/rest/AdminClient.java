package de.sambalmueslie.phonebook.client.rest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import de.sambalmueslie.phonebook.rest.request.CreateAttributeDefinitionRequest;
import de.sambalmueslie.phonebook.rest.request.CreateEntryRequest;
import de.sambalmueslie.phonebook.rest.request.CreatePhonebookRequest;
import de.sambalmueslie.phonebook.rest.request.CreateValidatorRequest;
import de.sambalmueslie.phonebook.rest.response.CreateResponse;
import de.sambalmueslie.phonebook.rest.rest.RestAdminService;

class AdminClient extends BaseRestClient implements RestAdminService {
	/**
	 * Constructor.
	 *
	 * @param connector
	 *            the {@link RestConnector}
	 */
	AdminClient(RestConnector connector) {
		super(connector);
	}

	@Override
	public CreateResponse createAttributeDefinition(CreateAttributeDefinitionRequest request) {
		return create(request, RestAdminService.PATH_ATTRIBUTE_DEFINITION_CREATE);
	}

	@Override
	public CreateResponse createEntry(CreateEntryRequest request) {
		return create(request, RestAdminService.PATH_ENTRY_CREATE);
	}

	@Override
	public CreateResponse createPhonebook(CreatePhonebookRequest request) {
		return create(request, RestAdminService.PATH_PHONEBOOK_CREATE);
	}

	@Override
	public CreateResponse createValidator(CreateValidatorRequest request) {
		return create(request, RestAdminService.PATH_VALIDATOR_CREATE);
	}

	private CreateResponse create(Object request, String path) {
		final WebTarget target = getTarget(RestAdminService.ADMIN_PATH + path);

		final Builder builder = target.request(MediaType.APPLICATION_JSON);
		return builder.post(Entity.entity(request, MediaType.APPLICATION_JSON), CreateResponse.class);
	}
}
