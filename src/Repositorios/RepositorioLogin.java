package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Modelo.Usuario;

public class RepositorioLogin {
	
	//Método para añadir usuario a la BDD
	 public static void crearUsuario(Usuario usuario){
			
			String consulta = "INSERT INTO usuario (dni, nombre, sexo, telefono, email, contraseña, administrador) VALUES (?, ?, ?, ?, ?, ?, ?)";
			Connection con=ConectorBD.getconexion();
		
			try (PreparedStatement preparedStatement = con.prepareStatement(consulta)) {
				preparedStatement.setString(1, usuario.getDni());
				preparedStatement.setString(2, usuario.getNombre());
				preparedStatement.setString(3,usuario.getSexo());
				preparedStatement.setString(4, usuario.getTelefono());
				preparedStatement.setString(5, usuario.getEmail());
				preparedStatement.setString(6, usuario.getContrasena());
				preparedStatement.setBoolean(7, usuario.isAdministrador());
		       
		        
		        preparedStatement.executeUpdate();
		    }catch(Exception e) {
		    	System.out.println("Error "+e.getMessage());
		    }
		}
	 //Método para Comprobar si está registrado
	 public static boolean comprobarUsuario(Usuario usuario) {
		 boolean existe = false;
		 
		 //La consulta busca que nos de 1 (es decir que sea igual al nombre y la contraseña). Lo que significa que ese nombre y contraseña están en la BDD
		
		 String consulta = "SELECT count(*) FROM usuario WHERE nombre=? AND contraseña=?";
		 Connection con = ConectorBD.getconexion();
		 try {PreparedStatement s=con.prepareStatement(consulta);
				s.setString(1, usuario.getNombre());
				s.setString(2, usuario.getContrasena());
				ResultSet rs=s.executeQuery();
				
				if(rs.next()) {
					int count = rs.getInt(1);
					existe = count > 0;
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al hacer la consulta"+ consulta);
			}
		 return existe;
	 }
	 
	 //Método para comprobar que la contraseña introducida sea correcta
	 public static boolean comprobarContraseña(String contraseña) {
		 boolean existe = false;
		 
		 String consulta = "SELECT COUNT(*) FROM usuario WHERE contraseña=?";
		 
		 Connection con = ConectorBD.getconexion();
		 
		 try {
			PreparedStatement s = con.prepareStatement(consulta);
			s.setString(1, contraseña);
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				int count = rs.getInt(1);
				existe = count > 0;
			}
			
		 } catch (SQLException e) {
			System.out.println("Error al hacer la consulta " + consulta);
		 }
		 return existe;
	 }
	 
	 //Método para comprobar que el DNI se encuentra en la BBDD
	 public static boolean comprobarDni(String dni) {
		 boolean existe = false;
		 
		 String consulta = "SELECT COUNT(*) FROM usuario WHERE dni=?";
		 
		 PreparedStatement s;
		try {
			s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, dni);
			
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
	 
	 //Método para comprobar si el usuario es admin o no
	 public static boolean comprobarAdmin(Usuario usuario) {
		 boolean admin=false;
		 //El administrador es 1 ya que así, en el Método login(Scanner sc, Usuario usuario) si es admin lo llevamos a menú admin, sino va a menú usuario
		String consulta="SELECT count(*) FROM usuario WHERE nombre=? and contraseña=? and administrador = 1";
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, usuario.getNombre());
			s.setString(2, usuario.getContrasena());
			ResultSet rs= s.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				admin = count > 0;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta " + consulta);
		}
		 return admin;
	 }
	 //Obtener DnI del usuario
	public static String obtenerDni(Usuario usuario) {
		String dni=" ";
		
		String consulta = "SELECT * FROM usuario WHERE nombre = ? AND contraseña = ?";
		
		try { PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, usuario.getNombre());
			s.setString(2, usuario.getContrasena());
			
			ResultSet rs= s.executeQuery();
			if(rs.next()) {
				dni= rs.getString("dni");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+ consulta);
		}
		return dni;
	}
	//Comprobar el email saber si ya está en la BDD o no
	public static boolean comprobaremail(String email) {
		
		boolean existe = false;
		
		String consulta = "SELECT * FROM usuario WHERE email = ?";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		s.setString(1, email);
		
		ResultSet rs= s.executeQuery();
		
		if(rs.next()) {
			existe = true;
		}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
		}		
		return existe;
	}
	//Método que devuelve el nombre por medio del DNI
	public static String devolverNombre(String dni) {
		
		String nombre = "";
		
		String consulta = "SELECT nombre FROM USUARIO WHERE dni = ?";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, dni);
			
			ResultSet rs = s.executeQuery();
			
			while (rs.next()) {
				nombre = rs.getString("nombre");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
		}
		return nombre;
	}
	
	//Método para comprobar si el telefono está ya en la BDD
	public static boolean comprobarTelefonoOficina(String telefono) {
		
		boolean existe= false;
		String consulta = "SELECT telefono FROM oficina WHERE telefono = ?";
		
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
		
			s.setString(1, telefono);
		
			ResultSet rs= s.executeQuery();
		
			if(rs.next()) {
				existe = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error "+consulta);
		}
		return existe;
	}
	
	//Método para comprobar si el telefono está ya en la BDD
		public static boolean comprobarTelefonoUsuario(String telefono) {
			
			boolean existe= false;
			String consulta = "SELECT telefono FROM usuario WHERE telefono = ?";
			
			try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			
				s.setString(1, telefono);
			
				ResultSet rs= s.executeQuery();
			
				if(rs.next()) {
					existe = true;
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Error "+consulta);
			}
			return existe;
		}
}