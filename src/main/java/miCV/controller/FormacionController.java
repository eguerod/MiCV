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
import miCV.controller.ventanas.VentanaFormacionController;

public class FormacionController implements Initializable {

	@FXML
	private BorderPane view;
	
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
		Stage ventana = new Stage();
		VentanaFormacionController controller = new VentanaFormacionController();
		ventana.setTitle("Nuevo título");
		ventana.setScene(new Scene(controller.getView()));
		ventana.showAndWait();
    }

    @FXML
    void onDeleteAction(ActionEvent event) {

    }

}
