package miCV.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import miCV.model.Nacionalidad;
import miCV.model.Personal;

public class PersonalController implements Initializable {
	
	@FXML
    private TextField apellidosText;

    @FXML
    private TextField codPostalText;

    @FXML
    private TextArea direccionText;

    @FXML
    private TextField identificacionText;

    @FXML
    private TextField localidadText;

    @FXML
    private DatePicker nacimientoDate;
    
	@FXML
	private ComboBox<String> paisCombo;
	
	@FXML
    private TextField nombreText;

	@FXML
    private ListView<Nacionalidad> nacionalidadList;
	
	@FXML
	private GridPane view;

	private Personal modelo = new Personal();
	
	private ObjectProperty<Nacionalidad> nacionalidadSelected = new SimpleObjectProperty<>();

	public PersonalController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modelo.identificacionProperty().bindBidirectional(identificacionText.textProperty());
		
		modelo.nombreProperty().bindBidirectional(nombreText.textProperty());
		
		modelo.apellidosProperty().bindBidirectional(apellidosText.textProperty());
		
		modelo.fechaNacimientoProperty().bindBidirectional(nacimientoDate.valueProperty());
		
		modelo.direccionProperty().bindBidirectional(direccionText.textProperty());
		
		modelo.codigoPostalProperty().bindBidirectional(codPostalText.textProperty());
		
		modelo.localidadProperty().bindBidirectional(localidadText.textProperty());
		
		modelo.paisProperty().bind(paisCombo.getSelectionModel().selectedItemProperty());
		paisCombo.getItems().addAll(readFromFile("/csv/paises.csv"));
		
		nacionalidadList.itemsProperty().bindBidirectional(modelo.nacionalidadesProperty());
		nacionalidadSelected.bind(nacionalidadList.getSelectionModel().selectedItemProperty());
	}

	public GridPane getView() {
		return view;
	}
	
	@FXML
    void onAddAction(ActionEvent event) {
		List<String> nacionalidades = readFromFile("/csv/nacionalidades.csv");
		ChoiceDialog<String> dialog = new ChoiceDialog<>(nacionalidades.get(0), nacionalidades);
		dialog.setTitle("Nueva nacionalidad");
		dialog.setHeaderText("Añadir nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad:");
		dialog.showAndWait();
		Nacionalidad nac = new Nacionalidad();
		nac.setDenominacion(dialog.getResult());
		modelo.getNacionalidades().add(nac);
    }

    @FXML
    void onRemoveAction(ActionEvent event) {
    	modelo.getNacionalidades().remove(nacionalidadSelected.get());
    }
    
	private List<String> readFromFile(String resource) {
		List<String> paises = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(new File(getClass().getResource(resource).toURI())));
			String line;
			while ((line = reader.readLine()) != null)
				paises.add(line);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return paises;
	}
	
	public Personal getModelo() {
		return modelo;
	}
	
	protected void vaciar() {
		identificacionText.setText("");
		nombreText.setText("");
		apellidosText.setText("");
		nacimientoDate.setValue(null);
		direccionText.setText("");
		codPostalText.setText("");
		localidadText.setText("");
		paisCombo.getSelectionModel().clearSelection();
		modelo.getNacionalidades().clear();
	}
	
	protected void setPersonal(Personal p) {
		modelo.setIdentificacion(p.getIdentificacion());
		modelo.setNombre(p.getNombre());
		modelo.setApellidos(p.getApellidos());
		modelo.setFechaNacimiento(p.getFechaNacimiento());
		modelo.setDireccion(p.getDireccion());
		modelo.setCodigoPostal(p.getCodigoPostal());
		modelo.setLocalidad(p.getLocalidad());
		paisCombo.getSelectionModel().select(p.getPais());
		modelo.getNacionalidades().addAll(p.getNacionalidades());
	}
}
