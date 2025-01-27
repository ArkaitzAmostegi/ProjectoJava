package View;

import java.util.Scanner;

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
				anadirVehiculo(sc);
				break;
			case 2: 
				//eliminarVehiculo(sc);
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

	//Menú para añadir vehiculos
	private static void anadirVehiculo(Scanner sc) {
		
		int opcion=0;
		do {
			System.out.println("-----BIENVENIDO AL MENÚ DE ADMINISTRADOR------");
			System.out.println("--------AÑADIR VEHICULO-------");
			System.out.println("0.-Salir de la web");
			System.out.println("1.-Añadir furgoneta a la BDD");
			System.out.println("2.-Añadir monovolumen a la BDD");
			System.out.println("3.-Añadir turismo a la BDD");
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: break;
			case 1: 
				//System.out.println("Introduce el tamano");
				//String tamano=sc.nextLine();
				Vehiculo v1= new Vehiculo();
				RepositorioVehiculo.insertarVehiculo(v1);
				break;
			case 2: 
				
				Vehiculo v2= new Vehiculo();
				RepositorioVehiculo.insertarVehiculo(v2);
				break;
			case 3: 
				//VehiculoRepositorio.insertarVehiculo(turismo);
				break;
			default:
				System.out.println("Número erroneo");
				System.out.println("Introduzca un número del 0 al 3 ambos inclusive");
			}
		}
		while (opcion != 0);
		
	}
}
