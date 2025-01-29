package Repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	//Método para mostrar el vehiculo y la oficina elegidos
	public static void vehiculoSeleccionado(String matricula) {
		
		String consulta = "SELECT * FROM vehiculo v NATURAL JOIN oficina o WHERE matricula = ?";
		
		try {
			PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, matricula);
			ResultSet rs=s.executeQuery();
			
			while(rs.next()) {
				System.out.println("Oficina: "+rs.getString("o.nombre")+"\nVehiculo: "+rs.getString("v.matricula")+" "+rs.getString("v.marca")+" "+ rs.getString("v.modelo")+" "+" "+ rs.getInt("v.km")+" "+ rs.getString("v.tipo"));
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
	
	//Método para comprobar si el vehículo de esa oficina está libre esa fecha
	public static boolean comprobarFecha(String matricula, String fecha_recogida, String fecha_entrega) {
		boolean existe= false;
		
		String consulta = "SELECT * FROM usuario_vehiculo NATURAL JOIN vehiculo WHERE matricula = ? AND fecha_entrega = ? AND fecha_recogida = ? ";
		
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
}
