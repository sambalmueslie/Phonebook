package de.sambalmueslie.phonebook.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DemoApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final String serverUrl = "http://localhost:8080";
		final PhonebooksPanel phonebook = new PhonebooksPanel(serverUrl);
		primaryStage.setScene(new Scene(phonebook, 800, 600));
		primaryStage.show();
	}

}
