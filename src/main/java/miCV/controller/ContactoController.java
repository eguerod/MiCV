package miCV.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import miCV.controller.ventanas.NuevaWebDialog;
import miCV.controller.ventanas.NuevoEmailDialog;
import miCV.controller.ventanas.NuevoTelefonoDialog;
import miCV.model.Contacto;
import miCV.model.Email;
import miCV.model.Telefono;
import miCV.model.TipoTelefono;
import miCV.model.Web;


public class ContactoController implements Initializable {

	@FXML
	private TableColumn<Email, String> emailCol;

	@FXML
	private TableView<Email> emailTab;

	@FXML
	private TableColumn<Telefono, String> numCol;

	@FXML
	private TableView<Telefono> telefonoTab;

	@FXML
	private TableColumn<Telefono, TipoTelefono> tipoCol;

	@FXML
	private TableColumn<Web, String> urlCol;

	@FXML
	private TableView<Web> urlTab;

	@FXML
	private SplitPane view;
	
	private ObjectProperty<Telefono> telefonoSelected = new SimpleObjectProperty<>();
	private ObjectProperty<Web> webSelected = new SimpleObjectProperty<>();
	private ObjectProperty<Email> emailSelected = new SimpleObjectProperty<>();
	
	private Contacto modelo = new Contacto();

	public ContactoController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		telefonoTab.itemsProperty().bind(modelo.telefonosProperty());
		emailTab.itemsProperty().bind(modelo.emailsProperty());
		urlTab.itemsProperty().bind(modelo.websProperty());
		
		numCol.setCellValueFactory(v -> v.getValue().telefonoProperty());
		tipoCol.setCellValueFactory(v -> v.getValue().tipoProperty());
		emailCol.setCellValueFactory(v -> v.getValue().direccionProperty());
		urlCol.setCellValueFactory(v -> v.getValue().urlProperty());
		
//		numCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		tipoCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		urlCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		telefonoSelected.bind(telefonoTab.getSelectionModel().selectedItemProperty());
		emailSelected.bind(emailTab.getSelectionModel().selectedItemProperty());
		webSelected.bind(urlTab.getSelectionModel().selectedItemProperty());
	}

	public SplitPane getView() {
		return view;
	}

	@FXML
	void onAddEmailAction(ActionEvent event) {
		NuevoEmailDialog dialogo = new NuevoEmailDialog();
		dialogo.showAndWait();
		if(dialogo.getResult()!=null)
			modelo.getEmails().add(dialogo.getResult());
	}

	@FXML
	void onAddTlfAction(ActionEvent event) {
		NuevoTelefonoDialog dialogo = new NuevoTelefonoDialog();
		dialogo.showAndWait();
		if(dialogo.getResult()!=null)
			modelo.getTelefonos().add(dialogo.getResult());
	}

	@FXML
	void onAddUrlAction(ActionEvent event) {
		NuevaWebDialog dialogo = new NuevaWebDialog();
		dialogo.showAndWait();
		if(dialogo.getResult()!=null)
			modelo.getWebs().add(dialogo.getResult());
	}

	@FXML
	void onDeleteEmailAction(ActionEvent event) {
		modelo.getEmails().remove(emailSelected.get());
	}

	@FXML
	void onDeleteTlfAction(ActionEvent event) {
		modelo.getTelefonos().remove(telefonoSelected.get());
	}

	@FXML
	void onDeleteUrlAction(ActionEvent event) {
		modelo.getWebs().remove(webSelected.get());
	}
	
	public Contacto getModelo() {
		return modelo;
	}

}
