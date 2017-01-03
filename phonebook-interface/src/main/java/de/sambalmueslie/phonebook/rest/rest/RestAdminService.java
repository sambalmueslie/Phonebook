package de.sambalmueslie.phonebook.rest.rest;

import de.sambalmueslie.phonebook.rest.Constants;
import de.sambalmueslie.phonebook.rest.PhonebookLevel;

public interface RestAdminService {
	String ADMIN_PATH = Constants.SEPARATOR + "phonebook" + Constants.SEPARATOR + "admin";
	String PATH_PHONEBOOK_CREATE = Constants.SEPARATOR + "create_phonebook";

	Long createPhonebook(String name, PhonebookLevel level);
}
