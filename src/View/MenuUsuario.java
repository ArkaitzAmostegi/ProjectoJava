package View;

import java.util.Scanner;

import Modelo.Vehiculo;
import Repositorios.UsuarioRepositorio;

public class MenuUsuario {

	//Menú usuario 
	public static void menuUsuario(Scanner sc) {
		
		int opcion=0;
		do {
			System.out.println("-----BIENVENIDO A NUESTRA WEB------");
			System.out.println("-----------MENÚ USUARIO----------");
			System.out.println("0.-Salir de la web");
			System.out.println("1.-Mirar nuestra flota de vehiculos");
			System.out.println("2.-Mirar nuestras oficinas");
			System.out.println("3.-Realizar la reserva");
			System.out.println("4.-Modificar sus datos de usuario");
			
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: break;
			case 1: 
				mirarVehiculo();
				break;
			case 2: 
				mirarOficina();
				break;
			case 3: 
				hacerReserva(sc);
				break;				
			case 4: 
				//modificarDatos(sc, usuario);
				break;
			default:
				System.out.println("Número erroneo");
				System.out.println("Introduzca un número del 0 al 4 ambos inclusive");
			}
		}
		while (opcion != 0);
		System.out.println("Ha salido de nuestra web");
	}
	
	private static void hacerReserva(Scanner sc) {
		
		System.out.println("A continuación le mostramos nuestras oficinas, para que elija desde cual quiere hacer una reserva");
		UsuarioRepositorio.mostrarOficina();
		System.out.println("Introduzca el nombre desde la oficina que desee realizar la reserva");
		System.out.println("Y le mostraremos los coches disponibles en esa oficina");
		String nombreOficina= sc.nextLine();
		//Mostrará los vehiculos dependiendo de que oficina haya eligido el usuario
		System.out.println("Esta oficina dispone de estos vehiculos: ");
		System.out.println("-----------------------");
		System.out.println("Marca--Modelo--Km---Tipo---Nº puertas--potencia--tamaño");
		UsuarioRepositorio.mosrtarVehiculoOficina(nombreOficina);
	}

	//Muestra todas las oficinas
	private static void mirarOficina() {
		System.out.println("------------------------------------------------------");
		System.out.println("Nombre----------Calle----------Teléfono----------Email");
		System.out.println("------------------------------------------------------");
		UsuarioRepositorio.mostrarOficina();
	}
	
	//Muestra todos los vehículos
	private static void mirarVehiculo() {
		System.out.println("-----------------------");
		System.out.println("Marca--Modelo--Tipo--Km");
		System.out.println("-----------------------");
		UsuarioRepositorio.mostrarVehiculo();
	}
}
