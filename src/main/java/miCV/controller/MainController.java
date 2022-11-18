package miCV.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import miCV.App;
import miCV.json.LocalDateAdapter;
import miCV.model.CV;

public class MainController implements Initializable {

	private PersonalController personalController;
	private ContactoController contactoController;
	private FormacionController formacionController;
	private ExperienciaController experienciaController;
	private ConocimientosController conocimientosController;

	private static Gson gson = FxGson.fullBuilder().setPrettyPrinting()
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

	@FXML
	private Tab conocimientosTab;

	@FXML
	private Tab contactoTab;

	@FXML
	private Tab experienciaTab;

	@FXML
	private Tab formacionTab;

	@FXML
	private Tab personalTab;

	@FXML
	private BorderPane view;

	public MainController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nuevoCV();
	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	void onAbrirAction(ActionEvent event) {
		try {
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.setInitialDirectory(new File(getClass().getResource("/json/").toURI()));
			File cvFile = fileChooser.showOpenDialog(App.primaryStage);
			if (cvFile != null) {
				miCV.model.CV cv = gson.fromJson(new FileReader(cvFile), CV.class);
				System.out.println(cvFile.getAbsolutePath());
			}
			//TODO Cargar fichero en la ventana
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onGuardarAction(ActionEvent event) {
		Alert alerta;
		try {
			CV cv = new CV();
			cv.setPersonal(personalController.getModelo());
			
			String json = gson.toJson(cv, CV.class);

//			File cvFile = new File(getClass().getResource("/json/cv.json").toURI());
			
			File cvFile = new File("cv.json");

			Files.writeString(cvFile.toPath(), json, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
			
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Guardado");
			alerta.setHeaderText("Archivo guardado");
			alerta.setContentText("El archivo se ha guardado correctamente.");
			alerta.showAndWait();
			
			nuevoCV();
			
		} catch (IOException /*| URISyntaxException*/ e) {
			alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error");
			alerta.setHeaderText("Archivo no guardado");
			alerta.setContentText("El archivo no se ha podido guardar debido a un error:\n"+e.getMessage());
			alerta.showAndWait();
		}
	}

	@FXML
	void onGuardarComoAction(ActionEvent event) {
		Alert alerta;
		try {
			CV cv = new CV();
			cv.setPersonal(personalController.getModelo());
			
			String json = gson.toJson(cv, CV.class);
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.setInitialDirectory(new File(getClass().getResource("/json/").toURI()));
			File cvFile = fileChooser.showOpenDialog(App.primaryStage);
			
			Files.writeString(cvFile.toPath(), json, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
			
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Guardado");
			alerta.setHeaderText("Archivo guardado");
			alerta.setContentText("El archivo se ha guardado correctamente.");
			alerta.showAndWait();
			
			nuevoCV();
		} catch (URISyntaxException | IOException e) {
			alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error");
			alerta.setHeaderText("Archivo no guardado");
			alerta.setContentText("El archivo no se ha podido guardar debido a un error:\n"+e.getMessage());
			alerta.showAndWait();
		}
	}

	@FXML
	void onNuevoAction(ActionEvent event) {
		nuevoCV();
	}

	@FXML
	void onSalirActon(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText("¿Está seguro de que quiere salir?");
		Optional<ButtonType> action = alert.showAndWait();
		if (action.get() == ButtonType.OK) {
			Platform.exit();
		}
	}

	private void nuevoCV() {
		personalController = new PersonalController();
		contactoController = new ContactoController();
		formacionController = new FormacionController();
		experienciaController = new ExperienciaController();
		conocimientosController = new ConocimientosController();

		personalTab.setContent(personalController.getView());
		contactoTab.setContent(contactoController.getView());
		formacionTab.setContent(formacionController.getView());
		experienciaTab.setContent(experienciaController.getView());
		conocimientosTab.setContent(conocimientosController.getView());
	}
}
