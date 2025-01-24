package View;

import java.util.Scanner;

import Repositorios.ConectorBD;

public class mainProyecto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc= new Scanner(System.in);
		
		ConectorBD.conectar();
		MenuInicial.menuInicial(sc);
		
		
		sc.close();
	}

}
