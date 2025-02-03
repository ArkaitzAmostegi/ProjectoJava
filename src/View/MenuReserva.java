package View;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import Modelo.Usuario;
import Modelo.Usuario_Vehiculo;
import Repositorios.RepositorioReserva;
import Repositorios.RepositorioUsuario;

public class MenuReserva {

	// Método para elegir la oficina en el menú de usuario
	public static String elegirOficina(Scanner sc, Usuario_Vehiculo usuariovehiculo) {
	    String nombreOficina;

	    //Bucle mientras el nombre de la oficina no sea correcto
	    while (true) {
	        System.out.println("Introduzca el nombre de la oficina desde donde desea realizar la reserva:");
	        System.out.println("Le mostraremos los coches disponibles en esa oficina.");
	        nombreOficina = sc.nextLine().trim().toLowerCase(); 

	        //Comprobar si la oficina existe
	        if (comprobarOficina(nombreOficina)) {
	        	usuariovehiculo.setLugarRecogida(nombreOficina);
		        	if (nombreOficina.contains("madrid")|| nombreOficina.contains("barcelona")|| nombreOficina.contains("bilbao")
		        			||nombreOficina.contains("barakaldo")||nombreOficina.contains("deusto")||nombreOficina.contains("sevilla") || nombreOficina.contains("alicante")) {
		        		System.out.println("A continuación le mostramos en que oficinas puede entregar el vehículo");
		        		RepositorioReserva.mostrarOficinasEspana();
		        		System.out.println("\nIntroduzca el nombre de la oficina, en la que va entregar el vehículo: ");
		        		String lugarEntrega = sc.nextLine();
		        		usuariovehiculo.setLugarEntrega(lugarEntrega);
		        		System.out.println();
		        		System.out.println("Oficina de recogida del vehículo: "+nombreOficina);
		        		System.out.println("Oficina de entrega del vehículo: "+lugarEntrega);
		        		
		        	}else usuariovehiculo.setLugarEntrega(nombreOficina);
	            break; // Sale del bucle si la oficina es válida
	        } else {
	            System.out.println("ERROR. Nombre de la oficina mal introducido. Inténtelo nuevamente. Gracias.\n");
	        }
	    }

	    // Mostrar los vehículos disponibles en la oficina seleccionada
	    System.out.println("La oficina "+ nombreOficina+" dispone de estos vehículos:");
	    System.out.println("-----------------------------------------");
	    System.out.println("Matrícula--Marca--Modelo--Km---Tipo---Nº puertas--Potencia--Tamaño");

        return nombreOficina;
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
			String matricula = "";
			System.out.println("Introduzca la matrícula del vehículo elegido");
			matricula = MenuAdministrador.comprobarFormatoMatricula(sc, matricula);
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
			System.out.println("\nUds. ha elegido nuestra oficina, y el vehículo siguientes: " );
			//Hace la consulta del vehículo para que escriba el modelo con todas sus caráteristicas
			RepositorioReserva.vehiculoSeleccionado(matricula);	
		}
		return existe;
	}
	
	//Método para validar reserva
	public static boolean validarReserva(Scanner sc, Long cantidadDeDias,  String matricula, Usuario_Vehiculo usuariovehiculo) {
		boolean reservar= false;
		System.out.println("Sí está de acuerdo, escriba 'SI'. Sí no está de acuerdo escriba 'NO'");
		String opcion = sc.nextLine();
		while (true) {
			if (opcion.equalsIgnoreCase("si")) {
				precioDia(sc, cantidadDeDias, matricula, usuariovehiculo);
				return true;
			}else if (opcion.equalsIgnoreCase("no")){
				System.out.println("Operación cancelada\n");
				return false;
			} else {
				System.out.println("Ha ocurrido un error por favor escriba (SI/NO).");
				opcion = sc.nextLine();
			}
		}
		
	}
	
	//Método para elegir conductor si es true (con conductor) se multiplicará el precio del día por 1.25
	public static boolean conConductor(Scanner sc, Usuario_Vehiculo usuariovehiculo) {
		boolean opcion= false;
		do {
			System.out.println("Quiere reservar el vehículo con o sin conductor?");
			System.out.println("Para reservar el vehículo con conductor, escriba (CON). Para reservar el vehículo sin conductor, escriba (SIN)");
			String consin=sc.nextLine();
			
			if (consin.equalsIgnoreCase("CON")) {
				usuariovehiculo.setConConductor(true);
				return opcion= true;
			}
			
			else if (consin.equalsIgnoreCase("SIN")) {
				usuariovehiculo.setConConductor(false);
				return opcion = false;
			}
			
			else System.out.println("Ha ocurrido un error.");
		}while(true);
		
	}
	
	//Método cantidad de días que va ha hacer la reserva
	public static long cantidadDias(Scanner sc, String matricula, Usuario_Vehiculo usuariovehiculo) {
		
		String regex = "\\d{4}/\\d{2}/\\d{2}"; // Para formato aaaa/mm/dd
		
        String fecha_recogida = " ";
		String fecha_entrega = " ";
		long dias = 0;
		
		do {
		
	        do {
	    	   
				do {
					System.out.println("Indícanos una fecha de recogida (aaaa/mm/dd): ");
					fecha_recogida= sc.nextLine();
					
					if (!fecha_recogida.matches(regex)) {
						System.out.println("Ha habido un error");
						System.out.println("Vuelva a introducir la fecha, por favor.");
						
					}else usuariovehiculo.setFecha_recogida(fecha_recogida);
					
				}while(!fecha_recogida.matches(regex));
						
				Date fechaR=convertirFecha(fecha_recogida);	
				usuariovehiculo.setFecha_recogida(fecha_recogida);
				
				do {
					System.out.println("Indicanos una fecha de entrega (aaaa/mm/dd): ");
					fecha_entrega= sc.nextLine();
					
					if(!fecha_entrega.matches(regex)) {
						System.out.println("Ha habido un error");
						System.out.println("Vuelva a introducir la fecha, por favor.");
						
					}else usuariovehiculo.setFecha_entrega(fecha_entrega);
					
				}while(!fecha_entrega.matches(regex));
				
				Date fechaE=convertirFecha(fecha_entrega);
				usuariovehiculo.setFecha_entrega(fecha_entrega);
				
				//Calcula la diferencia de días Hecho por ARRITXU
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
		
       return dias;
	}

	//Método precio final del alquiler por días
	public static void precioDia(Scanner sc, long cantidadDeDias, String matricula, Usuario_Vehiculo usuariovehiculo) {
		
		int precioVehiculo= RepositorioReserva.precioDiaVehiculo(matricula);//Me devuelve el precio por el tipo de vehículo
		
		double precioDia = 0;
		
		// Guarda la respuesta del usuario sobre conductor
		boolean conConductor =conConductor(sc, usuariovehiculo);
	    
	    // Obtiene la cantidad de días sin llamar a conSinConductor() nuevamente
	    long dias = cantidadDeDias;

	    if (conConductor) { 
	    	// El 2 corresponde uno al pago del conductor y otro el pago del vehículo
	        precioDia = 2 * (dias * precioVehiculo); //Hay que cambiar al precio furgoneta, turismo o monovolumen

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
		    usuariovehiculo.setPrecio_total(precioTotal);
	    } else {
	        usuariovehiculo.setPrecio_total( precioDia = dias * precioVehiculo);
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
	//Método para comprovar los datos de la reserva y activar el alquilado 
	public static void activarReserva(Scanner sc, Usuario usuario, Usuario_Vehiculo usuariovehiculo, String matricula) {
	
		System.out.println(usuario.getNombre()+" a elegido realizar la siguiente reserva: ");
		System.out.println("Nombre: "+usuario.getNombre()+ "\nFecha de recogida: "+usuariovehiculo.getFecha_recogida()+ "\nFecha de entrega: "+usuariovehiculo.getFecha_entrega()
		+"\nLugar de recogida: "+usuariovehiculo.getLugarRecogida()+"\nLugar de entrega: "+usuariovehiculo.getLugarEntrega()+"\nConductor: "+usuariovehiculo.isConConductor()+"\nPrecio total de la reserva: "+usuariovehiculo.getPrecio_total());
		
		System.out.println("Confirmar reserva (SI/NO)");
		String opcion = sc.nextLine();
		while (true) {
			if (opcion.equalsIgnoreCase("si")) {
				RepositorioReserva.anadirReserva(usuario, usuariovehiculo);
				System.out.println("Su Reserva ha sido realizada");
				System.out.println("Pase a recoger le vehículo, por nuestra oficina "+usuariovehiculo.getLugarRecogida()+" la fecha "+usuariovehiculo.getFecha_recogida()+ "\n");
				break;
			}else if(opcion.equalsIgnoreCase("NO")){
				System.out.println("Reserva cancelada");
				RepositorioReserva.eliminarRerserva(usuariovehiculo, usuario);
				break;
			}else {
				System.out.println("Ha ocurrido un error");
				System.out.println("Escriba (SI/NO):");
				opcion = sc.nextLine();
			}
		}		
	}
	
	
}

