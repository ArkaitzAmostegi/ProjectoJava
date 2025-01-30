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
	        nombreOficina = sc.nextLine().trim(); // Trim() Elimina espacios en blanco

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
	public static void validarReserva(Scanner sc, String matricula) {
		
		System.out.println("Sí está de acuerdo, escriba 'SI'. Sí no está conforme escriba 'NO'");
		String opcion = sc.nextLine();
		if (opcion.equalsIgnoreCase("si")) {
			precioDia(sc, matricula);
		}else System.out.println("Operación cancelada\n");
	}
	
	//Método para elegir conductor si es true (con conductor) se multiplicará el precio del día por 1.25
	public static boolean conSinConductor(Scanner sc) {
		boolean opcion= false;
		do {
			System.out.println("Quiere reservar el vehículo con o sin conductor?");
			System.out.println("Para reservar el vehículo con conductor, escriba (CON). Para reservar el vehículo sin conductor, escriba (SIN)");
			String consin=sc.nextLine();
			
			if (consin.equalsIgnoreCase("CON")) return opcion= true;
			else if (consin.equalsIgnoreCase("SIN"))return opcion = false;
			else System.out.println("Ha ocurrido un error.");
		}while(true);
		
	}
	
	//Método cantidad de días que va ha hacer la reserva
	public static long cantidadDias(Scanner sc, String matricula) {
		
		String regex = "\\d{4}/\\d{2}/\\d{2}"; // Para formato aaaa/mm/dd
		
        String fecha_recogida = " ";
		String fecha_entrega = " ";
		long dias = 0;
		
		do {
		
	        do {
	    	   
				do {
					System.out.println("Indícanos una fecha de recogida (aaaa/mm/dd): ");
					fecha_recogida= sc.nextLine();
					//comprobarFechaVehiculo();
					if (!fecha_recogida.matches(regex)) {
						System.out.println("Ha habido un error");
						System.out.println("Vuelva a introducir la fecha, por favor.");
					}
				}while(!fecha_recogida.matches(regex));
				//Comprobar formatoFecha();
				Date fechaR=convertirFecha(fecha_recogida);
				
				do {
					System.out.println("Indicanos una fecha de entrega (aaaa/mm/dd): ");
					fecha_entrega= sc.nextLine();
					//comprobarFechaVehiculo();
					if(!fecha_entrega.matches(regex)) {
						System.out.println("Ha habido un error");
						System.out.println("Vuelva a introducir la fecha, por favor.");
					}
				}while(!fecha_entrega.matches(regex));
				//Comprobar formatoFecha();
				Date fechaE=convertirFecha(fecha_entrega);
				
				
				
				//Calcula la diferencia de días
				long ms= fechaE.getTime() - fechaR.getTime();
				dias= ms/(1000*60*60*24);
				
				if (dias<0) {
					System.out.println("Ha habido un error");
					System.out.println("La fecha de entrega no puede ser menor , que la fecha de recogida");
					System.out.println("Vuelva a introducir la fecha, por favor.");
				}else if (dias==0) {
					System.out.println("El mínimo de reserva es de 1 día. Por lo que le cobraremos un día de reserva");
					dias = 1;
				}else {
					System.out.println("La cantidad de días reserva es de: "+dias+ " días.");
				}
				
	       	}while(dias<0);
	       
	    }while(RepositorioReserva.comprobarFecha(matricula, fecha_entrega, fecha_recogida));
		
       //Añadir la reserva la repositorio
		RepositorioReserva.anadirReserva(matricula, fecha_recogida, fecha_entrega); //Está sin hacer
		
		//MUY IMPORTANTE
		//HAY QUE AÑADIR QUE SI MATRICULA RESERVADA, NO SE PUEDE VOLVER A RESERVAR HASTA DESPUÉS DE SU FECHA_RECOGIDA
		
       return dias;
	}

	//Método precio final del alquiler por días
	public static void precioDia(Scanner sc, String matricula) {
		
		double precioDia = 0;
		
		  // Guarda la respuesta del usuario sobre conductor
	    boolean conConductor = conSinConductor(sc);
	    
	    // Obtiene la cantidad de días sin llamar a conSinConductor() nuevamente
	    long dias = cantidadDias(sc, matricula);

	    if (conConductor) { 
	        precioDia = 2 * (dias * 50); // El 2 corresponde uno al pago del conductor y otro el pago del vehículo

		    System.out.println("El precio de la reserva del vehículo es de: " + precioDia + " €");
		    System.out.println();
	        System.out.println("Se hace una reserva de 120€ día, por si el conductor ha de pagarse dietas(alojamiento y comidas)");
	        System.out.println("Esa reserva se devolverá una vez entregado el vehículo, si no ha sido necesaria");
	        System.out.println("O se descontará aquello que se haya utilizado");
	        System.out.println();
	        double dietasConductor= dias *120;
	        System.out.println("Precio dietas conductor: "+dietasConductor);
	        double precioTotal= precioDia +  dietasConductor;
	        System.out.println();
		    System.out.println("El precio total de la reserva es: " + precioTotal + " €");
	    } else {
	        precioDia = dias * 50;
		    System.out.println("El precio total de la reserva es: " + precioDia + " €");
	    }

	
	}
	//Método para pasar de String a date datetime (Hecho por Arritxu)
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
	
}

