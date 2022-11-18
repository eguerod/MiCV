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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import miCV.model.Email;

public class NuevoEmailDialog extends Dialog<Email> implements Initializable {

	// model

	private StringProperty correo = new SimpleStringProperty();

	// view

	@FXML
	private TextField mailText;

	@FXML
	private GridPane view;

	public NuevoEmailDialog() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EmailDialogoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Email onResultConverter(ButtonType button) {
		if (button.getButtonData() == ButtonData.OK_DONE) {
			Email email = new Email();
			email.setDireccion(correo.get());
			return email;
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// init dialog
		ButtonType addButtonType = new ButtonType("Añadir", ButtonData.OK_DONE);

		setTitle("Nuevo e-mail");
		setHeaderText("Introduzca una nueva dirección de correo.");
		getDialogPane().setContent(view);
		getDialogPane().getButtonTypes().setAll(addButtonType, ButtonType.CANCEL);

		setResultConverter(this::onResultConverter);
		
		// bindings
		correo.bind(mailText.textProperty());

		// disable add button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		addButton.disableProperty().bind(correo.isEmpty());

	}

}
