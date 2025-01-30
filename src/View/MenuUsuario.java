package View;

import java.util.Scanner;
import Repositorios.RepositorioLogin;
import Modelo.Vehiculo;
import Repositorios.RepositorioUsuario;
import Repositorios.RepositorioVehiculo;
import Modelo.Usuario;

public class MenuUsuario {

	//Menú usuario 
	public static void menuUsuario(Scanner sc, String nombre) {
		
		Modelo.Usuario usuario = new Usuario();
		
		int opcion=0;
		do {
			System.out.println("-----BIENVENIDO A NUESTRA WEB------");
			System.out.println("--------------"+ nombre +"---------------");
			System.out.println("-----------MENÚ USUARIO----------");
			System.out.println("0.-Cerrar sesión");
			System.out.println("1.-Nuestra flota de vehículos");
			System.out.println("2.-Donde disponemos de oficinas");
			System.out.println("3.-Realizar una reserva");
			System.out.println("4.-Modificar sus datos de usuario");
			
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: break;
			case 1: 
				RepositorioUsuario.mostrarVehiculo();
				break;
			case 2: 
				RepositorioUsuario.mostrarOficina(); 
				break;
			case 3: 
				hacerReserva(sc);
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
	
	private static void hacerReserva(Scanner sc) {
		
		System.out.println("A continuación le mostramos nuestras oficinas, para que elija desde cual quiere realizar la reserva");
		RepositorioUsuario.mostrarOficina(); //mostrar listado de oficinas
		System.out.println();
		MenuReserva.elegirOficina(sc); //elegir oficina
		System.out.println();
		String matricula = MenuReserva.elegirVehiculo(sc); //elegir vehiculo
		
		MenuReserva.validarReserva(sc, matricula); //validamos el vehículo y la oficina seleccionados. y elige con o sin conductor
		System.out.println();
	}
	
	//Modificar datos del usuario
	private static void modificarDatos(Scanner sc, Usuario usuario) {
		System.out.println("-----BIENVENIDO A NUESTRA WEB------");
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
			modificarDatos(sc, usuario);
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
		String dni = sc.next();
		System.out.println("Introduce tu nombre");		
		String nombre = sc.next();
		System.out.println("Introduce tu nuevo DNI");
		String dniNuevo = sc.next().toUpperCase();
		
		RepositorioUsuario.modificarDni(dniNuevo, nombre);
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
		String sexo = sc.next().toUpperCase();
		
		RepositorioUsuario.modificarSexo(dni, sexo);
	}
	
	private static void cambiarTelefono(Scanner sc, Usuario usuario) {
		System.out.println("Introduce tu DNI");
		String dni = sc.next();
		System.out.println("Introduce tu nuevo número de teléfono");
		int numTelefono = sc.nextInt();
		
		RepositorioUsuario.modificarTelefono(dni, numTelefono);
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
		String contraseña = sc.next();
		
		RepositorioUsuario.modificarContraseña(dni, contraseña);
	}
}
