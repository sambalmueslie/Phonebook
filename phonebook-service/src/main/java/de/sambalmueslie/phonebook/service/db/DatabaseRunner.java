package de.sambalmueslie.phonebook.service.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

public class DatabaseRunner {

	public static void main(String[] args) throws SQLException, IOException, AclFormatException {
		new DatabaseRunner().start();
	}

	public void start() throws SQLException, IOException, AclFormatException {
		System.out.println("Starting Database");
		final HsqlProperties p = new HsqlProperties();
		p.setProperty("server.database.0", "mem:phonebook");
		p.setProperty("server.dbname.0", "phonebook");
		p.setProperty("server.port", "9001");

		final Server server = new Server();
		server.setProperties(p);
		server.setLogWriter(null); // can use custom writer
		server.setErrWriter(null); // can use custom writer
		server.start();

		final Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:phonebook", "sa", "");
		c.createStatement().executeQuery("CREATE TABLE health (id int);");
		c.createStatement().executeQuery("SELECT * FROM health;");
	}

}
