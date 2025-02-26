package Repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Usuario;
import Modelo.Usuario_Vehiculo;
import Modelo.Vehiculo;


public class RepositorioReserva{

	
	//Método para mostrar vehiculos según la oficina
	public static void mostrarVehiculoOficina(String nombreOficina) {
		
		String consulta = "SELECT * FROM vehiculo NATURAL JOIN oficina WHERE nombre = ?";

				
		 try {PreparedStatement s=ConectorBD.getconexion().prepareStatement(consulta);
		 	s.setString(1, nombreOficina);
			ResultSet rs=s.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("matricula")+" "+rs.getString("marca")+" "+ rs.getString("modelo")+" "+" "+ rs.getInt("km")+" "+ rs.getString("tipo")+" "+ rs.getInt("num_puertas")+" "+ rs.getInt("potencia")+" "+ rs.getString("tamaño"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta "+ consulta);
		}
	}
	//Método que devuelve booleano, para comprobar si el vehículo de esa oficina está libre esa fecha
		public static boolean comprobarFecha(String matricula, String fecha_recogida, String fecha_entrega) {
			boolean existe= false;
			
			String consulta = "SELECT * FROM usuario_vehiculo NATURAL JOIN vehiculo WHERE matricula = ? AND fecha_entrega = ? AND fecha_recogida = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, matricula);
				s.setString(2, fecha_entrega);
				s.setString(3, fecha_recogida);
				
				ResultSet rs= s.executeQuery();
				
				if(rs.next()) {
					existe = true;	
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error "+ consulta);
			}
			return existe;
		}
	//Método para mostrar que vehículos tiene libre esa oficina en esa fecha
	public static void vehiculoLibre(int id_oficina, Usuario_Vehiculo usuariovehiculo) {
		
		String consulta ="SELECT * \r\n"
				+ "FROM VEHICULO V \r\n"
				+ "NATURAL JOIN OFICINA O\r\n"
				+ "WHERE O.ID_OFICINA = ?\r\n"
				+ "AND NOT EXISTS (\r\n"
				+ "    SELECT 1 \r\n"
				+ "    FROM USUARIO_VEHICULO UV\r\n"
				+ "    WHERE V.id_coche = UV.id_coche\r\n"
				+ "    AND (? BETWEEN UV.fecha_recogida AND UV.fecha_entrega \r\n"
				+ "        OR ? BETWEEN UV.fecha_recogida AND UV.fecha_entrega\r\n"
				+ "        OR (UV.fecha_recogida BETWEEN ? AND ?)\r\n"
				+ "        OR (UV.fecha_entrega BETWEEN ? AND ?)\r\n"
				+ "    )\r\n"
				+ ");";

		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		s.setInt(1, id_oficina);
		s.setString(2, usuariovehiculo.getFecha_recogida());
		s.setString(3, usuariovehiculo.getFecha_entrega());
		s.setString(4, usuariovehiculo.getFecha_recogida());
		s.setString(5, usuariovehiculo.getFecha_entrega());
		s.setString(6, usuariovehiculo.getFecha_recogida());
		s.setString(7, usuariovehiculo.getFecha_entrega());
		
		ResultSet rs = s.executeQuery();
		
		while(rs.next()) {
			  String tipoVehiculo = rs.getString("v.tipo");
		        int precio = 0;

		        // Determinar el precio según el tipo de vehículo
		        if (tipoVehiculo.equalsIgnoreCase("monovolumen")) {
		            precio = rs.getInt("precio_monovolumen");
		        } else if (tipoVehiculo.equalsIgnoreCase("turismo")) {
		            precio = rs.getInt("precio_turismo");
		        } else if (tipoVehiculo.equalsIgnoreCase("furgoneta")) {
		            precio = rs.getInt("precio_furgoneta");
		        } else {
		            System.out.println("Tipo de vehículo desconocido.");
		        }
			System.out.println(rs.getString("matricula")+" "+rs.getString("marca")+" "
		        +rs.getString("modelo")+" "+rs.getInt("km")+" "+"Precio por día: " + precio);
		}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
		}
	}
		
