package View;

import java.util.Scanner;

import Repositorios.RepositorioReserva;
import Repositorios.RepositorioUsuario;

public class MenuReserva {

	// Método para elegir la oficina en el menú de usuario
	public static void elegirOficina(Scanner sc) {
	    String nombreOficina;

	    //Bucle mientras el nombre de la oficina no sea correcto
	    while (true) {
	        System.out.println("Introduzca el nombre de la oficina desde donde desea realizar la reserva:");
	        System.out.println("Le mostraremos los coches disponibles en esa oficina.");
	        nombreOficina = sc.nextLine().trim(); // Trin() Elimina espacios en blanco

	        //Comprobar si la oficina existe
	        if (comprobarOficina(nombreOficina)) {
	            break; // Sale del bucle si la oficina es válida
	        } else {
	            System.out.println("ERROR. Nombre de la oficina mal introducido. Inténtelo nuevamente. Gracias.\n");
	        }
	    }

	    // Mostrar los vehículos disponibles en la oficina seleccionada
	    System.out.println("Esta oficina dispone de estos vehículos:");
	    System.out.println("-----------------------------------------");
	    System.out.println("Matrícula--Marca--Modelo--Km---Tipo---Nº puertas--Potencia--Tamaño");

	    // Consultar y mostrar los vehículos
	    RepositorioReserva.mostrarVehiculoOficina(nombreOficina);
	}

	// Método para comprobar si la oficina existe
	public static boolean comprobarOficina(String nombreOficina) {
	    boolean existe = RepositorioReserva.comprobarOficina(nombreOficina);
	    if (existe) {
	        System.out.println("\nUsted ha elegido la oficina: " + nombreOficina + "\n");
	    }
	    return existe;
	}

	//Método para elegir el vehiculo para la reserva
	public static void elegirVehiculo (Scanner sc) {
		
		while (true) {
			System.out.println("Introduzca la matrícula del vehículo elegido");
			String matricula = sc.nextLine().trim();
			//Comprobar si existe esa matricula
			if(comprobarMatricula(matricula)) { 
				
				break;
			}else {
				System.out.println("ERROR. Matrícula mal introducida");
				System.out.println("Por favor, pruebe de nuevo. Gracias.");
			}
		}
		
	}
	
	//Método para comprobar si el vehículo está bien escrito
	public static boolean comprobarMatricula(String matricula) {
		boolean existe = RepositorioReserva.comprobarMatricula(matricula);
		if (existe) {
			System.out.println("\nUds. ha elegido de nuestra oficina, y el vehículo siguientes: " );
			//Hace la consulta del vehículo para que escriba el modelo con todas sus caráteristicas
			RepositorioReserva.vehiculoSeleccionado(matricula);	
		}
		return existe;
	}
	
	//Método para validar reserva
	public static void validarReserva(Scanner sc) {
		
		System.out.println("Sí está de acuerdo, escriba 'SI'. Sí no está conforme escriba 'NO'");
		String opcion = sc.nextLine();
		if (opcion.equalsIgnoreCase("si")) {
			System.out.println("Operación aprobada\n");//Hay que seguir este proceso
		}else System.out.println("Operación cancelada\n");
	}

}

