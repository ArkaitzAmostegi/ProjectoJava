package View;

import java.util.Scanner;

import Modelo.Oficina;
import Modelo.Usuario;
import Modelo.Usuario_Vehiculo;
import Modelo.Vehiculo;
import Repositorios.RepositorioAdministrador;
import Repositorios.RepositorioLogin;
import Repositorios.RepositorioReserva;
import Repositorios.RepositorioUsuario;
import Repositorios.RepositorioVehiculo;

public class MenuAdministrador {

	//Menú administrador
	public static void menuAdministrador(Scanner sc, Usuario usuario, Usuario_Vehiculo usuariovehiculo) {
		int opcion=0;
		String nombre=usuario.getNombre();
		
		//Obtenemos el dni del dministrador y lo sobreescribimos al objeto
		usuario.setDni(RepositorioLogin.obtenerDni(usuario));
		
		
		do {
			System.out.println("\n-----BIENVENIDO AL MENÚ DE ADMINISTRADOR------");
			System.out.println("--------------- "+nombre+" ---------------");
			System.out.println("--------------ELIJA UNA OPCIÓN---------");
			System.out.println("0.-Volver al menú inicial");
			System.out.println("1.-Ver la lista de vehículos registrados");
			System.out.println("2.-Ver la lista de usuarios registrados");
			System.out.println("3.-Añadir vehículo a la BDD");
			System.out.println("4.-Eliminar vehículo de la BDD");
			System.out.println("5.-Añadir oficina a la BDD");
			System.out.println("6.-Eliminar oficina de la BDD");
			System.out.println("7.-Mostrar las reservas de la BDD");
			System.out.println("8.-Eliminar reserva de la BDD");
			System.out.println("9.-Cambiar km a los vehículos");
			System.out.println("10.-Crear administrador de la BDD");
			System.out.println("11.-Eliminar un usuario de la BDD");
			System.out.println("12.-Ir al menú usuario");
			
			//Bucle que nos va a corregir si el usuario mete primero texto en lugar de número
			while (true) {		
			
				try {
					opcion=sc.nextInt();
					sc.nextLine();
					break;
					
				}catch(java.util.InputMismatchException e){
					System.out.println("Error: Debes ingresar un número válido");
					System.out.println("Introduzca un número del 0 al 12 ambos inclusive");
					sc.next();
				}
			}
			
			switch (opcion) {
			case 0: break;
			case 1:
				RepositorioUsuario.mostrarVehiculo();
				break;
			case 2:
				RepositorioUsuario.mostrarUsuario();
				break;
			case 3: 
				MenuAnadirVehiculo.anadirVehiculo(sc, usuario); 
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
				RepositorioAdministrador.mostrarReservas(usuariovehiculo);
				break;
			case 8:
				eliminarReserva(sc);
				break;
			case 9:
				cambiarKm(sc, usuario);
				break;
			case 10:
				crearAdministrador(sc, usuario);
				break;
			case 11:
				eliminarUsuario(sc, usuario);
				break;
			case 12:
				MenuUsuario.menuUsuario(sc, usuario, usuariovehiculo);
				break;
			default:
				System.out.println("Número erroneo");
				System.out.println("Introduzca un número del 0 al 12, ambos inclusive");
			}
		}
		while (opcion != 0);
		System.out.println("Ha salido de nuestra web");
	}
	//Método para eliminar una reserva determinada de la BDD
	private static void eliminarReserva(Scanner sc) {
		
		Usuario_Vehiculo usuariovehiculo= new Usuario_Vehiculo();
		Usuario usuario = new Usuario();
		
		System.out.println("\nA continuación, te mostramos las reservas que están realizadas");
		RepositorioAdministrador.mostrarReservas(usuariovehiculo);
		
		System.out.println("\nIntroduzca el DNI del usuario de la reserva que desee eliminar: ");
		String dni = sc.nextLine();
		usuario.setDni(dni);
		
		System.out.println("Introduzca el id_coche de la reserva que desea eliminar: ");
		int id_coche = sc.nextInt();
		sc.nextLine();
		usuariovehiculo.setId_coche(id_coche);
		
		System.out.println("Es esta la reserva que desea Uds. eliminar?");
		RepositorioReserva.consultarReserva(usuariovehiculo, usuario);
		System.out.println("(SI/NO)");
		String opcion= sc.nextLine().trim();
		if(opcion.equalsIgnoreCase("SI")) {
			RepositorioReserva.eliminarRerserva(usuariovehiculo, usuario);
			System.out.println("La reserva ha sido eliminada");
		}
		
	}

