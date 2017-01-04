package de.sambalmueslie.phonebook.service.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import de.sambalmueslie.phonebook.rest.SearchRequest;
import de.sambalmueslie.phonebook.rest.SearchResult;
import de.sambalmueslie.phonebook.rest.rest.RestSearchService;
import io.dropwizard.hibernate.UnitOfWork;

@Path(RestSearchService.SEARCH_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class SearchService implements RestSearchService {

	@Override
	@POST
	@Timed
	@UnitOfWork
	public SearchResult search(SearchRequest request) {
		// TODO Auto-generated method stub
		return new SearchResult();
	}

}
