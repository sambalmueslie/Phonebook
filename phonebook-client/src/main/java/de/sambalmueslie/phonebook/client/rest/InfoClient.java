package de.sambalmueslie.phonebook.client.rest;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import de.sambalmueslie.phonebook.rest.Constants;
import de.sambalmueslie.phonebook.rest.PhonebookInfo;
import de.sambalmueslie.phonebook.rest.rest.RestInfoService;

public class InfoClient extends BaseRestClient implements RestInfoService {

	/**
	 * Constructor.
	 *
	 * @param serverUrl
	 *            {@link #serverUrl}
	 */
	public InfoClient(String serverUrl) {
		super(serverUrl);
	}

	@Override
	public PhonebookInfo getPhonebook(long id) {
		final String request = RestInfoService.PATH_GET_BY_ID + "?" + Constants.PARAM_ID + "=" + id;
		final WebTarget target = getTarget(RestInfoService.INFO_PATH + request);
		return target.request(MediaType.APPLICATION_JSON).get(PhonebookInfo.class);
	}

	@Override
	public PhonebookInfo getPhonebook(String name) {
		final String request = RestInfoService.PATH_GET_BY_NAME + "?" + Constants.PARAM_NAME + "=" + name;
		final WebTarget target = getTarget(RestInfoService.INFO_PATH + request);
		return target.request(MediaType.APPLICATION_JSON).get(PhonebookInfo.class);
	}

	@Override
	public List<PhonebookInfo> getPhonebooks() {
		final WebTarget target = getTarget(RestInfoService.INFO_PATH + RestInfoService.PATH_GET_ALL);
		final GenericType<List<PhonebookInfo>> type = new GenericType<List<PhonebookInfo>>() {
		};
		return target.request(MediaType.APPLICATION_JSON).get(type);
	}

}
