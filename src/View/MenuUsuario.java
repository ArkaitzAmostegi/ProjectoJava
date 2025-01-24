package View;

import java.util.Scanner;

public class MenuUsuario {

	//Menú usuario 
	public static void menuUsuario(Scanner sc) {
		
		int opcion=0;
		do {
			System.out.println("-----BIENVENIDO A NUESTRA WEB------");
			System.out.println("-----------MENÚ USUARIO----------");
			System.out.println("0.-Salir de la web");
			System.out.println("1.-Seleccionar vehiculo");
			System.out.println("2.-Realizar la reserva");
			System.out.println("3.-Realizar el pago");
			System.out.println("4.-Modificar los datos del usuario");
			
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: break;
			case 1: 
				//seleccionarVehiculo(sc, vehiculos);
				break;
			case 2: 
				//hacerReserva(sc, Usuario_Vehiculos);
				break;
			case 3: 
				//realizarPago(sc, Usuario_vehiculos);;
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
}
