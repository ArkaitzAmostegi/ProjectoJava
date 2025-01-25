package View;

import java.util.Scanner;

import Modelo.Vehiculo;

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
			
			Modelo.Vehiculo vehiculo = new Modelo.Vehiculo();
			
			switch (opcion) {
			case 0: break;
			case 1: 
				mirarVehiculo(sc, vehiculo);
				break;
			case 2: 
				//mirarOficinas();
				break;
			case 3: 
				//hacerReserva(sc, Usuario_Vehiculo);
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

	private static void mirarVehiculo(Scanner sc, Vehiculo vehiculo) {
		
		
	}
}
