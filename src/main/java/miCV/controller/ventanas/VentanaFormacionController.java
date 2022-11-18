package miCV.controller.ventanas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import miCV.model.Titulo;

public class VentanaFormacionController extends Dialog<Titulo> implements Initializable {

	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty organizador = new SimpleStringProperty();
	private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<>();

	@FXML
	private TextField denomText;

	@FXML
	private DatePicker desdeDate;

	@FXML
	private DatePicker hastaDate;

	@FXML
	private TextField orgText;

	@FXML
	private BorderPane view;

	public VentanaFormacionController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaFormacionView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Titulo onResultConverter(ButtonType button) {
		if (button.getButtonData() == ButtonData.OK_DONE) {
			Titulo titulo = new Titulo();
			titulo.setDenominacion(denominacion.get());
			titulo.setOrganizador(organizador.get());
			titulo.setDesde(desde.get());
			titulo.setHasta(hasta.get());
			return titulo;
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// init dialog
		ButtonType addButtonType = new ButtonType("Crear", ButtonData.OK_DONE);

		setTitle("Nuevo titulo");
		setHeaderText(null);
		setGraphic(null);
		getDialogPane().setContent(view);
		getDialogPane().getButtonTypes().setAll(addButtonType, ButtonType.CANCEL);

		setResultConverter(this::onResultConverter);

		// bindings
		denominacion.bind(denomText.textProperty());
		organizador.bind(orgText.textProperty());
		hasta.bind(hastaDate.valueProperty());
		desde.bind(desdeDate.valueProperty());

		// disable add button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		addButton.disableProperty()
				.bind(denominacion.isEmpty().or(organizador.isEmpty()).or(hasta.isNull()).or(desde.isNull()));
	}

	public BorderPane getView() {
		return view;
	}
}
