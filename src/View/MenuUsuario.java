package View;

import java.util.Scanner;
import Repositorios.RepositorioLogin;
import Repositorios.RepositorioReserva;
import Modelo.Vehiculo;
import Repositorios.RepositorioUsuario;
import Repositorios.RepositorioVehiculo;
import Modelo.Usuario;
import Modelo.Usuario_Vehiculo;

public class MenuUsuario {

	//Menú usuario 
	public static void menuUsuario(Scanner sc, Usuario usuario, Usuario_Vehiculo usuariovehiculo) {
		
		String nombre = usuario.getNombre();
		
		//Obtenemos el dni del usuario y lo sobreescribimos al objeto usuario
		usuario.setDni(RepositorioLogin.obtenerDni(usuario));
		
		int opcion=0;
		do {
			System.out.println("-----BIENVENIDO A NUESTRA WEB------");
			System.out.println("------------ "+nombre+" ----------");
			System.out.println("-----------MENÚ USUARIO----------");
			System.out.println("0.-Cerrar sesión");
			System.out.println("1.-Nuestra flota de vehículos");
			System.out.println("2.-Donde disponemos de oficinas");
			System.out.println("3.-Realizar una reserva");
			System.out.println("4.-Modificar sus datos de usuario");
			
			//Bucle que nos va a corregir si el usuario mete texto en vez de un número
			while (true) {		
			
				try {
					opcion=sc.nextInt();
					sc.nextLine();
					break;
					
				}catch(java.util.InputMismatchException e){
					System.out.println("Error: Debes ingresar un número válido");
					System.out.println("Introduzca un número del 0 al 4 ambos inclusive");
					sc.next();
				}
			}
			
			switch (opcion) {
			case 0: break;
			case 1: 
				RepositorioUsuario.mostrarVehiculo();
				break;
			case 2: 
				RepositorioUsuario.mostrarOficina(); 
				break;
			case 3: 
				hacerReserva(sc, usuario, usuariovehiculo);
				break;				
			case 4: 
				modificarDatos(sc, usuario);
				break;
			default:
				System.out.println("Número erroneo");
				System.out.println("Introduzca un número del 0 al 4 ambos inclusive");
			}
		}
		while (opcion != 0);
		System.out.println("Ha salido de nuestra web");
	}
	
	//Método para hacer la reserva 
	private static void hacerReserva(Scanner sc, Usuario usuario, Usuario_Vehiculo usuariovehiculo) {
		int cantidadReservas = 1;
		while (true) {
			String contraseña = usuario.getContrasena();
			String nombre= usuario.getNombre();
			
			//Obtener dni de usuario e Introducir el dni del usuariovehiculo
			usuariovehiculo.setDni(RepositorioReserva.usuario(nombre,contraseña));
			
			Long cantidadDeDias = MenuReserva.cantidadDias(sc, nombre, usuariovehiculo);//Reserva de días
			
			System.out.println("A continuación le mostramos nuestras oficinas, para que elija desde cual quiere realizar la reserva");
			RepositorioUsuario.mostrarOficina(); //mostrar listado de oficinas
			
			String nombreOficina= MenuReserva.elegirOficina(sc, usuariovehiculo); //elegir oficina
			System.out.println();
			
			//Método para conseguir el id_oficina
			int id_oficina = RepositorioReserva.obtenerIdOficina(nombreOficina);
			
			// Consultar y mostrar los vehículos libres en esa oficina
			RepositorioReserva.vehiculoLibre(id_oficina, usuariovehiculo);
			
			//elegir vehiculo, y obtenemos MATRÍCULA
			String matricula = MenuReserva.elegirVehiculo(sc); 
			
			//Obtenemos ID_COCHE - introducirlo al usuariovehiculo
			usuariovehiculo.setId_coche(RepositorioReserva.obtenerId(matricula));
			
			if (MenuReserva.validarReserva(sc, cantidadDeDias, matricula, usuariovehiculo)) {//validamos el vehículo y la oficina seleccionados. Y elige con o sin conductor
				//Método para comprovar los datos de la reserva y activar el alquilado. Reserva confirmada o eliminada
				MenuReserva.activarReserva(sc, usuario, usuariovehiculo, matricula);
				
			}else System.out.println("Su reserva ha sido cancelada\n");
			
			
			System.out.println("UDs. Tiene "+cantidadReservas+", reserva/s hecha/s");
			System.out.println("Quiere hacer otra reserva?");
			System.out.println("SI/NO");
			String opcion = sc.nextLine();
			if (opcion.equalsIgnoreCase("SI")) {
				cantidadReservas ++;
			}else if (opcion.equalsIgnoreCase("NO")) {
				break;
			}else {
				System.out.println("Ha ocurrido un error");
				System.out.println("Escriba (SI/NO)");
				opcion = sc.nextLine();
			}
		}
		
	}
	
