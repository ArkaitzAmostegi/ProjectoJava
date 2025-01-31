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
	public static void menuUsuario(Scanner sc, Usuario usuario) {
		
		Usuario_Vehiculo usuariovehiculo = new Usuario_Vehiculo();
		
		int opcion=0;
		do {
			System.out.println("-----BIENVENIDO A NUESTRA WEB------");
			System.out.println("--------------"+ "---------------");
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
		
		//Método para comprovar los datos de la reserva y activar el alquilado. Reserva confirmada o eliminada
		MenuReserva.activarReserva(sc, usuario, usuariovehiculo);
		
		
			
		//MUY IMPORTANTE!!!!!
		//La fecha de recogida también tiene que ser primary key, si sólo tenemos el dni y el id_coche como primary keys, un usuario determinado no podrá volver a coger ese vehículo nuevamente
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
			int numTelefono = sc.nextInt();
			if (String.valueOf(numTelefono).length() != 9) {
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
