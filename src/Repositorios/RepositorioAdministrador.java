package Repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Modelo.Oficina;

public class RepositorioAdministrador {

	//Método para añadir oficina
	public static void anadirOficina(Oficina oficina) {
		
		String consulta = "INSERT INTO oficina (calle, ciudad, pais, nombre, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
			
			s.setString(1, oficina.getCalle());
			s.setString(2, oficina.getCiudad());
			s.setString(3, oficina.getPais());
			s.setString(4, oficina.getNombre());
			s.setInt(5, oficina.getTelefono());
			s.setString(6, oficina.getEmail());
			
			s.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		System.out.println("Oficina añadida a la base de datos");
	}
	//Método para consultar usuario
	public static void consultarUsuario(String nombre) {
		String consulta = "SELECT * FROM usuario WHERE nombre = ?";
		
		try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
			
			s.setString(1, nombre);
			ResultSet rs= s.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("nombre")+" "+ rs.getString("dni")+" "+ rs.getString("telefono"));
			}
			
		}catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	}
	//Método para consultar oficina
	public static void consultaOficina(String nombre) {
		
		String consulta = "SELECT * FROM oficina WHERE nombre = ?";
		
		try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
			
			s.setString(1, nombre);
			ResultSet rs= s.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("nombre")+" "+ rs.getString("pais")+" "+ rs.getString("ciudad"));
			}
			
		}catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	}
	//Método eliminar oficina
	public static void eliminarOficina(String nombre) {
		
		String consulta = "DELETE FROM oficina WHERE nombre = ?";
		
		try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
			 s.setString(1, nombre); // Asigna el valor del nombre al parámetro de la consulta
		
			 int filasAfectadas = s.executeUpdate(); // Ejecuta la consulta y obtiene el número de filas afectadas

		     if (filasAfectadas > 0) {
		           System.out.println("La oficina " + nombre + " ha sido eliminada.");
		      } else {
		           System.out.println("No se encontró ningúna otra oficina con nombre: " + nombre + ".");
		      }
			
		}catch(Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	}
	//Método para eliminar usuario
	public static void eliminarUsuario(String nombre) {
		String consulta = "DELETE FROM usuario WHERE nombre = ?";
		
		try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
			 s.setString(1, nombre); // Asigna el valor del nombre al parámetro de la consulta
		
			 int filasAfectadas = s.executeUpdate(); // Ejecuta la consulta y obtiene el número de filas afectadas

		     if (filasAfectadas > 0) {
		           System.out.println("El usuario " + nombre + " ha sido eliminado.");
		      } else {
		           System.out.println("No se encontró ningúna otro usuario con nombre: " + nombre + ".");
		      }
			
		}catch(Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	}
}
