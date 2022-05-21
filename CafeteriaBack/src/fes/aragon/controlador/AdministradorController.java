package fes.aragon.controlador;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import fes.aragon.mysql.Conexion;
import javafx.event.ActionEvent;

public class AdministradorController extends BaseController{
	@FXML
	private TextField txtContraseña;
	@FXML
	private TextField txtUsuario;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnSalir;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptarAdministrador(ActionEvent event) {
		Conexion cn;
		try {
			cn = new Conexion();
			if((this.txtUsuario.getText().equals(cn.getUsuario())) || (this.txtContraseña.getText().equals(cn.getClave()))){
				System.out.println("Conexion establecida");
				cn.iniciarConexion();
				this.nuevaVentana("AdministradorAceptar");
			
			}
			else {
				System.out.println("Contrase�a incorrecta");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	// Event Listener on Button[#btnSalir].onAction
	@FXML
	public void salirAdministrador(ActionEvent event) {
		this.cerrarVentana(btnSalir);
	}
}
