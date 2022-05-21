package fes.aragon.controlador;

import fes.aragon.modelo.Extra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdministradorExtraController extends BaseController{

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnNuevoCExtra;

    @FXML
    private Button btnSalir;

    @FXML
    private TableColumn<Extra, Integer> clmId;

    @FXML
    private TableColumn<Extra, String> clmNombre;

    @FXML
    private TableView<Extra> tblExtra;

    @FXML
    void editarCExtra(ActionEvent event) {

    }

    @FXML
    void eliminarCExtra(ActionEvent event) {

    }

    @FXML
    void nuevoExtra(ActionEvent event) {
    	this.nuevaVentana("NuevoExtra");
    }

    @FXML
    void salir(ActionEvent event) {

    }

}
