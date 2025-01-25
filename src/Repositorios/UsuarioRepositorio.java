package Repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepositorio {

	//Método para mostrar listado de vehiculos
	public static void mostrarVehiculo() {
		
		String consulta = "SELECT * FROM vehiculo";
		
		 try {PreparedStatement s=ConectorBD.getconexion().prepareStatement(consulta);
			ResultSet rs=s.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("marca")+" "+ rs.getString("modelo")+" "+ rs.getString("tipo")+" "+ rs.getInt("km"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta"+ consulta);
		}
	}
	//Método para mostrar listado de oficinas
		public static void mostrarOficina() {
			
			String consulta = "SELECT * FROM oficina";
			
			 try {PreparedStatement s=ConectorBD.getconexion().prepareStatement(consulta);
				ResultSet rs=s.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getString("nombre")+" - "+ rs.getString("calle")+" - "+ rs.getString("telefono")+" - "+ rs.getString("email"));
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al hacer la consulta"+ consulta);
			}
		}
	//Método para mostrar vehiculos en según la oficina
		public static void mosrtarVehiculoOficina(String nombreOficina) {
			
			String consulta = "SELECT marca, modelo, km, tipo, num_puertas, potencia, tamaño " +
	                  "FROM vehiculo NATURAL JOIN oficina " +
	                  "WHERE nombre = ?";

					
			 try {PreparedStatement s=ConectorBD.getconexion().prepareStatement(consulta);
			 	s.setString(1, nombreOficina);
				ResultSet rs=s.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getString("marca")+" "+ rs.getString("modelo")+" "+" "+ rs.getInt("km")+" "+ rs.getString("tipo")+" "+ rs.getInt("num_puertas")+" "+ rs.getInt("potencia")+" "+ rs.getString("tamaño"));
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al hacer la consulta"+ consulta);
			}
		}
}