	//Método para cambiar los Km al vehiculo
	private static void cambiarKm(Scanner sc) {
		
		Vehiculo vehiculo = new Vehiculo();
		
		System.out.println("Introduce la matrícula del vehículo al que deseas cambiar los km: ");
		vehiculo.setMatricula(sc.nextLine());
		//Comprobamos vehículo
		System.out.println("Es este el vehículo que desea modificar?");
		
		RepositorioReserva.vehiculoSeleccionado(vehiculo.getMatricula());//Método para consultar matrícula
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
		
		System.out.println("Introduce los datos del administrador a crear");
		System.out.println("Introduce el DNI: ");
		MenuInicial.comprobarDni(sc, usuario); 
		
		System.out.println("Introduce el nombre: ");
		usuario.setNombre(sc.next());
		
		System.out.println("Introduce la contraseña: ");
		MenuInicial.comprobarContrasena(sc, usuario);
		
		System.out.println("Introduce el sexo (H = Hombre, M = Mujer)");
		MenuInicial.comprobarSexo(sc, usuario);

		System.out.println("Introduce el teléfono");
		MenuInicial.comprobarTelefono(sc, usuario);
		
		System.out.println("Introduce el email");
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
		
		
		while (true) {
			System.out.println("Introduce el nombre de la oficina que desee eliminar: ");
			String nombre=sc.nextLine().trim();
			
			if (RepositorioAdministrador.existeOficina(nombre)) {
				System.out.println("¿Está seguro que desea eliminar esta oficina (SI/NO)?");
				System.out.println("Nombre---------País------------Teléfono");
				RepositorioAdministrador.consultaOficina(nombre);//Método para consultar oficina
				String opcion=sc.nextLine().trim();
				
				if (opcion.equalsIgnoreCase("SI")) {
					RepositorioAdministrador.eliminarOficina(nombre);//Método para eliminar la oficina
					System.out.println("Oficina eliminada\n");
					break;
				} else if (opcion.equalsIgnoreCase("NO")) {
	                System.out.println("Operación cancelada.");
	                break; // Sale del bucle si el usuario cancela
	            } else {
	                System.out.println("Respuesta no válida. Introduzca 'SI' o 'NO'.");
	            }
				
			}else {
				System.out.println("Oficina no encontrada en nuestra base de datos");
				System.out.println("Inténtelo de nuevo, por favor");
			}
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
		oficina.setNombre(sc.nextLine());//Tiene que tener el nombre aja y el guión
		
		System.out.println("Intoduce el teléfono: ");
		String telefono = " ";
		oficina.setTelefono(comprobarTelefono(sc)); 
		
		System.out.println("Intoduce el email: ");
		oficina.setEmail(comprobarEmail(sc)); // que contenga @ y que no lo tenga ninguna otra oficina
		
		RepositorioAdministrador.anadirOficina(oficina);
	}
	
	private static String comprobarEmail(Scanner sc) {
		String email="";
		
		boolean emailValido = false;
		
		while (emailValido == false) {
			email = sc.nextLine();
			
			if(!RepositorioAdministrador.comprobarEmail(email) ){
				
				if (email.contains("@")) {
					emailValido = true;
				}
				else {
					System.out.println("El email que has introducido no es válido");
					System.out.println("El email debe contener la '@'");
				}
			}else {
				System.out.println("Este email ya exite en nuestra BDD");
				System.out.println("Por favor, vuelva a introducirlo el email");
			}
		}
		return email;
	}
	
	//Método para comprobar el teléfono
	private static String comprobarTelefono(Scanner sc) {
	boolean telefonoValido = false;
	String telefono = "";
		while (telefonoValido == false) {

			telefono = sc.nextLine().trim();
		
			if (telefono.matches("^6\\d{8}$")) { // Verifica que empiece con 6 y tenga 9 dígitos en total)) {
				telefonoValido = true;
			}
			else {
				System.out.println("El teléfono que has introducido no es válido");
				System.out.println("El número de teléfono tiene que tener una longitud de 9 dígitos y empezar por 6");
			}
		}
		return telefono;
	}
	
	//Método para eliminar vehículos
	private static void eliminarVehiculo(Scanner sc) {
		
		Vehiculo v = new Vehiculo();
		
		RepositorioUsuario.mostrarMatriculaVehiculo();
		System.out.println("Introduce la  matrícula del vehiculo que desea eliminar: ");
		String matricula =" ";
		matricula = comprobarFormatoMatricula(sc, matricula);
		
		System.out.println("El vehículo que desea eliminar, es el: ");
		RepositorioVehiculo.consultarMatricula(matricula);
		
		System.out.println("Si es así, introduce 'SI'. Si no introduce 'NO'");
		String opcion = sc.nextLine().trim();
		
		if (opcion.equalsIgnoreCase("si")) {
			RepositorioVehiculo.eliminarVehiculo(matricula);
			System.out.println("Vehículo eliminado de nuestra base de datos");
		}
	}
	
	//Método para cambiar los KMs
	private static void cambiarKm(Scanner sc, Usuario usuario) {
		System.out.println("Introduce la matricula del vehículo al que desea modificar la cantidad de kilometros");
		RepositorioUsuario.mostrarMatriculaVehiculo();
		String matricula = sc.next();
		
		System.out.println("Introduce la cantidad nueva de kilometros");
		int km = sc.nextInt();
		
		RepositorioVehiculo.cambiarKilometros(km, matricula);
	}
	//Método para comprobar formato matricula
		public static String comprobarFormatoMatricula(Scanner sc, String matricula) {
			do {
				matricula = sc.nextLine().toUpperCase();
			
				if (matricula.matches("[0-9]{4}[A-Z]{3}")) {
					if(!RepositorioVehiculo.existeMatricula(matricula)) {
						System.out.println("Esa matrícula no está en nuestra base de datos");
						System.out.println("Introduzca de nuevo la matrícula del vehículo que desea eliminar");
					}else break;
				}else {
					System.out.println("La matrícula debe contener este formato 1234ABC");
					System.out.println("Vuelva a introducirla, por favor");
				}

			}while (true);
			return matricula;
		}
}
