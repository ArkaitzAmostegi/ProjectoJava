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
		    	System.out.println("Error"+e.getMessage());
		    }
		}
	 //Comprobar si es admin o usuario
	 public static void comprobarUsuario() {
		 String consulta = "SELECT count FROM usuario where dni=?, nombre=?, contraseña=?";
		//( Connection con = ConectorBD.getconexion();
	//	 try {
			 //PreparedStatement s=conexion.prepareStatement(consulta);
				//s.setString(1, dni);
				//s.setString(2, nombre);
				//s.setString(6, contraseña);
				//ResultSet rs=s.executeQuery();
				//while(rs.next()) {
				//	System.out.println(rs.getString("nombre")+" "+rs.getString("apellido1")+" "+ rs.getString("apellido2")+", "+rs.getString("fecha_nacimiento")+", curso:"+rs.getInt("curso")+", clase:"+rs.getInt("clase"));
				//}
			//} catch (SQLException e) {
				//e.printStackTrace();
				//System.out.println("Error al hacer la consulta"+ consulta);
			//}
		 
	 }
}