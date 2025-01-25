package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Modelo.Usuario;

public class LoginRepositorio {
	
	//Método para añadir usuario a la BDD
	 public static void crearUsuario(Usuario usuario){
			
			String consulta = "INSERT INTO usuario (dni, nombre, sexo, telefono, email, contraseña, administrador) VALUES (?, ?, ?, ?, ?, ?, ?)";
			Connection con=ConectorBD.getconexion();
		
			try (PreparedStatement preparedStatement = con.prepareStatement(consulta)) {
				preparedStatement.setString(1, usuario.getDni());
				preparedStatement.setString(2, usuario.getNombre());
				preparedStatement.setString(3,usuario.getSexo());
				preparedStatement.setInt(4, usuario.getTelefono());
				preparedStatement.setString(5, usuario.getEmail());
				preparedStatement.setString(6, usuario.getContrasena());
				preparedStatement.setBoolean(7, usuario.isAdministrador());
		       
		        
		        preparedStatement.executeUpdate();
		    }catch(Exception e) {
		    	System.out.println("Error "+e.getMessage());
		    }
		}
	 //Método para Comprobar si está registrado
	 public static boolean comprobarUsuario(String nombre, String contraseña) {
		 boolean existe = false;
		 
		 //La consulta busca que nos de 1 (es decir que sea igual al nombre y la contraseña). Lo que significa que ese nombre y contraseña están en la BDD
		
		 String consulta = "SELECT count(*) FROM usuario WHERE nombre=? AND contraseña=?";
		 Connection con = ConectorBD.getconexion();
		 try {PreparedStatement s=con.prepareStatement(consulta);
				s.setString(1, nombre);
				s.setString(2, contraseña);
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
	 //Método para comprobar si el usuario es admin o no
	 public static boolean comprobarAdmin(String nombre, String contraseña) {
		 boolean admin=false;
		 //El administrador es 1 ya que así, en el Método login(Scanner sc, Usuario usuario) si es admin lo llevamos a menú admin, sino va a menú usuario
		String consulta="SELECT count(*) FROM usuario WHERE nombre=? and contraseña=? and administrador = 1";
		try {PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setString(1, nombre);
			s.setString(2, contraseña);
			ResultSet rs= s.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				admin = count > 0;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al hacer la consulta"+ consulta);
		}
		 return admin;
	 }
}