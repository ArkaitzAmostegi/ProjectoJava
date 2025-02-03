package Repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Modelo.Usuario;
import Modelo.Usuario_Vehiculo;

public class RepositorioUsuario {

	//Método para mostrar listado de vehiculos
	public static void mostrarVehiculo() {
		
		String consulta = "SELECT * FROM vehiculo";
		
		 try {PreparedStatement s=ConectorBD.getconexion().prepareStatement(consulta);
			ResultSet rs=s.executeQuery();
			
			System.out.println();
			System.out.println("-----LISTADO DE VEHÍCULOS------");
			System.out.println("MARCA----MODELO----TIPO-----KM");
			System.out.println("-------------------------------");
			while(rs.next()) {
				System.out.println(rs.getString("marca")+" "+ rs.getString("modelo")+" "+ rs.getString("tipo")+" "+ rs.getInt("km"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta"+ consulta);
		}
		 System.out.println();
	}
	
	//Método para mostrar vehículos con la matricula
	public static void mostrarMatriculaVehiculo() {
		String consulta=  "SELECT * FROM vehiculo";
		
		PreparedStatement s;
		try {
			s = ConectorBD.getconexion().prepareStatement(consulta);
			ResultSet rs=s.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("id_coche") + ". " + rs.getString("matricula") + " " + rs.getString("marca") + " " + rs.getString("modelo") + " " + rs.getString("tipo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	 //Método para comprobar que la matricula de un vehiculo se encuentra en la BBDD
	 public static boolean comprobarMatricula(String matricula) {
		 boolean existe = false;
		 
		 String consulta = "SELECT COUNT(*) FROM usuario WHERE matricula=?";
		 
		 PreparedStatement s;
		try {
			s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, matricula);
			
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				int count = rs.getInt(1);
				existe = count > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta " + consulta);
		}
		
		return existe;
	 }
	
	//Método para mostrar listado de oficinas
		public static void mostrarOficina() {
			
			String consulta = "SELECT * FROM oficina";
			
			 try {PreparedStatement s=ConectorBD.getconexion().prepareStatement(consulta);
				ResultSet rs=s.executeQuery();
				
				System.out.println("ESTAS SON NUESTRAS OFICINAS");
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("-------NOMBRE-------------------CALLE----------------------TELÉFONO--------------EMAIL");
				System.out.println("--------------------------------------------------------------------------------------");
	
				while(rs.next()) {
					System.out.println(rs.getString("nombre")+" - "+ rs.getString("calle")+" - "+ rs.getString("telefono")+" - "+ rs.getString("email"));
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al hacer la consulta"+ consulta);
			}
			System.out.println();
		}
		
	//Método para listar los usuarios
		public static void mostrarUsuario() {
			String consulta = "SELECT * FROM usuario";
			
			 try {PreparedStatement s=ConectorBD.getconexion().prepareStatement(consulta);
				ResultSet rs=s.executeQuery();
				
				System.out.println();
				System.out.println("---------LISTADO DE USUARIOS----------");
				System.out.println("---DNI----NOMBRE----TELÉFONO-----EMAIL");
				System.out.println("--------------------------------------");
				
				while(rs.next()) {
					System.out.println(rs.getString("dni")+" - "+ rs.getString("nombre")+" - "+ rs.getString("telefono")+" - "+ rs.getString("email"));
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al hacer la consulta"+ consulta);
			}
			 System.out.println();
		}
		
	//Métodos para actualizar datos del usuario
		public static void modificarDni(String dni, String nombre) {
			String consulta = "UPDATE usuario SET dni = ? WHERE nombre = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, dni);
				s.setString(2, nombre);
				
				s.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void modificarNombre(String dni, String nombreNuevo) {
			String consulta = "UPDATE usuario SET nombre = ? WHERE dni = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, nombreNuevo);
				s.setString(2, dni);
				
				s.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void modificarSexo(String dni, String sexo) {
			String consulta = "UPDATE usuario SET sexo = ? WHERE dni = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, sexo);
				s.setString(2, dni);
				
				s.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void modificarTelefono(String dni, String numTelefono) {
			String consulta = "UPDATE usuario SET telefono = ? WHERE dni = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, numTelefono);
				s.setString(2, dni);
				
				s.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void modificarCorreo(String dni, String correo) {
			String consulta = "UPDATE usuario SET email = ? WHERE dni = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, correo);
				s.setString(2, dni);
				
				s.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void modificarContraseña(String dni, String contraseña) {
			String consulta = "UPDATE usuario SET contraseña = ? WHERE dni = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, contraseña);
				s.setString(2, dni);
				
				s.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Método para que muestre todas los alquileres de vehículos que tiene el usuario
		public static void mostrarListadoVehiculosreservados(Usuario usuario) {
			
			String consulta = "SELECT * FROM usuario_vehiculo NATURAL JOIN vehiculo WHERE dni = ? AND alquilado = 1";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, usuario.getDni());
				
				ResultSet rs= s.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getString("matricula")+" "+rs.getString("marca")+" "+ rs.getString("modelo")+" "+" "+ rs.getInt("km")+" "+ rs.getString("tipo"));
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error "+ consulta);
			}
		}
		//Método que devuelve true si ese usuario puede devolver esa matrícula 
		public static boolean vehiculosReservados(Usuario usuario, String matricula) {
			boolean existe= false;
			String consulta = "SELECT * FROM usuario_vehiculo NATURAL JOIN vehiculo WHERE dni = ? AND alquilado = 1";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, usuario.getDni());
				
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
		
		
		//Método para eliminar la reserva
		public static void eliminarReserva(Usuario usuario, int id_coche) {
			
			String consulta = "DELETE FROM usuario_vehiculo WHERE dni = ? and id_coche = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, usuario.getDni());
				s.setInt(2, id_coche);
				
				s.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error "+ consulta);
			}
		}
		//Método para finalizar el estado alquilado de un vehículo
		public static void finalizarEstadoAlquilado(Usuario usuario, int id_coche) {
			
			String consulta = "UPDATE vehiculo SET administrador = 1 WHERE id_coche = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setInt(1, id_coche);
				
				s.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error "+consulta);
			}
		}
		
		//Método que devuelve si hay vehículos reservados o no
		public static boolean tieneVehiculosReservados(Usuario usuario) {
			boolean existe = false;
			String consulta = "SELECT count(*) FROM usuario_vehiculo NATURAL JOIN vehiculo WHERE dni = ? AND alquilado = 1";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(1, usuario.getDni());
				
				ResultSet rs= s.executeQuery();
				
				if(rs.next()) {
					int cantidad = rs.getInt(1); //Obtiene el valor de count
					return cantidad > 0; // Devuelve true si hay al menos 1 alquiler
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error "+ consulta);
			}
			
			return false;
		}
		
}
