package View;

import java.util.Scanner;

import Modelo.Furgoneta;
import Modelo.Monovolumen;
import Modelo.Turismo;
import Modelo.Usuario;
import Modelo.Vehiculo;
import Repositorios.LoginRepositorio;
import Repositorios.VehiculoRepositorio;

public class MenuInicial {
	

	//Scanner menú inicial
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
		//comprobarDni();
		System.out.println("Introduce tu nombre: ");
		usuario.setNombre(sc.nextLine());
		System.out.println("Introduce tu contraseña: ");
		usuario.setContrasena(sc.nextLine());
		//comprobarContrasena();
		System.out.println("Repite tu contraseña: ");
		usuario.setContrasena(sc.nextLine());
		//Comprobar que sea igual a la anterior
		System.out.println("Introduce tu sexo");
		usuario.setSexo(sc.next());
		//comprobarSexo();
		System.out.println("Introduce tu teléfono");
		usuario.setTelefono(sc.nextInt());
		sc.nextLine();
		//comprobarTelefono();
		System.out.println("Introduce tu email");
		usuario.setEmail(sc.nextLine());
		//comprobarEmail();
		
		//Administrador va como falso, ya que un usuario normal no puede crearse como administrador
		usuario.setAdministrador(false);
		
		//Añadir usuario con insert en el repositorio
		LoginRepositorio.crearUsuario(usuario);
	}

	//Método  login Usuario
	private static void login(Scanner sc, Usuario usuario) {
		
		System.out.println("Introduce tus datos");
		System.out.println("Introduce tu nombre: ");
		String nombre=sc.nextLine();
		System.out.println("Introduce tu contraseña: ");
		String contraseña= sc.nextLine();
		
		//Comprobar si usuario EXISTE en la BDD
		if(LoginRepositorio.comprobarUsuario(nombre, contraseña)) {
			//si es admin, le llevará al menú admin, si no le llevará al menú usuario
			if(LoginRepositorio.comprobarAdmin(nombre, contraseña))
			MenuAdministrador.menuAdministrador(sc);
			else MenuUsuario.menuUsuario(sc);
		
		}else {
			System.out.println("Usuario no encontrado en nuestra base de datos");
			System.out.println("Si quiere entrar en nuestra web, por favor registrese");
		}
		
	}
}
