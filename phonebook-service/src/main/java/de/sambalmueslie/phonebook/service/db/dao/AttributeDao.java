package de.sambalmueslie.phonebook.service.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import de.sambalmueslie.phonebook.service.data.PAttribute;
import io.dropwizard.hibernate.AbstractDAO;

public class AttributeDao extends AbstractDAO<PAttribute> {
	public AttributeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public long create(PAttribute attribute) {
		return persist(attribute).getId();
	}

	public PAttribute findById(Long id) {
		return get(id);
	}

	public List<PAttribute> getAll() {
		return list(criteria());
	}
}
