package View;

import java.util.Scanner;

import Modelo.Furgoneta;
import Modelo.Monovolumen;
import Modelo.Tamano;
import Modelo.Turismo;
import Modelo.Vehiculo;
import Repositorios.RepositorioVehiculo;

public class MenuAdministrador {

	//Menú administrador
	public static void menuAdministrador(Scanner sc, String nombre) {
		
		int opcion=0;
		
		do {
			System.out.println("-----BIENVENIDO AL MENÚ DE ADMINISTRADOR------");
			System.out.println("--------------- "+ nombre +" ---------------");
			System.out.println("0.-Salir de la web");
			System.out.println("1.-Añadir vehiculo a la BDD");
			System.out.println("2.-Eliminar vehiculo de la BDD");
			System.out.println("3.-Añadir oficina a la BDD");
			System.out.println("4.-Eliminar Oficina de la BDD");
			System.out.println("5.-Eliminar reserva de la BDD");
			System.out.println("6.-Cambiar km a los vehiculos");
			System.out.println("7.-Crear administrador de la BDD");
			System.out.println("8.-Menú usuario");
			
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: break;
			case 1: 
				MenuAnadirVehiculo.anadirVehiculo(sc);
				break;
			case 2: 
				eliminarVehiculo(sc);
				break;
			case 3: 
				//anadirOficina(sc);
				break;
			case 4: 
				//eliminarOficina(sc);
				break;
			case 5: 
				//eliminarReserva(sc);
				break;
			case 6:
				//cambiarKm(sc);
				break;
			case 7:
				//crearAdministrador(sc);
				break;
			case 8:
				MenuUsuario.menuUsuario(sc, nombre);
				break;
			default:
				System.out.println("Número erroneo");
				System.out.println("Introduzca un número del 0 al 8 ambos inclusive");
			}
		}
		while (opcion != 0);
		System.out.println("Ha salido de nuestra web");
	}
	
	//Método para eliminar vehículos
	private static void eliminarVehiculo(Scanner sc) {
		
		Vehiculo v = new Vehiculo();
		
		System.out.println("Introduce la  matricula del vehiculo que desea eliminar: ");
		String matricula = sc.nextLine();
		
		System.out.println("El vehículo que desea eliminar, es el: ");
		RepositorioVehiculo.consultarMatricula(matricula);
		
		System.out.println("Si es así, introduce 'SI'. Si no introduce 'NO'");
		String opcion = sc.nextLine().trim();
		
		if (opcion.equalsIgnoreCase("si")) {
			RepositorioVehiculo.eliminarVehiculo(matricula);
			System.out.println("Vehículo eliminado de nuestra base de datos");
		}
	}
	
}
