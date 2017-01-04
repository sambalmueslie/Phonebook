package de.sambalmueslie.phonebook.client;

import java.util.*;

import de.sambalmueslie.phonebook.client.rest.RestService;
import de.sambalmueslie.phonebook.rest.PhonebookInfo;
import de.sambalmueslie.phonebook.rest.PhonebookLevel;
import de.sambalmueslie.phonebook.rest.rest.RestAdminService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PhonebookPanel extends BorderPane {

	public PhonebookPanel(String serverUrl) {
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

	private Tab createTab(final PhonebookInfo info) {
		final Tab tab = new Tab(info.getName());

		final ObservableList<String> entires = FXCollections.observableArrayList();
		final TableView<String> entryTable = new TableView<>(entires);

		final ScrollPane scrollPane = new ScrollPane(entryTable);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		tab.setContent(scrollPane);

		tab.setClosable(false);
		return tab;
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
		Tab tab = tabs.get(id);
		if (tab == null) {
			tab = createTab(info);
			phonebooks.getTabs().add(tab);
		} else {
			tab.setText(info.getName());
		}
	}

	private TabPane phonebooks;
	private final RestService restService;
	private final Map<Long, Tab> tabs = new HashMap<>();
}
