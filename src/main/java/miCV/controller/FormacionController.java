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
import javafx.scene.layout.BorderPane;
import miCV.controller.ventanas.VentanaFormacionController;
import miCV.model.Titulo;

public class FormacionController implements Initializable {

	@FXML
	private BorderPane view;
	
	private ListProperty<Titulo> titulos  = new SimpleListProperty<>(FXCollections.observableArrayList());
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
		// TODO Auto-generated method stub

	}
	
	public BorderPane getView() {
		return view;
	}
	
	@FXML
    void onAddAction(ActionEvent event) {
		VentanaFormacionController ventana = new VentanaFormacionController();
		ventana.showAndWait();
		if(ventana.getResult()!=null)
			titulos.add(ventana.getResult());
    }

    @FXML
    void onDeleteAction(ActionEvent event) {

    }

}
