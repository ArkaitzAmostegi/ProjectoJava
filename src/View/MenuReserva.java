package View;

import java.util.Scanner;

import Repositorios.RepositorioReserva;
import Repositorios.RepositorioUsuario;

public class MenuReserva {

	//Método para elegir la oficina usado en el menuUsuario
	public static void elegirOficina(Scanner sc){
		
		System.out.println("Introduzca el nombre desde la oficina que desee realizar la reserva");
		System.out.println("Y le mostraremos los coches disponibles en esa oficina");
		String nombreOficina= sc.nextLine().trim();//trim()elimina los espacios al inicio y al final
		//Mostrará los vehiculos dependiendo de que oficina haya eligido el usuario
		System.out.println("Esta oficina dispone de estos vehiculos: ");
		System.out.println("-----------------------");
		System.out.println("Matricula--Marca--Modelo--Km---Tipo---Nº puertas--potencia--tamaño");
		RepositorioReserva.mostrarVehiculoOficina(nombreOficina); 
		
	}
	
	//Método para elegir el vehiculo para la reserva
	public static void elegirVehiculo (Scanner sc) {
		
		
		while (true) {
			System.out.println("Introduzca la matricula del vehiculo elegido");
			String matricula = sc.nextLine().trim();
			//comprobarMatricula(); para saber si está bien escrita y en esa oficina
			
			//Hace la consulta del vehículo para que escriba el modelo con todas sus caráteristicas
			System.out.println("Usted a elegido el vehiculo: \n");
			RepositorioReserva.vehiculoSeleccionado(matricula);
			System.out.println("Si es así escriba 'SI', sino escriba 'NO'");
			String opcion = sc.nextLine().toUpperCase();
			
			if (opcion.equals("SI")) {
				//Seguir con el proceso
				System.out.println("Elegido si");
				break;
			}else if (opcion.equals("NO")) {
				System.out.println("Elegido no");
			} else {
				// comenzar el proceso de nuevo
				
			}
				
		}
		
	}
}

