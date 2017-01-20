package de.sambalmueslie.phonebook.rest.rest;

import de.sambalmueslie.phonebook.rest.Constants;
import de.sambalmueslie.phonebook.rest.request.CreateAttributeDefinitionRequest;
import de.sambalmueslie.phonebook.rest.request.CreateEntryRequest;
import de.sambalmueslie.phonebook.rest.request.CreatePhonebookRequest;
import de.sambalmueslie.phonebook.rest.request.CreateValidatorRequest;
import de.sambalmueslie.phonebook.rest.response.CreateResponse;

public interface RestAdminService {
	String ADMIN_PATH = Constants.SEPARATOR + "phonebook" + Constants.SEPARATOR + "admin";
	String PATH_ATTRIBUTE_DEFINITION_CREATE = Constants.SEPARATOR + "create_attribute_definition";
	String PATH_ENTRY_CREATE = Constants.SEPARATOR + "create_entry";
	String PATH_PHONEBOOK_CREATE = Constants.SEPARATOR + "create_phonebook";
	String PATH_VALIDATOR_CREATE = Constants.SEPARATOR + "create_validator";

	CreateResponse createAttributeDefinition(CreateAttributeDefinitionRequest request);

	CreateResponse createEntry(CreateEntryRequest request);

	CreateResponse createPhonebook(CreatePhonebookRequest request);

	CreateResponse createValidator(CreateValidatorRequest request);

}
