package miCV.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.LocalDateStringConverter;
import miCV.controller.ventanas.VentanaExperienciaController;
import miCV.model.Experiencia;

public class ExperienciaController implements Initializable {

	@FXML
	private TableColumn<Experiencia, String> denomColumn;

	@FXML
	private TableColumn<Experiencia, LocalDate> desdeColumn;

	@FXML
	private TableColumn<Experiencia, String> empleadorColumn;

	@FXML
	private TableView<Experiencia> experienciaTable;

	@FXML
	private TableColumn<Experiencia, LocalDate> hastaColumn;

	@FXML
	private BorderPane view;

	private ListProperty<Experiencia> experiencias = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Experiencia> selectedExperiencia = new SimpleObjectProperty<>();

	public ExperienciaController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		experienciaTable.itemsProperty().bindBidirectional(experiencias);
		selectedExperiencia.bind(experienciaTable.getSelectionModel().selectedItemProperty());

		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		denomColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		empleadorColumn.setCellValueFactory(v -> v.getValue().empleadorProperty());

		hastaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		desdeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	void onAddAction(ActionEvent event) {
		VentanaExperienciaController ventana = new VentanaExperienciaController();
		ventana.showAndWait();
		if (ventana.getResult() != null)
			experiencias.add(ventana.getResult());
	}

	@FXML
	void onDeleteAction(ActionEvent event) {
		experiencias.remove(selectedExperiencia.get());
	}
	
	public ListProperty<Experiencia> getExperiencias() {
		return experiencias;
	}
	
	protected void vaciar() {
		experiencias.clear();
	}
	
	protected void setExperiencias(ObservableList<Experiencia> e) {
		experiencias.addAll(e);
	}
}
