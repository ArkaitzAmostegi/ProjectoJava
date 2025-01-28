package View;

import java.util.Scanner;

import Modelo.Oficina;
import Modelo.Usuario;
import Modelo.Vehiculo;
import Repositorios.RepositorioAdministrador;
import Repositorios.RepositorioLogin;
import Repositorios.RepositorioReserva;
import Repositorios.RepositorioUsuario;
import Repositorios.RepositorioVehiculo;

public class MenuAdministrador {

	//Menú administrador
	public static void menuAdministrador(Scanner sc, String nombre) {
		Usuario usuario = new Usuario();
		int opcion=0;
		
		do {
			System.out.println();
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
			System.out.println("10.-Eliminar un usuario de la BDD");
			System.out.println("11.-Ir al menú usuario");
			
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: break;
			case 1:
				RepositorioUsuario.mostrarVehiculo();
				break;
			case 2:
				RepositorioUsuario.mostrarUsuario();
				break;
			case 3: 
				MenuAnadirVehiculo.anadirVehiculo(sc, nombre);
				break;
			case 4: 
				eliminarVehiculo(sc);
				break;
			case 5: 
				anadirOficina(sc);
				break;
			case 6: 
				eliminarOficina(sc);
				break;
			case 7: 
				//eliminarReserva(sc);
				break;
			case 8:
				cambiarKm(sc);
				break;
			case 9:
				crearAdministrador(sc, usuario);
				break;
			case 10:
				eliminarUsuario(sc, usuario);
				break;
			case 11:
				MenuUsuario.menuUsuario(sc, nombre);
				break;
			default:
				System.out.println("Número erroneo");
				System.out.println("Introduzca un número del 0 al 11, ambos inclusive");
			}
		}
		while (opcion != 0);
		System.out.println("Ha salido de nuestra web");
	}
	
	//Método para cambiar los Km al vehiculo
	private static void cambiarKm(Scanner sc) {
		
		Vehiculo vehiculo = new Vehiculo();
		
		System.out.println("Introduzce la matrícula del vehículo al que deseas cambiar los km: ");
		String matricula= sc.nextLine();
		//Comprobamos vehículo
		System.out.println("Es es el vehiculo que desea modificar?");
		
		RepositorioReserva.vehiculoSeleccionado(matricula);//Método para consultar matrícula
		System.out.println("Intoduce (SI/NO): ");
		String opcion = sc.nextLine().trim();
		
		if(opcion.equalsIgnoreCase("si")){
			System.out.println("Introduce los Km nuevos al vehículo:");
			vehiculo.setKm(sc.nextInt());
			RepositorioAdministrador.introducirKm(vehiculo);//Método para introducir km
			}
	}

	//Método eliminar usuario
	private static void eliminarUsuario(Scanner sc, Usuario usuario) {
		System.out.println("Introduce el nombre del usuario que desee eliminar: ");
		String nombre=sc.nextLine();
		
		System.out.println("Esta seguro que desea eliminar este usuario (SI/NO)?");
		RepositorioAdministrador.consultarUsuario(nombre);//Método para consultar el usuario
		String opcion=sc.nextLine().trim();
		if (opcion.equalsIgnoreCase("SI")) {
			RepositorioAdministrador.eliminarUsuario(nombre);//Método para eliminar la oficina
		}
	}

	//Método crear adminnistrador
	private static void crearAdministrador(Scanner sc, Usuario usuario) {
		
		System.out.println("Introduce tus datos");
		System.out.println("Introduce tu DNI: ");
		usuario.setDni(sc.nextLine());
		//MenuInicial.comprobarDni(sc, usuario); De momento no funciona e comprobar usuario
		
		System.out.println("Introduce tu nombre: ");
		usuario.setNombre(sc.next());
		
		System.out.println("Introduce tu contraseña: ");
		MenuInicial.comprobarContrasena(sc, usuario);
		
		System.out.println("Introduce tu sexo (H = Hombre, M = Mujer)");
		MenuInicial.comprobarSexo(sc, usuario);
		
		System.out.println("Introduce tu teléfono");
		MenuInicial.comprobarTelefono(sc, usuario);
		
		System.out.println("Introduce tu email");
		usuario.setEmail(sc.nextLine());
		MenuInicial.comprobarEmail(sc, usuario); //tiene que contener @ y . y después del punto 2 o 3 letras máximo
		
		//Administrador va como true, así creamos como administrador 
		usuario.setAdministrador(true);
		
		//Añadir usuario con insert en el repositorio
		RepositorioLogin.crearUsuario(usuario);
		
		System.out.println("¡Administrador registrado correctamente!");
		System.out.println();
	}

	//Método para eliminar la oficina
	private static void eliminarOficina(Scanner sc) {
		
		System.out.println("Introduce el nombre de la oficina que desee eliminar: ");
		String nombre=sc.nextLine();
		
		System.out.println("Esta seguro que desea eliminar esta oficina (SI/NO)?");
		System.out.println("Nombre---------DNI------------telefono");
		RepositorioAdministrador.consultaOficina(nombre);//Método para consultar oficina
		String opcion=sc.nextLine().trim();
		if (opcion.equalsIgnoreCase("SI")) {
			RepositorioAdministrador.eliminarOficina(nombre);//Método para eliminar la oficina
		}
	}

	//Método para añadir oficina
	private static void anadirOficina(Scanner sc) {
		
		Oficina oficina = new Oficina();
		
		System.out.println("Intoduce la calle y nº: ");
		oficina.setCalle(sc.nextLine());
		
		System.out.println("Intoduce la ciudad: ");
		oficina.setCiudad(sc.nextLine());
		
		System.out.println("Intoduce el país: ");
		oficina.setPais(sc.nextLine());
		
		System.out.println("Intoduce el nombre de la oficina: ");
		oficina.setNombre(sc.nextLine());
		
		System.out.println("Intoduce el teléfono: ");
		oficina.setTelefono(sc.nextInt());
		//comprobarTelefono();
		sc.nextLine();
		
		System.out.println("Intoduce el email: ");
		oficina.setEmail(sc.nextLine());
		//comprobarEmail();
		
		RepositorioAdministrador.anadirOficina(oficina);
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
}
