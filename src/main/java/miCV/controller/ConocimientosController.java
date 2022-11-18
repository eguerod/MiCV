package miCV.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import miCV.controller.ventanas.VentanaConocimientoController;
import miCV.controller.ventanas.VentanaIdiomaController;

public class ConocimientosController implements Initializable {

	@FXML
	private BorderPane view;
	
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
		// TODO Auto-generated method stub

	}
	
	public BorderPane getView() {
		return view;
	}
	
	@FXML
    void onAddConocimientoAction(ActionEvent event) {
		Stage ventana = new Stage();
		VentanaConocimientoController controller = new VentanaConocimientoController();
		ventana.setTitle("Nuevo conocimiento");
		ventana.setScene(new Scene(controller.getView()));
		ventana.showAndWait();
    }

    @FXML
    void onAddIdiomaAction(ActionEvent event) {
    	Stage ventana = new Stage();
    	VentanaIdiomaController controller = new VentanaIdiomaController();
		ventana.setTitle("Nuevo conocimiento");
		ventana.setScene(new Scene(controller.getView()));
		ventana.showAndWait();
    }

    @FXML
    void onDeleteAction(ActionEvent event) {

    }

}
