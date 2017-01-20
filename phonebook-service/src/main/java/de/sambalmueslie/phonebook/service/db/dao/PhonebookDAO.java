package de.sambalmueslie.phonebook.service.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import de.sambalmueslie.phonebook.service.data.Phonebook;
import io.dropwizard.hibernate.AbstractDAO;

public class PhonebookDAO extends AbstractDAO<Phonebook> {

	public PhonebookDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public long create(Phonebook phonebook) {
		return persist(phonebook).getId();
	}

	public Phonebook findById(Long id) {
		return get(id);
	}

	public Phonebook findByName(String name) {
		return uniqueResult(criteria().add(Restrictions.eq("name", name)));
	}

	public List<Phonebook> getAll() {
		return list(criteria());
	}

	public void update(Phonebook phonebook) {
		persist(phonebook);
	}
}
