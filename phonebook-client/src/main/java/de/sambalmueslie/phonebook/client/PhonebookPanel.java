package de.sambalmueslie.phonebook.client;

import de.sambalmueslie.phonebook.rest.PhonebookInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

public class PhonebookPanel extends Tab {

	public PhonebookPanel(PhonebookInfo info) {
		final ObservableList<String> entires = FXCollections.observableArrayList();
		final TableView<String> entryTable = new TableView<>(entires);

		final ScrollPane scrollPane = new ScrollPane(entryTable);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		setContent(scrollPane);

		setClosable(false);
		update(info);
	}

	public void update(PhonebookInfo info) {
		setText(info.getName());
	}

}
