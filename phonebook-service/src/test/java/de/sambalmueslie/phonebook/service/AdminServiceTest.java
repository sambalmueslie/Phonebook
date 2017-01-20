package de.sambalmueslie.phonebook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import de.sambalmueslie.phonebook.rest.Constants;
import de.sambalmueslie.phonebook.rest.PhonebookInfo;
import de.sambalmueslie.phonebook.rest.request.CreatePhonebookRequest;
import de.sambalmueslie.phonebook.rest.response.CreateResponse;
import de.sambalmueslie.phonebook.rest.rest.RestAdminService;
import de.sambalmueslie.phonebook.rest.rest.RestInfoService;
import de.sambalmueslie.phonebook.service.config_mgt.PhonebookConfiguration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;

public class AdminServiceTest {

	@ClassRule
	public static final DropwizardAppRule<PhonebookConfiguration> RULE = new DropwizardAppRule<>(PhonebookApplication.class, "config.yml");

	@Test
	public void createPhonebook() {
		final Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");

		final String createPath = RestAdminService.ADMIN_PATH + RestAdminService.PATH_PHONEBOOK_CREATE;
		final CreatePhonebookRequest createRequest = new CreatePhonebookRequest("Test");
		final CreateResponse createResponse = post(client, createPath, createRequest, CreateResponse.class);

		assertNotNull(createResponse);
		final long phonebookId = createResponse.getId();
		assertTrue(phonebookId > 0);

		final String infoPath = RestInfoService.INFO_PATH + RestInfoService.PATH_GET_BY_ID + "?" + Constants.PARAM_ID + "=" + phonebookId;
		final PhonebookInfo infoResponse = get(client, infoPath, PhonebookInfo.class);
		assertNotNull(infoResponse);

		assertEquals(phonebookId, infoResponse.getId());
		assertEquals(createRequest.getName(), infoResponse.getName());
		assertEquals(createRequest.getLevel(), infoResponse.getLevel());

	}

	private <T> T get(Client client, String path, Class<T> responseType) {
		final WebTarget target = client.target(String.format("http://localhost:%d/" + path, RULE.getLocalPort()));
		return target.request(MediaType.APPLICATION_JSON).get(responseType);
	}

	private <T, R> T post(Client client, String path, final R request, Class<T> responseType) {
		final WebTarget target = client.target(String.format("http://localhost:%d/" + path, RULE.getLocalPort()));
		return target.request().post(Entity.entity(request, MediaType.APPLICATION_JSON), responseType);
	}
}
