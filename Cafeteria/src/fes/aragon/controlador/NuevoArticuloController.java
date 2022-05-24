package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Articulo;
import fes.aragon.modelo.Articulos;
import fes.aragon.mysql.ArticuloImp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.CheckBox;

public class NuevoArticuloController extends BaseController implements Initializable {

	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtPrecio;
	@FXML
	private TextField txtDescripcion;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnSalir;
	@FXML
	private ComboBox<String> cmbTipoLeche;
	@FXML
	private CheckBox chkCremaBatida;
	@FXML
	private TextField txtCantidad;

	private Articulo art = new Articulo();

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptarNuevo(ActionEvent event) {
		try {
			art.setNombre(this.txtNombre.getText());
			art.setDescripcion(this.txtDescripcion.getText());
			art.setPrecio(Double.valueOf(txtPrecio.getText()));
			ObservableList<Articulo> grupo = Articulos.getInstancia().getGrupoArticulos();
			if (Articulos.getInstancia().isGrupoArticulo()) {
				Articulos.getInstancia().getGrupoArticulos().set
				(Articulos.getInstancia().getIndice(), art);
				ArticuloImp<Articulo> cn = new ArticuloImp<>();
				cn.modificar(art);
			}
			else {
				grupo.add(art);
				ArticuloImp<Articulo> cn = new ArticuloImp<>();
				cn.insertar(art);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Articulos.getInstancia().setGrupoArticulo(false);
		this.cerrarVentana(btnAceptar);
	}

	// Event Listener on Button[#btnSalir].onAction
	@FXML
	public void salir(ActionEvent event) {
		Articulos.getInstancia().setGrupoArticulo(false);
		this.cerrarVentana(btnSalir);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Articulos.getInstancia().isGrupoArticulo()) {
			this.art = Articulos.getInstancia().getGrupoArticulos()
					.get(Articulos.getInstancia().getIndice());
			this.txtNombre.setText(art.getNombre());
			this.txtDescripcion.setText(art.getDescripcion());
			this.txtPrecio.setText(String.valueOf(art.getPrecio()));
		}
	}
}
