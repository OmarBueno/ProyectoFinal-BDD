package fes.aragon.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fes.aragon.modelo.Articulo;
import fes.aragon.modelo.Articulos;
import fes.aragon.modelo.Cliente;
import fes.aragon.modelo.ClientesPedidos;
import fes.aragon.modelo.Extra;
import fes.aragon.modelo.Pedido;
import fes.aragon.modelo.Pedidos;
import fes.aragon.modelo.TipoError;
import fes.aragon.mysql.ArticuloImp;
import fes.aragon.mysql.ClienteImp;
import fes.aragon.mysql.ExtraImp;
import fes.aragon.mysql.PedidoImp;
import javafx.collections.FXCollections;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BaseController {
	protected boolean nombreValido = true;
	protected boolean correoValido = true;
	protected boolean telefonoValido = true;
	protected boolean pedidoValido = true;
	protected boolean costoValido = true;
	/*
	 * EXPRESIONES REGULARES 0 palabras sin espacio 1 solo n�meros 2 validar RFC 3
	 * validar correo 4 validar tel�fono
	 */
	private String[] expresiones = { "(\\w+)", "(\\d+)(\\.\\d{1,2})", "(\\w){13}",
			"^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", "(\\d){10}" };

	public void nuevaVentana(String archivo) {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/" + archivo + ".fxml"));
			Scene scene = new Scene(root);
			Stage escenario = new Stage();
			escenario.setScene(scene);
			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nuevaVentanaEstado(String archivo) {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/" + archivo + ".fxml"));
			AdministradorArticulosController.estado=true;
			Scene scene = new Scene(root);
			Stage escenario = new Stage();
			escenario.setScene(scene);
			escenario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ventanaEmergente(String titulo, String encabezado, String contenido) {
		Alert alerta;
		alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle(titulo);
		alerta.setHeaderText(encabezado);
		alerta.setContentText(contenido);
		alerta.showAndWait();
	}

	public void cerrarVentana(Button boton) {
		Stage stage = (Stage) boton.getScene().getWindow();
		stage.close();
	}

	public void verificarEntrada(TextField caja, TipoError error) {
		caja.textProperty().addListener(evento -> {
			String text = caja.getText();
			if (text == null) {
				text = "";
			}
			String patron = expresiones[error.ordinal()];
			Pattern pt = Pattern.compile(patron);
			Matcher match = pt.matcher(text);
			caja.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !match.matches());
			if (error == TipoError.PALABRAS) {
				this.pedidoValido = match.matches();
			} else if (error == TipoError.NUMEROS) {
				this.costoValido = match.matches();
			} else if (error == TipoError.RFC) {
				this.costoValido = match.matches();
			} else if (error == TipoError.CORREO) {
				this.correoValido = match.matches();
			} else if (error == TipoError.TELEFONO) {
				this.telefonoValido = match.matches();
			}
		});
	}

	public void recogerDatos() throws Exception {
		ClienteImp<Cliente> cn = new ClienteImp<>();
		ArrayList<Cliente> datos = cn.consulta();
		for (Cliente cliente : datos) {
			Cliente objeto = new Cliente();
			objeto.setId(cliente.getId());
			objeto.setNombre(cliente.getNombre());
			objeto.setApPaterno(cliente.getApPaterno());
			objeto.setApMaterno(cliente.getApMaterno());
			objeto.setCorreo(cliente.getCorreo());
			objeto.setTelefono(cliente.getTelefono());
			objeto.setPedidos(FXCollections.observableArrayList(cliente.getPedidos()));
			ClientesPedidos.getInstancia().getGrupoClientesPedidos().add(objeto);
		}
	}
	
	public void recogerDatosArticulos() throws Exception {
		ArticuloImp<Articulo> cn = new ArticuloImp<>();
		ArrayList<Articulo> datos = cn.consulta();
		for (Articulo art : datos) {
			Articulo objeto = new Articulo();
			objeto.setId(art.getId());
			objeto.setNombre(art.getNombre());
			objeto.setDescripcion(art.getDescripcion());
			objeto.setPrecio(art.getPrecio());
			Articulos.getInstancia().getGrupoArticulos().add(objeto);
		}
	}
	
	public void recogerDatosArticulosPedido() throws Exception {
		ArticuloImp<Articulo> cn = new ArticuloImp<>();
		ArrayList<Articulo> datos = cn.consulta();
		for (Articulo art : datos) {
			Articulo objeto = new Articulo();
			objeto.setId(art.getId());
			objeto.setNombre(art.getNombre());
			objeto.setDescripcion(art.getDescripcion());
			objeto.setPrecio(art.getPrecio());
			Articulos.getInstancia().getGrupoArticulos().add(objeto);
		}
	}

	public void recogerDatosPedidos() throws Exception {
		PedidoImp<Pedido> cn = new PedidoImp<>();
		ArrayList<Pedido> datos = cn.consulta();
		for (Pedido pedido : datos) {
			Pedido objeto = new Pedido();
			objeto.setId(pedido.getId());
			objeto.setDireccion(pedido.getDireccion());
			objeto.setFecha(pedido.getFecha());
			objeto.setEstado(pedido.getEstado());
			objeto.setTotal(pedido.getTotal());
			objeto.setCliente(pedido.getCliente());
			objeto.setArticulos(FXCollections.observableArrayList(pedido.getArticulos()));
			Pedidos.getInstancia().getGrupoPedidos().add(objeto);
		}

	}
	

}
