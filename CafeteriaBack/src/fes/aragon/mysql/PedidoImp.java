package fes.aragon.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.modelo.Articulo;
import fes.aragon.modelo.Cliente;
import fes.aragon.modelo.Extra;
import fes.aragon.modelo.Pedido;

public class PedidoImp<E> implements IBaseDatos<E> {

	@Override
	public ArrayList<E> consulta() throws Exception {
		String query = "SELECT * FROM PEDIDOS a, clientes b WHERE a.id_cls=b.id_cls order by id_pds asc;";
		ArrayList<E> datos = new ArrayList<>();
		Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
		ResultSet resultado = solicitud.executeQuery(query);
		if (!resultado.next()) {
			System.out.println("Sin datos");
		} else {
			do {
				Pedido pedido = new Pedido();
				pedido.setId(resultado.getInt(1));
				pedido.setDireccion(resultado.getString(2));
				pedido.setFecha(resultado.getString(3));
				pedido.setEstado(resultado.getString(4));
				pedido.setTotal(resultado.getDouble(5));
				Cliente cl = new Cliente();
				cl.setId(resultado.getInt(7));
				cl.setNombre(resultado.getString(8));
				cl.setApPaterno(resultado.getString(9));
				cl.setApMaterno(resultado.getString(10));
				cl.setCorreo(resultado.getString(11));
				cl.setContrasena(resultado.getString(12));
				cl.setTelefono(resultado.getString(13));
				pedido.setCliente(cl);
				// buscar habitaciones de cada hotel
				datos.add((E) pedido);
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
		Pedido pedido = (Pedido) obj;
		String query = "insert into pedidos(direccion_pds,fecha_hora_pds,estado_pds,total_pds,"
				+ "id_cls) values(?,?,?,?,?)";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		solicitud.setString(1, pedido.getDireccion());
		solicitud.setString(2, pedido.getFecha());
		solicitud.setString(3, pedido.getEstado());
		solicitud.setDouble(4, pedido.getTotal());
		solicitud.setInt(5, pedido.getCliente().getId());
		solicitud.executeUpdate();
		ResultSet resultado = solicitud.getGeneratedKeys();
		if (resultado.next()) {
			pedido.setId(resultado.getInt(1));
		}
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
