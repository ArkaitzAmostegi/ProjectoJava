package View;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public static String elegirVehiculo (Scanner sc) {
		
		while (true) {
			System.out.println("Introduzca la matrícula del vehículo elegido");
			String matricula = sc.nextLine().trim();
			//Comprobar si existe esa matricula
			if(comprobarMatricula(matricula)) { 
				return matricula;
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
			conSinConductor(sc);
		}else System.out.println("Operación cancelada\n");
	}
	
	//Método para elegir conductor si es true (con conductor) se multiplicará el precio del día por 1.25
	public static boolean conSinConductor(Scanner sc) {
		boolean opcion= false;
		System.out.println("Quiere reservar el vehículo con o sin conductor?");
		System.out.println("Para reservar el vehículo con conductor, escriba (CON). Para reservar el vehículo sin conductor, escriba (SIN)");
		String consin=sc.nextLine();
		if (consin.equalsIgnoreCase("CON")) {
			return opcion= true;
		}else return opcion = false;
	}
	
	//Método cantidad de días que va ha hacer la reserva
	public static long cantidadDias(Scanner sc, String matricula) {
		
		System.out.println("Indícanos una fecha de recogida (aaaa/mm/dd: ");
		String fecha_recogida= sc.nextLine();
		//Comprobar formatoFecha();
		Date fechaR=convertirFecha(fecha_recogida);
		
		System.out.println("Indicanos una fecha de entrega (aaaa/mm/dd): ");
		String fecha_entrega= sc.nextLine();
		Date fechaE=convertirFecha(fecha_entrega);

		RepositorioReserva.comprobarFecha(matricula, fecha_entrega, fecha_recogida);
		
		//Método para hacer la resta de días
		long ms= fechaE.getTime() - fechaR.getTime();
		long dias= ms/(1000*60*60*24);
		System.out.println(dias);
	
		return dias;
	}

	//Método precio final del alquiler por días
	public static void precioDia(Scanner sc, String matricula) {
		
		//el precio es igual al vehículo alquilado*factor con conductor* km
		double precioDia = 0;
		
		if (conSinConductor(sc)) { //50 son los euros por día que cuesta el alquiler
			precioDia = (cantidadDias(sc, matricula) * 50) * 1.25;
		}else precioDia = cantidadDias(sc, matricula) * 50;
		
		System.out.println("El precio total de la reserva es: "+precioDia+ " €");
	}
	//Método para pasar de string a date datetime
	private static Date convertirFecha(String fechaString) {
	    
	    SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy/MM/dd");
	    java.sql.Date fechaSql = null;
	    try {
	        // Convertir el String a java.util.Date
	        java.util.Date fechaUtil = formatoEntrada.parse(fechaString);
	        
	        // Convertir el java.util.Date a java.sql.Date
	        fechaSql = new java.sql.Date(fechaUtil.getTime());
	        
	        
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return fechaSql;
	}
	//Método precio final del alquiler por km
}

