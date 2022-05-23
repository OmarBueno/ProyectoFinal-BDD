package fes.aragon.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.modelo.Cliente;

public class ClienteImp<E> implements IBaseDatos<E> {

	@Override
	public ArrayList<E> consulta() throws Exception {
		String query = "select * from clientes";
		ArrayList<E> datos = new ArrayList<>();
		Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
		ResultSet resultado = solicitud.executeQuery(query);
		if (!resultado.next()) {
			System.out.println("Sin datos");
		} else {
			do {
				Cliente ct = new Cliente();
				ct.setId(resultado.getInt(1));
				ct.setNombre(resultado.getString(2));
				ct.setApPaterno(resultado.getString(3));
				ct.setApMaterno(resultado.getString(4));
				ct.setCorreo(resultado.getString(5));
				ct.setContrasena(resultado.getString(6));
				ct.setTelefono(resultado.getString(7));
				// System.out.println(ct);
				// buscar habitaciones de cada hotel
				datos.add((E) ct);
			} while (resultado.next());
		}
		solicitud.close();
		resultado.close();
		return datos;
	}

	@Override
	public ArrayList<E> buscar(String patron) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(E obj) throws Exception {
		Cliente cl = (Cliente) obj;
		String query = "insert into clientes(nombre_cls,ap_paterno_cls,ap_materno_cls,correo_cls,"
				+ "contraseña_cls,telefono_cls) values(?,?,?,?,?,?)";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		solicitud.setString(1, cl.getNombre());
		solicitud.setString(2, cl.getApPaterno());
		solicitud.setString(3, cl.getApMaterno());
		solicitud.setString(4, cl.getCorreo());
		solicitud.setString(5, cl.getContrasena());
		solicitud.setString(6, cl.getTelefono());
		solicitud.executeUpdate();
		ResultSet resultado = solicitud.getGeneratedKeys();
		solicitud.close();
		resultado.close();
	}

	@Override
	public void modificar(E obj) throws Exception {
		Cliente cl = (Cliente) obj;
		String query = "update clientes set nombre_cls=?,ap_paterno_cls=?,ap_materno_cls=?"
				+ ",correo_cls=?,contraseña_cls=?,telefono_cls=? where id_cls=?";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		solicitud.setString(1, cl.getNombre());
		solicitud.setString(2, cl.getApPaterno());
		solicitud.setString(3, cl.getApMaterno());
		solicitud.setString(4, cl.getCorreo());
		solicitud.setString(5, cl.getContrasena());
		solicitud.setString(6, cl.getTelefono());
		solicitud.setInt(7, cl.getId());
		solicitud.executeUpdate();
		ResultSet resultado = solicitud.getGeneratedKeys();
		solicitud.close();
		resultado.close();
	}

	@Override
	public E consulta(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		System.out.println("Entre borrar");
		String query = "{call borrar_clientes(?)}";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		solicitud.setInt(1, id);
		solicitud.executeUpdate();
		ResultSet resultado = solicitud.getGeneratedKeys();
		solicitud.close();
		resultado.close();

	}

	@Override
	public void cerrar() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarProc(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}
}
