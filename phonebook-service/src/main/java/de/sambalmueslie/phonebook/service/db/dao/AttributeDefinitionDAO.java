package de.sambalmueslie.phonebook.service.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import de.sambalmueslie.phonebook.service.data.PAttributeDefinition;
import io.dropwizard.hibernate.AbstractDAO;

public class AttributeDefinitionDAO extends AbstractDAO<PAttributeDefinition> {
	public AttributeDefinitionDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public long create(PAttributeDefinition definition) {
		return persist(definition).getId();
	}

	public PAttributeDefinition findById(Long id) {
		return get(id);
	}

	public PAttributeDefinition findByName(String name) {
		return uniqueResult(criteria().add(Restrictions.eq("name", name)));
	}

	public List<PAttributeDefinition> getAll() {
		return list(criteria());
	}
}
