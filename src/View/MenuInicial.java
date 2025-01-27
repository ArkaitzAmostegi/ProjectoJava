package View;

import java.util.Scanner;

import Modelo.Furgoneta;
import Modelo.Monovolumen;
import Modelo.Turismo;
import Modelo.Usuario;
import Modelo.Vehiculo;
import Repositorios.RepositorioLogin;
import Repositorios.RepositorioVehiculo;

public class MenuInicial {
	

	//Menú inicial
	public static Modelo.Usuario menuInicial(Scanner sc) {
	
	Modelo.Usuario usuario= new Modelo.Usuario();

	
	int opcion=0;
	
	do {
		System.out.println("-----BIENVENIDO A NUESTRA WEB------");
		System.out.println("---------MENÚ INICIAL---------");
		System.out.println("----------------------------");
		System.out.println("0.-Salir de la web");
		System.out.println("1.-Login");
		System.out.println("2.-Crear usuario");
		
		opcion=sc.nextInt();
		sc.nextLine();
		
		switch (opcion) {
		case 0: break;
		case 1: 
			login(sc, usuario);
			break;
		case 2:
			crearUsuario(sc, usuario);
			break;
		default:
			System.out.println("Número erroneo");
			System.out.println("Introduzca un número del 0 al 2 ambos inclusive");
		}
	}
	while (opcion != 0);
	System.out.println("Ha salido de nuestra web");
	
	return usuario;
	}
	
	//Método crear usuario
	private static void crearUsuario(Scanner sc, Usuario usuario) {
		
		System.out.println("Introduce tus datos");
		System.out.println("Introduce tu DNI: ");
		usuario.setDni(sc.nextLine());
		//comprobarDni(); //tiene que tener 8 dígitos + letra
		comprobarDni(sc, usuario);
		System.out.println("Introduce tu nombre: ");
		usuario.setNombre(sc.next());
		System.out.println("Introduce tu contraseña: ");
		comprobarContrasena(sc, usuario);
		System.out.println("Introduce tu sexo (H = Hombre, M = Mujer)");
		comprobarSexo(sc, usuario);
		System.out.println("Introduce tu teléfono");
		comprobarTelefono(sc, usuario);
		System.out.println("Introduce tu email");
		usuario.setEmail(sc.nextLine());
		comprobarEmail(sc, usuario); //tiene que contener @ y . y después del punto 2 o 3 letras máximo
		
		//Administrador va como falso, ya que un usuario normal no puede crearse como administrador
		usuario.setAdministrador(false);
		
		//Añadir usuario con insert en el repositorio
		RepositorioLogin.crearUsuario(usuario);
		
		System.out.println("¡Usuario registrado correctamente!");
		System.out.println();
	}

	//Método  login Usuario
	private static void login(Scanner sc, Usuario usuario) {
		
		System.out.println("Introduce tus datos de usuario");
		System.out.println("Introduce tu nombre: ");
		String nombre=sc.nextLine();
		System.out.println("Introduce tu contraseña: ");
		String contraseña= sc.nextLine();
		
		//Comprobar si usuario EXISTE en la BDD
		if(RepositorioLogin.comprobarUsuario(nombre, contraseña)) {
			//si es admin, le llevará al menú admin, si no le llevará al menú usuario
			if(RepositorioLogin.comprobarAdmin(nombre, contraseña))
				MenuAdministrador.menuAdministrador(sc, nombre);
			else MenuUsuario.menuUsuario(sc, nombre);
		
		}
		else {
			System.out.println("Usuario no encontrado en nuestra base de datos");
			System.out.println("Si quiere entrar en nuestra web, por favor registrese");
		}
	}
	
	//Método para comprobar que el DNI sea válido
	private static void comprobarDni(Scanner sc, Usuario usuario) {
		boolean dniValido = false;
		
		while (dniValido == false) {
			String dni = sc.next();
			
			if (dni.length() == 9 && dni.matches("^[0-9]{8}[A-Za-z]$")) {
				usuario.setDni(dni.toUpperCase());
				dniValido = true;
			}
			else {
				System.out.println("El DNI que has introducido no es válido, vuelve a intentarlo");
			}
		}
	}
	
	//Método para comprobar que la contraseña coincide con los requisitos mínimos	
	private static void comprobarContrasena(Scanner sc, Usuario usuario) {
		boolean contraseñaValida = false;
		
		while (contraseñaValida == false) {
			String contraseña = sc.next();
			
			if (contraseña.length() != 8) {
				System.out.println("La contraseña debe tener una longitud de 8 caracteres");
			}
			else {
				usuario.setContrasena(contraseña);
				verificarContraseña(sc, usuario);
				contraseñaValida = true;
			}
		}
	}
	private static void verificarContraseña(Scanner sc, Usuario usuario) {
		System.out.println("Repita la contraseña");
		
		String contraseñaComparar = sc.next();
		
		if (contraseñaComparar.equals(usuario.getContrasena())) {
			usuario.setContrasena(contraseñaComparar);
		}
		else {
			System.out.println("La contraseña que has introducido no coincide con la anterior, vuelve a intentarlo");
		}
	}
	
	//Método para comprobar sexo del usuario válido
	private static void comprobarSexo(Scanner sc, Usuario usuario) {
		boolean sexoValido = false;
		
		while (sexoValido == false) {
			String sexo = sc.next();
			if (sexo.equalsIgnoreCase("H") || sexo.equalsIgnoreCase("M")) {
				usuario.setSexo(sexo.toUpperCase());
				sexoValido = true;
			}
			else {
				System.out.println("El sexo que has introducido no es válido. Vuelve a intentarlo");
			}
		}
	}
	
	//Método para comprobar que el número de teléfono tiene 9 números
	private static void comprobarTelefono(Scanner sc, Usuario usuario) {
		boolean telefonoValido = false;
		
		while (telefonoValido == false) {
			int telefono = sc.nextInt();
			
			if (String.valueOf(telefono).length() == 9) {
				usuario.setTelefono(telefono);
				telefonoValido = true;
			}
			else {
				System.out.println("El teléfono que has introducido no es válido");
			}
		}
	}
	
	//Método para comprobar que el email contenga @
	private static void comprobarEmail(Scanner sc, Usuario usuario) {
		boolean emailValido = false;
		
		while (emailValido == false) {
			String email = sc.next();
			
			if (email.contains("@")) {
				usuario.setEmail(email);
				emailValido = true;
			}
			else {
				System.out.println("El email que has introducido no es válido");
			}
		}
	}
}
