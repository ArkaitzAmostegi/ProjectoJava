package Modelo;

public class Monovolumen extends Vehiculo{

	
	//Atributos
	private int numPuertas;
	
	
	//Getters & Setters
	public int getNumPuertas() {
	    return numPuertas;
	}
	public void setNumPuertas(int numPuertas) {
	    this.numPuertas = numPuertas;
	}
	
	//Constructores
	public Monovolumen(int id_coche, int id_oficina, String matricula, String marca, String modelo, int km, int numPuertas) {
	    super(id_coche, id_oficina, matricula, marca, modelo, km);
	    this.numPuertas = numPuertas;
	}
	public Monovolumen() {
	}
}

