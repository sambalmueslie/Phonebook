package de.sambalmueslie.phonebook.service.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import de.sambalmueslie.phonebook.service.data.PEntry;
import io.dropwizard.hibernate.AbstractDAO;

public class EntryDAO extends AbstractDAO<PEntry> {
	public EntryDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public long create(PEntry entry) {
		return persist(entry).getId();
	}

	public PEntry findById(Long id) {
		return get(id);
	}

	public List<PEntry> getAll() {
		return list(criteria());
	}
}
