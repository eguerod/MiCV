package miCV.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
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
	
	private Contacto model = new Contacto();

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
		telefonoTab.itemsProperty().bind(model.telefonosProperty());
		emailTab.itemsProperty().bind(model.emailsProperty());
		urlTab.itemsProperty().bind(model.websProperty());
		
		numCol.setCellValueFactory(v -> v.getValue().telefonoProperty());
		tipoCol.setCellValueFactory(v -> v.getValue().tipoProperty());
		emailCol.setCellValueFactory(v -> v.getValue().direccionProperty());
		urlCol.setCellValueFactory(v -> v.getValue().urlProperty());
		
//		numCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		tipoCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		urlCol.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	public SplitPane getView() {
		return view;
	}

	@FXML
	void onAddEmailAction(ActionEvent event) {
		Dialog dialog = new Dialog<>();
		dialog.setDialogPane(null);
	}

	@FXML
	void onAddTlfAction(ActionEvent event) {

	}

	@FXML
	void onAddUrlAction(ActionEvent event) {

	}

	@FXML
	void onDeleteEmailAction(ActionEvent event) {

	}

	@FXML
	void onDeleteTlfAction(ActionEvent event) {

	}

	@FXML
	void onDeleteUrlAction(ActionEvent event) {

	}

}
