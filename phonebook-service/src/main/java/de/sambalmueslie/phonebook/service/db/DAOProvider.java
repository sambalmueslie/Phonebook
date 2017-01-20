package de.sambalmueslie.phonebook.service.db;

import org.hibernate.SessionFactory;

import de.sambalmueslie.phonebook.service.db.dao.*;

public class DAOProvider {

	DAOProvider(SessionFactory sessionFactory) {
		phonebookDAO = new PhonebookDAO(sessionFactory);
		attributeDefinitionDAO = new AttributeDefinitionDAO(sessionFactory);
		validatorDAO = new ValidatorDAO(sessionFactory);
		entryDAO = new EntryDAO(sessionFactory);
		attributeDao = new AttributeDao(sessionFactory);
	}

	public AttributeDao getAttributeDao() {
		return attributeDao;
	}

	public AttributeDefinitionDAO getAttributeDefinitionDAO() {
		return attributeDefinitionDAO;
	}

	public EntryDAO getEntryDAO() {
		return entryDAO;
	}

	public PhonebookDAO getPhonebookDAO() {
		return phonebookDAO;
	}

	public ValidatorDAO getValidatorDAO() {
		return validatorDAO;
	}

	private final AttributeDao attributeDao;
	private final AttributeDefinitionDAO attributeDefinitionDAO;
	private final EntryDAO entryDAO;
	private final PhonebookDAO phonebookDAO;
	private final ValidatorDAO validatorDAO;
}
