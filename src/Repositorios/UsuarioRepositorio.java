package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelo.Usuario;

public class UsuarioRepositorio {
	
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
	 //Comprobar si es admin o usuario
	 public static boolean comprobarUsuario(String nombre, String contraseña) {
		 boolean existe = false;
		 String consulta = "SELECT count(*) FROM usuario where dni=?, nombre=?, contraseña=?";
		 Connection con = ConectorBD.getconexion();
		 try {
			 PreparedStatement s=ConectorBD.getconexion().prepareStatement(consulta);
				s.setString(2, nombre);
				s.setString(6, contraseña);
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
}