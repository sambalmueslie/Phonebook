package de.sambalmueslie.phonebook.service.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import de.sambalmueslie.phonebook.service.data.PValidator;
import io.dropwizard.hibernate.AbstractDAO;

public class ValidatorDAO extends AbstractDAO<PValidator> {
	public ValidatorDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public long create(PValidator definition) {
		return persist(definition).getId();
	}

	public PValidator findById(Long id) {
		return get(id);
	}

	public List<PValidator> getAll() {
		return list(criteria());
	}
}
