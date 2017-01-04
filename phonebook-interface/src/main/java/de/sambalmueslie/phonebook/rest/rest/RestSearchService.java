package de.sambalmueslie.phonebook.rest.rest;

import de.sambalmueslie.phonebook.rest.Constants;
import de.sambalmueslie.phonebook.rest.SearchRequest;
import de.sambalmueslie.phonebook.rest.SearchResult;

public interface RestSearchService {
	String SEARCH_PATH = Constants.SEPARATOR + "phonebook" + Constants.SEPARATOR + "search";

	SearchResult search(SearchRequest request);
}
