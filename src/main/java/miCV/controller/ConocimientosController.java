package miCV.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import miCV.controller.ventanas.VentanaConocimientoController;
import miCV.controller.ventanas.VentanaIdiomaController;
import miCV.model.Conocimiento;
import miCV.model.Nivel;

public class ConocimientosController implements Initializable {

	@FXML
	private TableView<Conocimiento> conocimientosTable;

	@FXML
	private TableColumn<Conocimiento, String> denomColumn;

	@FXML
	private TableColumn<Conocimiento, Nivel> nivelColumn;

	@FXML
	private BorderPane view;

	private ListProperty<Conocimiento> conocimientos = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Conocimiento> selectedConocimiento = new SimpleObjectProperty<>();

	public ConocimientosController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conocimientosTable.itemsProperty().bind(conocimientos);
		selectedConocimiento.bind(conocimientosTable.getSelectionModel().selectedItemProperty());

		denomColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());
	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	void onAddConocimientoAction(ActionEvent event) {
		VentanaConocimientoController ventana = new VentanaConocimientoController();
		ventana.showAndWait();
		if (ventana.getResult() != null)
			conocimientos.add(ventana.getResult());
	}

	@FXML
	void onAddIdiomaAction(ActionEvent event) {
		VentanaIdiomaController ventana = new VentanaIdiomaController();
		ventana.showAndWait();
		if (ventana.getResult() != null)
			conocimientos.add(ventana.getResult());
	}

	@FXML
	void onDeleteAction(ActionEvent event) {
		conocimientos.remove(selectedConocimiento.get());
	}

	public ListProperty<Conocimiento> getConocimientos() {
		return conocimientos;
	}

}
