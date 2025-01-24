package Modelo;

public class Furgoneta extends Vehiculo{

	
	//Atributos
	private Tamano tamano;

	
	//Getters & Setters
	public Tamano getTamano() {
		return tamano;
	}
	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}
	
	
	//Constructores
	public Furgoneta(int id_coche, int id_oficina, String matricula, String marca, String modelo, int km,
			Tamano tamano) {
		super(id_coche, id_oficina, matricula, marca, modelo, km);
		this.tamano = tamano;
	}
	public Furgoneta(int id_coche, int id_oficina, String matricula, String marca, String modelo, int km) {
		super(id_coche, id_oficina, matricula, marca, modelo, km);
	}
	public Furgoneta() {
	}
}
