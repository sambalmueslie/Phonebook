package de.sambalmueslie.phonebook.rest.rest;

import java.util.List;

import de.sambalmueslie.phonebook.rest.Constants;
import de.sambalmueslie.phonebook.rest.PhonebookInfo;

public interface RestInfoService {

	String INFO_PATH = Constants.SEPARATOR + "phonebook";
	String PATH_GET_ALL = Constants.SEPARATOR + "phonebooks";
	String PATH_GET_BY_ID = Constants.SEPARATOR + "getbyid";
	String PATH_GET_BY_NAME = Constants.SEPARATOR + "getbyname";

	PhonebookInfo getPhonebook(long id);

	PhonebookInfo getPhonebook(String name);

	List<PhonebookInfo> getPhonebooks();
}
