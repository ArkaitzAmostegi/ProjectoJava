package View;

import java.util.Scanner;

import Modelo.Furgoneta;
import Modelo.Monovolumen;
import Modelo.Tamano;
import Modelo.Turismo;
import Repositorios.RepositorioUsuario;
import Repositorios.RepositorioVehiculo;

public class MenuAnadirVehiculo {

	//Menú para añadir vehiculos
	public static void anadirVehiculo(Scanner sc, String nombre) {
		
		int opcion=0;
		do {
			System.out.println("-----BIENVENIDO AL MENÚ DE ADMINISTRADOR------");
			System.out.println("-------------" + nombre + "--------------");
			System.out.println("-------------AÑADIR VEHICULO---------");
			System.out.println("0.-Salir de la web");
			System.out.println("1.-Añadir furgoneta a la BDD");
			System.out.println("2.-Añadir monovolumen a la BDD");
			System.out.println("3.-Añadir turismo a la BDD");
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: break;
			case 1: 
				anadirFrugoneta(sc); //No está bien acabado, no se introduce por el enum
				break;
			case 2: 
				anadirMonovolumen(sc);
				break;
			case 3: 
				anadirTurismo(sc);
				break;
			default:
				System.out.println("Número erroneo");
				System.out.println("Introduzca un número del 0 al 3 ambos inclusive");
			}
		}
		while (opcion != 0);
		
	}

	//Método para añadir turismo
	private static void anadirTurismo(Scanner sc) {
		
		Turismo turis = new Turismo();
		System.out.println("Vehículo seleccionado: TURISMO");
		System.out.println("Vamos a introducir los datos");
		
		System.out.println("Introduce la matricula");
		turis.setMatricula(sc.nextLine());
		
		System.out.println("Introduce el modelo");
		turis.setModelo(sc.nextLine());
		
		System.out.println("Introduce la marca");
		turis.setMarca(sc.nextLine());
		
		System.out.println("Introduce los Km");
		turis.setKm(sc.nextInt());
		sc.nextLine();
		
		turis.settipo("Turismo");//Va fijo como Turismo
		
		System.out.println("Introduce la potencia del vehículo");
		turis.setPotencia(sc.nextInt());
		sc.nextLine();
		
		System.out.println("Introduce el precio/km del vehículo");
		turis.setPrecio_turismo(sc.nextDouble());
		
		System.out.println("Introduce el número de identificación de la oficina en la que se va ha registrar el vehículo(de la 1 a la 23): ");
		RepositorioUsuario.mostrarOficina();
		turis.setId_oficina(sc.nextInt());
		sc.nextLine();
		
		RepositorioVehiculo.insertarTurismo(turis);
	}

	//Método para añadir Monovolumen
	private static void anadirMonovolumen(Scanner sc) {
		
		Monovolumen mono = new Monovolumen();
		
		System.out.println("Vehículo seleccionado: MONOVOLUMEN");
		System.out.println("Vamos a introducir los datos");
		
		System.out.println("Introduce la matricula");
		mono.setMatricula(sc.nextLine());
		
		System.out.println("Introduce el modelo");
		mono.setModelo(sc.nextLine());
		
		System.out.println("Introduce la marca");
		mono.setMarca(sc.nextLine());
		
		System.out.println("Introduce los Km");
		mono.setKm(sc.nextInt());
		sc.nextLine();
		
		mono.settipo("Monovolumen");//Va fijo como Monovolumen
		
		System.out.println("Introduce el nº de puertas");
		mono.setNumPuertas(sc.nextInt());
		
		System.out.println("Introduce el precio/km del vehículo");
		mono.setPrecio_monovolumen(sc.nextDouble());
		
		System.out.println("Introduce el número de identificación de la oficina en la que se va ha registrar el vehículo: ");
		RepositorioUsuario.mostrarOficina();
		mono.setId_oficina(sc.nextInt());
		sc.nextLine();
		
		
		RepositorioVehiculo.insertarMonovolumen(mono);
	}

	//Método para añadir furgonetas
	private static void anadirFrugoneta(Scanner sc) {

		Furgoneta furgo = new Furgoneta();
		System.out.println("Vehículo seleccionado: FURGONETA");
		System.out.println("Vamos a introducir los datos");
		
		System.out.println("Introduce la matricula");
		furgo.setMatricula(sc.nextLine());
		
		System.out.println("Introduce el modelo");
		furgo.setModelo(sc.nextLine());
		
		System.out.println("Introduce la marca");
		furgo.setMarca(sc.nextLine());
		
		System.out.println("Introduce los Km");
		furgo.setKm(sc.nextInt());
		sc.nextLine();
		
		furgo.settipo("Furgoneta");//Va fijo como furgoneta
		
		System.out.println("Introduce el tamaño de la furgoneta");
		tamanoFurgo(sc, furgo);
		
		System.out.println("Introduce el precio/km del vehículo");
		furgo.setPrecio_furgoneta(sc.nextDouble());
		
		System.out.println("Introduce el número de identificación de la oficina en la que se va ha registrar el vehículo: ");
		RepositorioUsuario.mostrarOficina();
		furgo.setId_oficina(sc.nextInt());
		sc.nextLine();
		
		
		RepositorioVehiculo.insertarFurgoneta(furgo);
	}

	//Método tamaño furgo
	public static void tamanoFurgo(Scanner sc, Furgoneta furgo) {
		Tamano[] lista=Tamano.values();
		
		System.out.println("Te mostramos la lista de tamaños");
		for (int i=0;i<lista.length;i++) {
			System.out.println("Opcion:"+i+" "+lista[i]);
		}
		
		System.out.println("Introduce una opción: ");
		int opcion= sc.nextInt();
		sc.nextLine();
	
		furgo.setTamano(lista[opcion]);
	}


}
