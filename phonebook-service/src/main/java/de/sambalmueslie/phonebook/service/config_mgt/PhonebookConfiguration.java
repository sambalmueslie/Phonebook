package de.sambalmueslie.phonebook.service.config_mgt;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class PhonebookConfiguration extends Configuration {
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

	@JsonProperty
	public boolean isInternalDb() {
		return internalDb;
	}

	public void setDatabase(DataSourceFactory database) {
		this.database = database;
	}

	@JsonProperty
	public void setInternalDb(boolean internalDb) {
		this.internalDb = internalDb;
	}

	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();

	private boolean internalDb = false;
}
