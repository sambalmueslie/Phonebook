package de.sambalmueslie.phonebook.service.db;

import org.hibernate.SessionFactory;

import de.sambalmueslie.phonebook.service.db.dao.PhonebookDAO;

public class DAOProvider {

	DAOProvider(SessionFactory sessionFactory) {
		phonebookDAO = new PhonebookDAO(sessionFactory);
	}

	public PhonebookDAO getPhonebookDAO() {
		return phonebookDAO;
	}

	private final PhonebookDAO phonebookDAO;
}
