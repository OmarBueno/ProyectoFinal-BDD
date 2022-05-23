package fes.aragon.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
	private String url = "jdbc:mysql://localhost:3306/cafeteria1?serverTimezone=UTC";
	private String usuario = "root";
	private String clave = "admin";
	private Connection cnn = null;
	private static Conexion instancia;
	public Conexion() throws Exception {		
		Class.forName("com.mysql.cj.jdbc.Driver");
		cnn = DriverManager.getConnection(url, usuario, clave);
		System.out.println("Conexion establecida");
	}	

	public static Conexion getInstancia() throws Exception {
		if(instancia==null) {
			instancia=new Conexion();
		}
		return instancia;		
		
	}
	public void iniciarConexion() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cnn = DriverManager.getConnection(url, usuario, clave);
		System.out.println("Conexion establecida");
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Connection getCnn() {
		return cnn;
	}

	public void cerrar() throws SQLException {
		this.cnn.close();
	}

}
