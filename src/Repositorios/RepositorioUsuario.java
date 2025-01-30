package Repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Modelo.Usuario;

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
		
		public static void modificarTelefono(String dni, int numTelefono) {
			String consulta = "UPDATE usuario SET telefono = ? WHERE dni = ?";
			
			try {
				PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
				s.setInt(1, numTelefono);
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
}
