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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.LocalDateStringConverter;
import miCV.controller.ventanas.VentanaFormacionController;
import miCV.model.Titulo;

public class FormacionController implements Initializable {

	@FXML
	private TableColumn<Titulo, String> denomColumn;

	@FXML
	private TableColumn<Titulo, LocalDate> desdeColumn;

	@FXML
	private TableView<Titulo> formacionTable;

	@FXML
	private TableColumn<Titulo, LocalDate> hastaColumn;

	@FXML
	private TableColumn<Titulo, String> organizadorColumn;

	@FXML
	private BorderPane view;

	private ListProperty<Titulo> titulos = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Titulo> selectedTitulo = new SimpleObjectProperty<>();

	public FormacionController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		formacionTable.itemsProperty().bind(titulos);
		selectedTitulo.bind(formacionTable.getSelectionModel().selectedItemProperty());
		
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		denomColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorColumn.setCellValueFactory(v -> v.getValue().organizadorProperty());
		
		hastaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		desdeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	void onAddAction(ActionEvent event) {
		VentanaFormacionController ventana = new VentanaFormacionController();
		ventana.showAndWait();
		if (ventana.getResult() != null)
			titulos.add(ventana.getResult());
	}

	@FXML
	void onDeleteAction(ActionEvent event) {
		titulos.remove(selectedTitulo.get());
	}
	
	public ListProperty<Titulo> getTitulos() {
		return titulos;
	}

}
