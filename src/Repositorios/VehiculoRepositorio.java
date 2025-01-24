package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;


import Modelo.Vehiculo;

public class VehiculoRepositorio {

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
}
