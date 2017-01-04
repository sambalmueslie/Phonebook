package de.sambalmueslie.phonebook.client;

import java.util.*;

import de.sambalmueslie.phonebook.client.rest.RestService;
import de.sambalmueslie.phonebook.rest.PhonebookInfo;
import de.sambalmueslie.phonebook.rest.PhonebookLevel;
import de.sambalmueslie.phonebook.rest.rest.RestAdminService;
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

	private void createNewPhonebook() {
		Platform.runLater(() -> {

			final TextInputDialog dialog = new TextInputDialog("Demo");
			dialog.setTitle("Create Phonebook");
			dialog.setHeaderText("Phonebook Creation");
			dialog.setContentText("Name:");

			final Optional<String> result = dialog.showAndWait();
			if (!result.isPresent()) return;
			final String name = result.get();
			final RestAdminService adminService = restService.getAdminService();

			final Long phonebookId = adminService.createPhonebook(name, PhonebookLevel.GLOBAL);
			if (phonebookId == null) return;
			final PhonebookInfo info = restService.getInfoService().getPhonebook(phonebookId);
			update(info);
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
		final Button addBtn = new Button("Create Phonebook");
		addBtn.setOnAction(e -> createNewPhonebook());
		adminPanel.getChildren().add(addBtn);
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
