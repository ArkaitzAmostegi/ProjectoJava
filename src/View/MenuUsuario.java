package View;

import java.util.Scanner;
import Repositorios.RepositorioLogin;
import Repositorios.RepositorioReserva;
import Modelo.Vehiculo;
import Repositorios.RepositorioUsuario;
import Modelo.Usuario;
import Modelo.Usuario_Vehiculo;

public class MenuUsuario {

	//Menú usuario 
	public static void menuUsuario(Scanner sc, Usuario usuario) {
		
		Usuario_Vehiculo usuariovehiculo = new Usuario_Vehiculo();
		
		int opcion=0;
		do {
			System.out.println("-----BIENVENIDO A NUESTRA WEB------");
			System.out.println("--------------"+ "---------------");
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
	
	private static void hacerReserva(Scanner sc, Usuario usuario, Usuario_Vehiculo usuariovehiculo) {
		
		
		String contraseña = usuario.getContrasena();
		String nombre= usuario.getNombre();
		
		//Obtener dni de usuario e Introducir el dni del usuariovehiculo
		usuariovehiculo.setDni(RepositorioReserva.usuario(nombre,contraseña));
		
		System.out.println("A continuación le mostramos nuestras oficinas, para que elija desde cual quiere realizar la reserva");
		RepositorioUsuario.mostrarOficina(); //mostrar listado de oficinas
		System.out.println();
		
		MenuReserva.elegirOficina(sc, usuariovehiculo); //elegir oficina
		System.out.println();
		
		String matricula = MenuReserva.elegirVehiculo(sc); //elegir vehiculo, Obtenemos MATRÍCULA
		
		//Obtenemos ID_COCHE - introducirlo al usuariovehiculo
		usuariovehiculo.setId_coche(RepositorioReserva.obtenerId(matricula));
		
		MenuReserva.validarReserva(sc, matricula, usuariovehiculo); //validamos el vehículo y la oficina seleccionados. Y elige con o sin conductor
		System.out.println();
		
		//Añadir la reserva la repositorio
		RepositorioReserva.anadirReserva(usuario, usuariovehiculo); //Estamos trabajando en ello
				
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
