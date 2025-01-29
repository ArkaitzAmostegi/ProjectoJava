package View;

import java.util.Scanner;
import Repositorios.RepositorioLogin;
import Modelo.Vehiculo;
import Repositorios.RepositorioUsuario;
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
			System.out.println("Introduce tu DNI actual");
			String dniActual = sc.next();
			System.out.println("Introduce tu nombre");
			String nombreActual = sc.next();
			
			if (RepositorioLogin.comprobarDni(dniActual, nombreActual)) { //Verificar que se encuentra el DNI y el nombre en la base de datos
				System.out.println("Introduce tu nuevo DNI");
				MenuInicial.comprobarDni(sc, usuario);
				RepositorioUsuario.modificarDni(sc, usuario);
			}
			else {
				System.out.println("No se ha encontrado el DNI o usuario en la base de datos de la empresa");
				System.out.println();
			}
			break;
		case 2:
			System.out.println();
			break;
		}
	}
}
