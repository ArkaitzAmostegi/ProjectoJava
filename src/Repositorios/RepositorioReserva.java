package Repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioReserva {

	
	//Método para mostrar vehiculos en según la oficina
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
	
	//Método para mostrar el vehiculo y la oficina elegidos
	public static void vehiculoSeleccionado(String matricula) {
		
		String consulta = "SELECT * FROM vehiculo v NATURAL JOIN oficina o WHERE matricula=?";
		
		try {
			PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, matricula);
			ResultSet rs=s.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("v.matricula")+" "+rs.getString("v.marca")+" "+ rs.getString("v.modelo")+" "+" "+ rs.getInt("v.km")+" "+ rs.getString("v.tipo")+" "+ rs.getString("o.nombre"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta "+ consulta);
		}
	}
}
