package miCV.controller.ventanas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import miCV.model.Conocimiento;
import miCV.model.Idioma;
import miCV.model.Nivel;

public class VentanaConocimientoController extends Dialog<Conocimiento> implements Initializable {

	private Conocimiento modelo = new Conocimiento();

	@FXML
	private TextField denomText;

	@FXML
	private ComboBox<Nivel> nivelCombo;

	@FXML
	private BorderPane view;

	public VentanaConocimientoController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaConocimientoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Conocimiento onResultConverter(ButtonType button) {
		if (button.getButtonData() == ButtonData.OK_DONE) {
			Conocimiento conocimiento = new Idioma();
			conocimiento.setDenominacion(modelo.getDenominacion());
			conocimiento.setNivel(modelo.getNivel());
			return conocimiento;
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// init dialog
		ButtonType addButtonType = new ButtonType("Crear", ButtonData.OK_DONE);

		setTitle("Nuevo idioma");
		setHeaderText(null);
		setGraphic(null);
		getDialogPane().setContent(view);
		getDialogPane().getButtonTypes().setAll(addButtonType, ButtonType.CANCEL);

		setResultConverter(this::onResultConverter);

		// load combo
		nivelCombo.getItems().setAll(Nivel.values());

		// bindings
		modelo.denominacionProperty().bind(denomText.textProperty());
		modelo.nivelProperty().bind(nivelCombo.getSelectionModel().selectedItemProperty());

		// disable add button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		addButton.disableProperty().bind(modelo.denominacionProperty().isEmpty().or(modelo.nivelProperty().isNull()));
	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	void onRemoveAction(ActionEvent event) {
		nivelCombo.getSelectionModel().select(null);
	}

}
