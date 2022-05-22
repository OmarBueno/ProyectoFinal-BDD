package fes.aragon.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.modelo.Articulo;
import fes.aragon.modelo.Articulos;
import fes.aragon.modelo.Cliente;
import fes.aragon.modelo.Extra;

public class ArticuloImp<E> implements IBaseDatos<E>{

	@Override
	public ArrayList<E> consulta() throws Exception {
		String query = "select * from articulos a, extras b where a.id_exs=b.id_exs order by id_ats";
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
				art.setLeche(resultado.getString(5));
				art.setCrema(resultado.getBoolean(7));
				art.setExtra(new Extra(resultado.getInt(8),resultado.getString(9)));
				System.out.println(art);
				// buscar habitaciones de cada hotel
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
		Articulo ats= (Articulo) obj;
		String query = "insert into articulos(nombre_ats,descripcion_ats,precio_ats,tipo_leche_ats,"
				+ "id_exs,crema_batida_ats) values(?,?,?,?,?,?)";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		solicitud.setString(1, ats.getNombre());
		solicitud.setString(2, ats.getDescripcion());
		solicitud.setDouble(3, ats.getPrecio());
		solicitud.setString(4, ats.getLeche());
		solicitud.setInt(5, ats.getExtra().getId());
		solicitud.setBoolean(6, ats.isCrema());
		solicitud.executeUpdate();
		ResultSet resultado = solicitud.getGeneratedKeys();
		
		solicitud.close();
		resultado.close();				
	}

	@Override
	public void modificar(E obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E consulta(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
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
