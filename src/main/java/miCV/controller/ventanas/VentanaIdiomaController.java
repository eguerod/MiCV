package miCV.controller.ventanas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class VentanaIdiomaController implements Initializable {
	
	@FXML
	private BorderPane view;

	public VentanaIdiomaController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaIdiomaView.fxml"));
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

}