	//Modificar datos del usuario
	private static void modificarDatos(Scanner sc, Usuario usuario) {
		
		String nombre = usuario.getNombre();
		
		System.out.println("-----BIENVENIDO A NUESTRA WEB------");
		System.out.println("--------- "+nombre+" ------------");
		System.out.println("---------EDITAR DATOS---------");
		System.out.println("----------------------------");
		
		System.out.println("0. Volver atrás");
		System.out.println("1. Modificar DNI");
		System.out.println("2. Modificar nombre");
		System.out.println("3. Modificar sexo");
		System.out.println("4. Modificar número de teléfono");
		System.out.println("5. Modificar correo electrónico");
		System.out.println("6. Modificar contraseña");
		
		int opcion = sc.nextInt();
		
		switch (opcion) {
		case 0: 
			break;
		case 1:
			cambiarDni(sc, usuario);
			break;
		case 2:
			cambiarNombre(sc, usuario);
			break;
		case 3:
			cambiarSexo(sc, usuario);
			break;
		case 4:
			cambiarTelefono(sc, usuario);
			break;
		case 5:
			cambiarCorreo(sc, usuario);
			break;
		case 6:
			cambiarContraseña(sc, usuario);
			break;
		}
	}
	
	//Métodos para cambiar los datos del usuario
	private static void cambiarDni(Scanner sc, Usuario usuario) {
		System.out.println("Introduce tu DNI");
		boolean dniValido = false;
		
		while (dniValido == false) {
			String dni = sc.next();
			
			if (dni.length() == 9 && dni.matches("^[0-9]{8}[A-Za-z]$")) {
				System.out.println("Introduce tu nombre");		
				String nombre = sc.next();
				System.out.println("Introduce tu nuevo DNI");
				String dniNuevo = sc.next().toUpperCase();
				
				RepositorioUsuario.modificarDni(dniNuevo, nombre);
				dniValido = true;
			}
			else {
				System.out.println("El DNI introducido no es válido");
			}
		}
	}
	
	private static void cambiarNombre(Scanner sc, Usuario usuario) {
		System.out.println("Introduce tu DNI");
		String dni = sc.next();
		System.out.println("Introduce tu nombre");
		String nombre = sc.next();
		System.out.println("Introduce tu nuevo nombre");
		String nombreNuevo = sc.next();
		
		RepositorioUsuario.modificarNombre(dni, nombreNuevo);
	}
	
	private static void cambiarSexo(Scanner sc, Usuario usuario) {
		System.out.println("Introduce tu DNI");
		String dni = sc.next();
		System.out.println("Introduce tu nuevo sexo");
		boolean sexoValido = false;
		
		while (sexoValido == false) {
			String sexo = sc.next().toUpperCase();
			
			if (sexo.length() != 1) {
				System.out.println("El sexo debe tener una longitud de 1 caracter solamente");
			}
			else {		
				RepositorioUsuario.modificarSexo(dni, sexo);
				sexoValido = true;
			}
		}
	}
	
	private static void cambiarTelefono(Scanner sc, Usuario usuario) {
		System.out.println("Introduce tu DNI");
		String dni = sc.next();
		System.out.println("Introduce tu nuevo número de teléfono");
		boolean telefonoValido = false;
		
		while (telefonoValido == false) {
			String numTelefono = sc.nextLine();
			if (!numTelefono.matches("^6\\d{8}$")) {
				System.out.println("El teléfono debe tener una longitud de 9 caracteres");
			}
			else {
				RepositorioUsuario.modificarTelefono(dni, numTelefono);
				telefonoValido = true;
			}
		}
	}
	
	private static void cambiarCorreo(Scanner sc, Usuario usuario) {
		System.out.println("Introduce tu DNI");
		String dni = sc.next();
		System.out.println("Introduce tu nuevo correo electrónico");
		String correo = sc.next();
		
		RepositorioUsuario.modificarCorreo(dni, correo);
	}
	
	private static void cambiarContraseña(Scanner sc, Usuario usuario) {
		System.out.println("Introduce tu DNI");
		String dni = sc.next();
		System.out.println("Introduce tu nueva contraseña");
		boolean contraseñaValida = false;
		
		while (contraseñaValida == false) {
			String contraseña = sc.next();
			
			if (contraseña.length() != 8) {
				System.out.println("La contraseña que has introducido no es válida. Debe contener una longitud de 8 caracteres");
			}
			else {
				RepositorioUsuario.modificarContraseña(dni, contraseña);
				contraseñaValida = true;
			}
		}
	}
}
