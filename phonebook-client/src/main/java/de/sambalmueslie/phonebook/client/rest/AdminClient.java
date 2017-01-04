package de.sambalmueslie.phonebook.client.rest;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import de.sambalmueslie.phonebook.rest.Constants;
import de.sambalmueslie.phonebook.rest.PhonebookLevel;
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
	public Long createPhonebook(String name, PhonebookLevel level) {

		final String request = RestAdminService.PATH_PHONEBOOK_CREATE + "?" + Constants.PARAM_NAME + "=" + name;
		final WebTarget target = getTarget(RestAdminService.ADMIN_PATH + request);
		return target.request(MediaType.APPLICATION_JSON).get(Long.class);
	}

}
