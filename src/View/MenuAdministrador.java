package View;

import java.util.Scanner;

import Modelo.Vehiculo;
import Repositorios.VehiculoRepositorio;

public class MenuAdministrador {

	//Menú administrador
	public static void menuAdministrador(Scanner sc) {
		
		int opcion=0;
		
		do {
			System.out.println("-----BIENVENIDO AL MENÚ DE ADMINISTRADOR------");
			System.out.println("0.-Salir de la web");
			System.out.println("1.-Añadir vehiculo a la BDD");
			System.out.println("2.-Eliminar vehiculo de la BDD");
			System.out.println("3.-Añadir oficina a la BDD");
			System.out.println("4.-Eliminar Oficina de la BDD");
			System.out.println("5.-Eliminar reserva de la BDD");
			System.out.println("6.-Crear administrador de la BDD");
			System.out.println("7.-Menú usuario");
			
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: break;
			case 1: 
				anadirVehiculo(sc);
				break;
			case 2: 
				//eliminarVehiculo(sc, turismo, monovolumen, furgoneta);
				break;
			case 3: 
				//anadirOficina(sc, oficina);
				break;
			case 4: 
				//eliminarVehiculo(sc, oficina);
				break;
			case 5: 
				//eliminarReserva(sc, Usuario_Vehiculos);
				break;
			case 6:
				//crearAdministrador();
				break;
			case 7:
				MenuUsuario.menuUsuario(sc);
				break;
			default:
				System.out.println("Número erroneo");
				System.out.println("Introduzca un número del 0 al 7 ambos inclusive");
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
				VehiculoRepositorio.insertarVehiculo(v1);
				break;
			case 2: 
				
				Vehiculo v2= new Vehiculo();
				VehiculoRepositorio.insertarVehiculo(v2);
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
