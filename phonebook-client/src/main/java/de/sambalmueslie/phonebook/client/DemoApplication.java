package de.sambalmueslie.phonebook.client;

import java.util.Optional;

import de.sambalmueslie.phonebook.client.rest.AdminClient;
import de.sambalmueslie.phonebook.client.rest.InfoClient;
import de.sambalmueslie.phonebook.rest.PhonebookLevel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DemoApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public DemoApplication() {
		final String serverUrl = "http://localhost:8080";
		infoClient = new InfoClient(serverUrl);
		adminClient = new AdminClient(serverUrl);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello World!");
		final Button btn = new Button();
		btn.setText("Create Phonebook");
		btn.setOnAction(e -> createNewPhonebook());

		final StackPane root = new StackPane();
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	private void createNewPhonebook() {
		Platform.runLater(() -> {

			final TextInputDialog dialog = new TextInputDialog("Demo");
			dialog.setTitle("Create Phonebook");
			dialog.setHeaderText("Phonebook Creation");
			dialog.setContentText("Name:");

			final Optional<String> result = dialog.showAndWait();
			if (!result.isPresent()) return;
			final String name = result.get();
			System.out.println("Start creatin of: " + name);
			final Long phonebookId = adminClient.createPhonebook(name, PhonebookLevel.GLOBAL);
			if (phonebookId == null) return;
			System.out.println("Done: " + name + "=" + phonebookId);
		});
	}

	/** the {@link AdminClient} */
	private final AdminClient adminClient;
	/** the {@link InfoClient}. */
	private final InfoClient infoClient;

}
