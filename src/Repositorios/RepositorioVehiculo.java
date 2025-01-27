package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Modelo.Vehiculo;

public class RepositorioVehiculo {

	 //Método para insertar un alumno
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
	 
	 //Método para mostrar vehículos
	 public static void mostrarVehiculo(Vehiculo vehiculo) {
		 String consulta = "SELECT * FROM vehiculo";
		 
		Statement statement;
		ResultSet rs = statement.executeQuery(consulta){
			while (rs.next()) {
				System.out.println("Id: " + rs.getString("ID_COCHE") + ", Matricula: " + rs.getString("MATRICULA") + ", Marca: " + rs.getString("MARCA") + ", Modelo: " + rs.getString("MODELO") + ", KM: " + rs.getNString("KM") + ", Tipo: " + rs.getString("TIPO") + ", Número de puertas: " + rs.getString("NUM_PUERTAS") + ", Potencia: " + rs.getString("POTENCIA") + ", Tamaño: " + rs.getString("TAMAÑO") + ", Id_Oficina: " + rs.getString("ID_OFICINA"));
			}
		}
	 }
}
