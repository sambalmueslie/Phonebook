package de.sambalmueslie.phonebook.service.db;

import org.hibernate.SessionFactory;

import de.sambalmueslie.phonebook.service.db.dao.AttributeDefinitionDAO;
import de.sambalmueslie.phonebook.service.db.dao.PhonebookDAO;
import de.sambalmueslie.phonebook.service.db.dao.ValidatorDAO;

public class DAOProvider {

	DAOProvider(SessionFactory sessionFactory) {
		phonebookDAO = new PhonebookDAO(sessionFactory);
		attributeDefinitionDAO = new AttributeDefinitionDAO(sessionFactory);
		validatorDAO = new ValidatorDAO(sessionFactory);
	}

	public AttributeDefinitionDAO getAttributeDefinitionDAO() {
		return attributeDefinitionDAO;
	}

	public PhonebookDAO getPhonebookDAO() {
		return phonebookDAO;
	}

	public ValidatorDAO getValidatorDAO() {
		return validatorDAO;
	}

	private final AttributeDefinitionDAO attributeDefinitionDAO;
	private final PhonebookDAO phonebookDAO;

	private final ValidatorDAO validatorDAO;
}
