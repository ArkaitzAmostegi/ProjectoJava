package View;

import java.util.Scanner;

import Modelo.Vehiculo;
import Repositorios.RepositorioUsuario;
import Repositorios.RepositorioVehiculo;

public class MenuAdministrador {

	//Menú administrador
	public static void menuAdministrador(Scanner sc, String nombre) {
		
		int opcion=0;
		
		do {
			System.out.println("-----BIENVENIDO AL MENÚ DE ADMINISTRADOR------");
			System.out.println("--------------- "+ nombre +" ---------------");
			System.out.println("0.-Salir de la web");
			System.out.println("1.-Ver la lista de vehículos registrados");
			System.out.println("2.-Ver la lista de usuarios registrados");
			System.out.println("3.-Añadir vehículo a la BDD");
			System.out.println("4.-Eliminar vehículo de la BDD");
			System.out.println("5.-Añadir oficina a la BDD");
			System.out.println("6.-Eliminar oficina de la BDD");
			System.out.println("7.-Eliminar reserva de la BDD");
			System.out.println("8.-Cambiar km a los vehículos");
			System.out.println("9.-Crear administrador de la BDD");
			System.out.println("10.-Añadir un usuario a la BDD");
			System.out.println("11. Eliminar un usuario de la BDD");
			System.out.println("12.-Menú usuario");
			
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: break;
			case 1:
				listaVehiculos();
			case 3: 
				MenuAnadirVehiculo.anadirVehiculo(sc);
			case 2:
				RepositorioUsuario.mostrarUsuario();
			case 3: 
				RepositorioUsuario.mostrarVehiculo();
				break;
			case 4: 
				eliminarVehiculo(sc);
				break;
			case 5: 
				//anadirOficina(sc);
				break;
			case 6: 
				//eliminarOficina(sc);
				break;
			case 7: 
				//eliminarReserva(sc);
				break;
			case 8:
				//cambiarKm(sc);
				break;
			case 9:
				//crearAdministrador(sc);
				break;
			case 10:
				MenuUsuario.menuUsuario(sc, nombre);
				break;
			default:
				System.out.println("Número erroneo");
				System.out.println("Introduzca un número del 0 al 12, ambos inclusive");
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
	
	//Método de la lista de vehículos
	private static void listaVehiculos() {
		RepositorioVehiculo.mostrarVehiculo(null);
	}
}
