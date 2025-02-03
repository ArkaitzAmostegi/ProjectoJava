package Repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Usuario;
import Modelo.Usuario_Vehiculo;
import Modelo.Vehiculo;


public class RepositorioReserva {

	
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
	//Método para comprobar si el vehículo de esa oficina está libre esa fecha
		public static boolean comprobarFecha(String matricula, String fecha_recogida, String fecha_entrega) {
			boolean existe= false;
			
			String consulta = "SELECT * FROM usuario_vehiculo NATURAL JOIN vehiculo WHERE matricula = ? AND fecha_entrega = ? AND fecha_recogida = ? AND alquilado = 0";
			
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
	//Método para mostrar que vehículos tiene libre esa oficina
	public static void vehiculoLibre(String nombreOficina) {
		
		String consulta ="SELECT * FROM vehiculo NATURAL JOIN oficina WHERE nombre = ? AND alquilado = 0";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		s.setString(1, nombreOficina);
		
		ResultSet rs = s.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString("matricula")+" "+rs.getString("marca")+" "+rs.getString("modelo")+" "+rs.getInt("km"));
		}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
		}
	}
		
	//Método para comprobar la oficina
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
			
			while(rs.next()) {
				System.out.println("Oficina: "+rs.getString("o.nombre")+"\nVehiculo: "+rs.getString("v.matricula")+" "+rs.getString("v.marca")+" "+ rs.getString("v.modelo")+" "+" "+ rs.getInt("v.km")
				+" "+ rs.getString("v.tipo")+" "+rs.getInt("precio_monovolumen")+" "+rs.getString("precio_turismo")+" "+rs.getString("precio_furgoneta"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta "+ consulta);
		}
	}
	
	//Método para comprobar la matrícula
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
	
	//Métod para sacar el id del vehículo
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
	//Método para obtener los datos del usuario
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
		
		String consulta = "SELECT * FROM usuario_vehiculo WHERE dni = ? AND id_coche = ?";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		s.setString(1, usuario.getDni());
		s.setInt(2, usuariovehiculo.getId_coche());
		
		ResultSet rs = s.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString("dni")+" "+ rs.getInt("id_coche")+" "+rs.getDouble("precio_total")+" "+rs.getString("fecha_recogida")+" "+
					rs.getString("fecha_entrega")+" "+rs.getBoolean("conconductor")+" "+rs.getString("lugar_recogida")+" "+
					rs.getString("lugar_entrega")+" "+rs.getBoolean("alquilado"));
		}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
		}
		
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
	//Método para activar estado alquilado al vehículo
	public static void activarAlquilado(String matricula) {
		
		String consulta ="UPDATE vehiculo SET alquilado = ? WHERE matricula = ?";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		s.setBoolean(1, true);
		s.setString(2, matricula);
		
		s.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
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
}
