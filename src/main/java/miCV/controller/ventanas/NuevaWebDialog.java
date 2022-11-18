package miCV.controller.ventanas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import miCV.model.Web;

public class NuevaWebDialog extends Dialog<Web> implements Initializable {

	// model

	private StringProperty web = new SimpleStringProperty();

	// view

	@FXML
	private TextField webText;

	@FXML
	private GridPane view;

	public NuevaWebDialog() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WebDialogoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Web onResultConverter(ButtonType button) {
		if (button.getButtonData() == ButtonData.OK_DONE) {
			Web url = new Web();
			url.setUrl(web.get());
			return url;
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// init dialog
		ButtonType addButtonType = new ButtonType("Añadir", ButtonData.OK_DONE);

		setTitle("Nueva web");
		setHeaderText("Crear una nueva dirección web.");
		getDialogPane().setContent(view);
		getDialogPane().getButtonTypes().setAll(addButtonType, ButtonType.CANCEL);

		setResultConverter(this::onResultConverter);

		// bindings
		web.bind(webText.textProperty());

		// disable add button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		addButton.disableProperty().bind(web.isEmpty());
	}

}
