package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Modelo.Furgoneta;
import Modelo.Monovolumen;
import Modelo.Turismo;
import java.sql.SQLException;
import java.sql.Statement;

import Modelo.Vehiculo;

public class RepositorioVehiculo {

	 private static Statement statement;

	//Método para insertar un vehiculo
	 public static void insertarVehiculo(Vehiculo vehiculo){
			
		String consulta = "INSERT INTO vehiculo (id_coche, id_oficina, matricula, marca, modelo, km, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection com=ConectorBD.getconexion();
	
		try (PreparedStatement preparedStatement = com.prepareStatement(consulta)) {
			preparedStatement.setInt(1, vehiculo.getId_coche());
			preparedStatement.setInt(2, vehiculo.getId_oficina());
			preparedStatement.setString(3, vehiculo.getMatricula());
			preparedStatement.setString(4, vehiculo.getMarca());
			preparedStatement.setString(5, vehiculo.getModelo());
			preparedStatement.setInt(6, vehiculo.getKm());
			preparedStatement.setString(7, vehiculo.gettipo());
	       
	        preparedStatement.executeUpdate();
	        
	    }catch(Exception e) {
	    	System.out.println("Error"+e.getMessage());
	    }
	}
	 
	 //Método para Añadir furgoneta
	 public static void insertarFurgoneta(Furgoneta furgo) {
		 
		 String consulta = "INSERT INTO vehiculo (id_coche, matricula, marca, modelo, km, tipo, tamaño, precio_furgoneta, id_oficina) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 
		 try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
			 s.setInt(1, furgo.getId_coche());
			 s.setString(2, furgo.getMatricula());
			 s.setString(3, furgo.getMarca());
			 s.setString(4, furgo.getModelo());
			 s.setInt(5, furgo.getKm());
			 s.setString(6, furgo.gettipo());
			 s.setString(7, furgo.getTamano().toString());
			 s.setDouble(8, furgo.getPrecio_furgoneta());
			 s.setInt(9, furgo.getId_oficina());
			 
			 s.executeUpdate();	 
			 
		 }catch(Exception e) {
		    	System.out.println("Error "+e.getMessage());
		    }
	 }
	 //Método para Añadir Monovolumen
	public static void insertarMonovolumen(Monovolumen mono) {
		
		String consulta = "INSERT INTO vehiculo (id_coche, matricula, marca, modelo, km, tipo, num_puertas, precio_monovolumen, id_oficina) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 
		 try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
			 s.setInt(1, mono.getId_coche());
			 s.setString(2, mono.getMatricula());
			 s.setString(3, mono.getMarca());
			 s.setString(4, mono.getModelo());
			 s.setInt(5, mono.getKm());
			 s.setString(6, mono.gettipo());
			 s.setInt(7, mono.getNumPuertas());
			 s.setDouble(8, mono.getPrecio_monovolumen());
			 s.setInt(9,  mono.getId_oficina());
			 
			 s.executeUpdate();
			 
		 }catch(Exception e) {
		    	System.out.println("Error "+e.getMessage());
		    }
	}
	//Método para Añadir turismo
	public static void insertarTurismo(Turismo turis) {
		String consulta = "INSERT INTO vehiculo (id_coche, matricula, marca, modelo, km, tipo, potencia, precio_turismo, id_oficina) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 
		 try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
			 s.setInt(1, turis.getId_coche());
			 s.setString(2, turis.getMatricula());
			 s.setString(3, turis.getMarca());
			 s.setString(4, turis.getModelo());
			 s.setInt(5, turis.getKm());
			 s.setString(6, turis.gettipo());
			 s.setInt(7, turis.getPotencia());
			 s.setDouble(8, turis.getPrecio_turismo());
			 s.setInt(9, turis.getId_oficina());
			 
			 s.executeUpdate();
			 
		 }catch(Exception e) {
		    	System.out.println("Error "+e.getMessage());
		    }
	}
	
	//Método para Consultar vehículo
	public static void consultarMatricula(String matricula) {
		
		String consulta = "SELECT * FROM vehiculo WHERE matricula = ?";
		
		try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
			s.setString(1, matricula);
			ResultSet rs= s.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("matricula")+" "+rs.getString("marca")+" "+ rs.getString("modelo")+" "+ rs.getInt("km")+" "+ rs.getString("tipo"));
			}
			
		}catch(Exception e) {
	    	System.out.println("Error "+e.getMessage());
	    }
	}
	//Método para Devuelve booleano, Consultar vehículo para seber si esa matrícula existe 
		public static boolean existeMatricula(String matricula) {
			
			boolean existe = false;
			String consulta = "SELECT COUNT(*) FROM vehiculo WHERE matricula = ?";
			
			try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)){
				s.setString(1, matricula);
				ResultSet rs= s.executeQuery();
				
				if (rs.next()) {
					int cantidad = rs.getInt(1); //Obtiene el valor de count
					return cantidad > 0; // Devuelve true si hay al menos 1 alquiler
				}
				
			}catch(Exception e) {
		    	System.out.println("Error "+e.getMessage());
		    }
			return false;
		}
	
	//Método para Eliminar vehiculo
	public static void eliminarVehiculo(String matricula) {
		
	    String consulta = "DELETE FROM vehiculo WHERE matricula = ?";

	    try (PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta)) {
	        s.setString(1, matricula); // Asigna el valor de la matrícula al parámetro de la consulta

	        int filasAfectadas = s.executeUpdate(); // Ejecuta la consulta y obtiene el número de filas afectadas

	        if (filasAfectadas > 0) {
	            System.out.println("El vehículo con matrícula " + matricula + " ha sido eliminado.");
	        } else {
	            System.out.println("No se encontró ningún vehículo con la matrícula " + matricula + ".");
	        }
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}
	
	
	//Método para modificar KMs de un vehículo
	public static void cambiarKilometros(int km, String matricula) {
		String consulta = "UPDATE vehiculo SET km = ? WHERE matricula = ?";
		
		try {
			PreparedStatement s = ConectorBD.getconexion().prepareStatement(consulta);
			s.setInt(1, km);
			s.setString(2, matricula);
			s.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
