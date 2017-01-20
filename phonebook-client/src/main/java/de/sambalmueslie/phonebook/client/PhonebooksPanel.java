package de.sambalmueslie.phonebook.client;

import java.util.*;

import de.sambalmueslie.phonebook.client.rest.RestService;
import de.sambalmueslie.phonebook.rest.PhonebookInfo;
import de.sambalmueslie.phonebook.rest.PhonebookLevel;
import de.sambalmueslie.phonebook.rest.request.CreateAttributeDefinitionRequest;
import de.sambalmueslie.phonebook.rest.request.CreateEntryRequest;
import de.sambalmueslie.phonebook.rest.request.CreatePhonebookRequest;
import de.sambalmueslie.phonebook.rest.request.CreateValidatorRequest;
import de.sambalmueslie.phonebook.rest.response.CreateResponse;
import de.sambalmueslie.phonebook.rest.rest.RestAdminService;
import de.sambalmueslie.phonebook.rest.rest.RestInfoService;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PhonebooksPanel extends BorderPane {

	public PhonebooksPanel(String serverUrl) {
		restService = new RestService(serverUrl);

		setupUI();
		setupData();
	}

	private void createAttribute() {
		Platform.runLater(() -> {
			final TextInputDialog dialog = new TextInputDialog("Number");
			dialog.setTitle("Create Attribute");
			dialog.setHeaderText("Attribute Creation");
			dialog.setContentText("Attribute:");

			final Optional<String> result = dialog.showAndWait();
			if (!result.isPresent()) return;
			final String name = result.get();

			final RestAdminService adminService = restService.getAdminService();

			adminService.createAttributeDefinition(new CreateAttributeDefinitionRequest(name));
		});
	}

	private void createPhonebook() {
		Platform.runLater(() -> {

			final TextInputDialog dialog = new TextInputDialog("Demo");
			dialog.setTitle("Create Phonebook");
			dialog.setHeaderText("Phonebook Creation");
			dialog.setContentText("Name:");

			final Optional<String> result = dialog.showAndWait();
			if (!result.isPresent()) return;
			final String name = result.get();
			final RestAdminService adminService = restService.getAdminService();

			final CreateResponse response = adminService.createPhonebook(new CreatePhonebookRequest(name));
			if (response == null || response.getId() == null) return;

			final RestInfoService infoService = restService.getInfoService();
			final PhonebookInfo info = infoService.getPhonebook(response.getId());
			update(info);
		});
	}

	private void createSample() {
		Platform.runLater(() -> {
			final RestAdminService adminService = restService.getAdminService();
			// create phonebook
			CreateResponse response = adminService.createPhonebook(new CreatePhonebookRequest("Sample Demo", PhonebookLevel.GLOBAL));
			final Long phonebookId = response.getId();

			// create some attributes
			response = adminService.createValidator(new CreateValidatorRequest("[\\w]+"));
			final Long nameValidator = response.getId();

			response = adminService.createValidator(new CreateValidatorRequest("[+\\d]+"));
			final Long numberValidator = response.getId();

			adminService.createAttributeDefinition(new CreateAttributeDefinitionRequest("Name", nameValidator));
			adminService.createAttributeDefinition(new CreateAttributeDefinitionRequest("Number", numberValidator));

			// create some sample entries
			final CreateEntryRequest request = new CreateEntryRequest(phonebookId, "Max Mustermann");
			request.set("Name", "Max Mustermann");
			request.set("Number", "+496363774425");
			adminService.createEntry(request);

			final RestInfoService infoService = restService.getInfoService();
			final PhonebookInfo info = infoService.getPhonebook(phonebookId);
			update(info);
		});
	}

	private void createValidator() {
		Platform.runLater(() -> {
			final TextInputDialog dialog = new TextInputDialog(".*");
			dialog.setTitle("Create Validator");
			dialog.setHeaderText("Validator Creation");
			dialog.setContentText("Regular Expression:");

			final Optional<String> result = dialog.showAndWait();
			if (!result.isPresent()) return;
			final String expression = result.get();

			final RestAdminService adminService = restService.getAdminService();

			adminService.createValidator(new CreateValidatorRequest(expression));
		});
	}

	private void setupData() {
		Platform.runLater(() -> {
			final Set<Long> tabsToRemove = new HashSet<>(tabs.keySet());
			final List<PhonebookInfo> infos = restService.getInfoService().getPhonebooks();
			for (final PhonebookInfo info : infos) {
				final long id = info.getId();
				tabsToRemove.remove(id);
				update(info);
			}

			tabsToRemove.stream().map(id -> tabs.get(id)).forEach(t -> phonebooks.getTabs().remove(t));
		});
	}

	private void setupUI() {
		final HBox adminPanel = new HBox(10);
		adminPanel.setPadding(new Insets(5));
		final Button addPhonebookBtn = new Button("Create Phonebook");
		addPhonebookBtn.setOnAction(e -> createPhonebook());

		final Button addValidatorBtn = new Button("Create Validator");
		addValidatorBtn.setOnAction(e -> createValidator());

		final Button addAttributeBtn = new Button("Create Attribute");
		addAttributeBtn.setOnAction(e -> createAttribute());

		final Button createSampleBtn = new Button("Create Sample");
		createSampleBtn.setOnAction(e -> createSample());

		adminPanel.getChildren().addAll(addPhonebookBtn, addValidatorBtn, addAttributeBtn, createSampleBtn);
		setTop(adminPanel);

		phonebooks = new TabPane();
		setCenter(phonebooks);
	}

	private void update(final PhonebookInfo info) {
		if (info == null) return;
		final long id = info.getId();
		PhonebookPanel panel = tabs.get(id);
		if (panel == null) {
			panel = new PhonebookPanel(info);
			phonebooks.getTabs().add(panel);
		} else {
			panel.update(info);
		}
	}

	private TabPane phonebooks;
	private final RestService restService;
	private final Map<Long, PhonebookPanel> tabs = new HashMap<>();
}
