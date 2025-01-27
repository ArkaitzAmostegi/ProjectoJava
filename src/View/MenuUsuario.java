package View;

import java.util.Scanner;

import Modelo.Vehiculo;
import Repositorios.RepositorioUsuario;

public class MenuUsuario {

	//Menú usuario 
	public static void menuUsuario(Scanner sc, String nombre) {
		
		int opcion=0;
		do {
			System.out.println("-----BIENVENIDO A NUESTRA WEB------");
			System.out.println("--------------"+ nombre +"---------------");
			System.out.println("-----------MENÚ USUARIO----------");
			System.out.println("0.-Salir de la web");
			System.out.println("1.-Nuestra flota de vehículos");
			System.out.println("2.-Donde disponemos de oficinas");
			System.out.println("3.-Realizar una reserva");
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
		
		System.out.println("A continuación le mostramos nuestras oficinas, para que elija desde cual quiere realizar la reserva");
		RepositorioUsuario.mostrarOficina(); //mostrar listado de oficinas
		System.out.println();
		MenuReserva.elegirOficina(sc); //elegir oficina
		System.out.println();
		MenuReserva.elegirVehiculo(sc); //elegir vehiculo
		MenuReserva.validarReserva(sc); //validamos el vehículo y la oficina seleccionados
	}

	//Muestra todas las oficinas
	private static void mirarOficina() {
		System.out.println("ESTAS SON NUESTRAS OFICINAS");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("-------Nombre-------------------Calle----------------------Teléfono--------------Email");
		System.out.println("--------------------------------------------------------------------------------------");
		RepositorioUsuario.mostrarOficina(); //Muestra las oficinas
		System.out.println();
	}
	
	//Muestra todos los vehículos
	private static void mirarVehiculo() {
		System.out.println();
		System.out.println("ESTOS SON NUESTROS VEHÍCULOS");
		System.out.println("-----------------------");
		System.out.println("Marca--Modelo--Tipo--Km");
		System.out.println("-----------------------");
		RepositorioUsuario.mostrarVehiculo(); //Muestra los vehículos
		System.out.println();
	}
}
