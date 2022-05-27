package fes.aragon.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.modelo.Articulo;

public class ArticuloImp<E> implements IBaseDatos<E> {

	@Override
	public ArrayList<E> consulta() throws Exception {
		String query = "select * from articulos";
		ArrayList<E> datos = new ArrayList<>();
		Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
		ResultSet resultado = solicitud.executeQuery(query);
		if (!resultado.next()) {
			System.out.println("Sin datos");
		} else {
			do {
				Articulo art = new Articulo();
				art.setId(resultado.getInt(1));
				art.setNombre(resultado.getString(2));
				art.setDescripcion(resultado.getString(3));
				art.setPrecio(resultado.getDouble(4));
				datos.add((E) art);
			} while (resultado.next());
		}
		solicitud.close();
		resultado.close();
		return datos;
	}

	public ArrayList<E> consultaValor(Integer id) throws Exception {
		/*String query = "select b.id_ats,b.nombre_ats,b.descripcion_ats,b.precio_ats from pedidos_articulos a, articulos b "
				+ "where"
				+ " a.id_ats=b.id_ats and id_pds=?";*/
		
		String query = "select b.id_ats,b.nombre_ats,b.descripcion_ats,b.precio_ats from pedidos_articulos a, articulos b "
				+ "where a.id_ats=b.id_ats and id_pds="+id;
		ArrayList<E> datos = new ArrayList<>();
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query);
		System.out.println(id);
		ResultSet resultado = solicitud.executeQuery(query);
		if (!resultado.next()) {
			System.out.println("Sin datos");
		} else {
			do {
				Articulo art = new Articulo();
				art.setId(resultado.getInt(1));
				art.setNombre(resultado.getString(2));
				art.setDescripcion(resultado.getString(3));
				art.setPrecio(resultado.getDouble(4));
				datos.add((E) art);
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
		Articulo ats = (Articulo) obj;
		String query = "insert into articulos(nombre_ats,descripcion_ats,precio_ats) " + "values(?,?,?)";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		solicitud.setString(1, ats.getNombre());
		solicitud.setString(2, ats.getDescripcion());
		solicitud.setDouble(3, ats.getPrecio());
		solicitud.executeUpdate();
		ResultSet resultado = solicitud.getGeneratedKeys();
		solicitud.close();
		resultado.close();
	}

	@Override
	public void modificar(E obj) throws Exception {
		Articulo ats = (Articulo) obj;
		String query = "update articulos set nombre_ats=?,descripcion_ats=?,precio_ats=? where id_ats=?";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		solicitud.setString(1, ats.getNombre());
		solicitud.setString(2, ats.getDescripcion());
		solicitud.setDouble(3, ats.getPrecio());
		solicitud.setInt(4, ats.getId());
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
		String query = "{call borrar_articulos(?)}";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