	//Método que devuelve booleano, para comprobar la oficina
	public static boolean comprobarOficina(String nombre) {
		boolean existe = false;
		String consulta = "SELECT * FROM vehiculo v NATURAL JOIN oficina o WHERE o.nombre=?";
		try {
			PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, nombre);
			ResultSet rs=s.executeQuery();
			
			if(rs.next()) {
				existe = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta "+ consulta);
		}
		return existe;
	}
	
	//Mostar listado de oficinas en españa
	public static void mostrarOficinasEspana() {
		
		String consulta = "SELECT * FROM oficina WHERE pais = 'España'";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		
		ResultSet rs = s.executeQuery();
		
		while (rs.next()) {
			System.out.println(rs.getString("nombre")+", "+rs.getString("ciudad")+", "+rs.getString("telefono"));
		}
			
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Error "+ consulta);
		}
	}
	
	//Método para mostrar el vehiculo y la oficina elegidos
	public static void vehiculoSeleccionado(String matricula) {
		
		String consulta = "SELECT * FROM vehiculo v NATURAL JOIN oficina o WHERE matricula = ?";
		
		try {
			PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, matricula);
			ResultSet rs=s.executeQuery();
			
			 while (rs.next()) {
			        String tipoVehiculo = rs.getString("v.tipo");
			        int precio = 0;

			        // Determinar el precio según el tipo de vehículo
			        if (tipoVehiculo.equalsIgnoreCase("monovolumen")) {
			            precio = rs.getInt("precio_monovolumen");
			        } else if (tipoVehiculo.equalsIgnoreCase("turismo")) {
			            precio = rs.getInt("precio_turismo");
			        } else if (tipoVehiculo.equalsIgnoreCase("furgoneta")) {
			            precio = rs.getInt("precio_furgoneta");
			        } else {
			            System.out.println("Tipo de vehículo desconocido.");
			        }

			        System.out.println("Oficina: " + rs.getString("o.nombre") +
			                "\nVehículo: " + rs.getString("v.matricula") + " " + rs.getString("v.marca") + " " + 
			                rs.getString("v.modelo") + " " + rs.getInt("v.km") + " " + rs.getString("v.tipo") + 
			                "\nPrecio por día: " + precio);
			 }
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta "+ consulta);
		}
	}
	
	//Método que devuelve booleano, para comprobar la matrícula
	public static boolean comprobarMatricula(String matricula) {
		
		boolean existe=false;
		
		String consulta = "SELECT * FROM vehiculo NATURAL JOIN oficina WHERE matricula=?";
		try {
			PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, matricula);
			ResultSet rs=s.executeQuery();
			
			if(rs.next()) {
				existe = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta "+ consulta);
		}
		return existe;
	}
	
	//Métod que devuelve un número entero, que es el id del vehículo
	public static int obtenerId(String matricula) {
		int id_coche=0;
		
		String consulta = "SELECT * FROM vehiculo WHERE matricula = ?";
		
		try {
			PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, matricula);
			
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				id_coche= rs.getInt("id_coche");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error " +consulta);
		}
		return id_coche;
	}
	
	//Método insertar el alquiler del vehículo en la tabla usuario_vehiculo
	public static void anadirReserva(Usuario usuario, Usuario_Vehiculo usuariovehiculo) {
		
		String consulta = "INSERT INTO usuario_vehiculo (dni, id_coche, precio_total, fecha_recogida, fecha_entrega, conconductor, Lugar_recogida, Lugar_entrega) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareCall(consulta);
			s.setString(1, usuariovehiculo.getDni());
			s.setInt(2, usuariovehiculo.getId_coche());
			s.setDouble(3, usuariovehiculo.getPrecio_total());
			s.setString(4, usuariovehiculo.getFecha_recogida());
			s.setString(5, usuariovehiculo.getFecha_entrega());
			s.setBoolean(6, usuariovehiculo.isConConductor());
			s.setString(7, usuariovehiculo.getLugarRecogida());
			s.setString(8, usuariovehiculo.getLugarEntrega());
			
			s.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+ consulta);
		}
	}
	//Método que devuelve un String, obtiene el dni del usuario
	public static String usuario (String nombre, String contraseña) {
		
		String dni=" ";
		String consulta = "SELECT * FROM usuario WHERE nombre = ? and contraseña = ?";
		
		try {PreparedStatement s= ConectorBD.getconexion().prepareStatement(consulta);
		s.setString(1, nombre);
		s.setString(2, contraseña);
		
		ResultSet rs = s.executeQuery();
		
		while(rs.next()) {
			dni=(rs.getString("dni"));
		}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+ consulta);
		}
		return dni;
	}
	//Método para consultar la reserva a eliminar
	public static void consultarReserva(Usuario_Vehiculo usuariovehiculo, Usuario usuario) {
		
		String consulta = "SELECT * FROM usuario_vehiculo WHERE dni = ? AND id_coche = ? and fecha_recogida = ?";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		s.setString(1, usuario.getDni());
		s.setInt(2, usuariovehiculo.getId_coche());
		s.setString(3, usuariovehiculo.getFecha_recogida());
		
		ResultSet rs = s.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString("dni")+" "+ rs.getInt("id_coche")+" "+rs.getDouble("precio_total")+" "+rs.getString("fecha_recogida")+" "+
					rs.getString("fecha_entrega")+" "+rs.getBoolean("conconductor")+" "+rs.getString("lugar_recogida")+" "+
					rs.getString("lugar_entrega"));
		}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
		}
	}
	//Método que devuelve si la reserva existe o no
	public static boolean consultarId_coche(Usuario_Vehiculo usuariovehiculo, Usuario usuario) {
		boolean existe = false;
		String consulta = "SELECT * FROM usuario_vehiculo WHERE id_coche = ?";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setInt(1, usuariovehiculo.getId_coche());
			
			ResultSet rs = s.executeQuery();
			
			if(rs.next()) {
				existe = true;
			}
				
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
		}
		return existe;
	}
	//Método para eliminar la reserva
	public static void eliminarRerserva(Usuario_Vehiculo usuariovehiculo, Usuario usuario) {
		
		String consulta = "DELETE FROM usuario_vehiculo WHERE dni = ? and id_coche = ?";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		s.setString(1,usuario.getDni());
		s.setInt(2, usuariovehiculo.getId_coche());
		
		s.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error "+ consulta);
		}
	}
	
	//Método que devuelva el precio dia del vehículo 
	public static int precioDiaVehiculo(String matricula) {
		int precioDia =0;
		
		String consulta = "SELECT * FROM vehiculo WHERE matricula = ?";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		s.setString(1, matricula);
		
		ResultSet rs= s.executeQuery();
		
		if(rs.next()) {
			   // Determinar qué precio usar según el tipo de vehículo
            if (rs.getInt("precio_monovolumen") > 0) {
                precioDia = rs.getInt("precio_monovolumen");
            } else if (rs.getInt("precio_turismo") > 0) {
                precioDia = rs.getInt("precio_turismo");
            } else if (rs.getInt("precio_furgoneta") > 0) {
                precioDia = rs.getInt("precio_furgoneta");
            }
		}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
		}
		return precioDia;
	}
	//Métod que devuelve un int, el id de la oficina
		public static int obtenerIdOficina(String nombreOficina) {
			int id_oficina=0;
			
			String consulta = "SELECT * FROM oficina WHERE nombre = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, nombreOficina);
				
				ResultSet rs = s.executeQuery();
				while(rs.next()) {
					id_oficina= rs.getInt("id_oficina");
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error " +consulta);
			}
			return id_oficina;
			
		}
		
}
