package Modelo;

public class Turismo extends Vehiculo{

	//Atributos
	private int potencia;
	
	
	//Getters & Setters
	public int getPotencia() {
		return potencia;
	}
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}
	
	//Constructores
	public Turismo(int id_coche, int id_oficina, String matricula, String marca, String modelo, int km, int potencia) {
		super(id_coche, id_oficina, matricula, marca, modelo, km);
		this.potencia = potencia;
	}
	public Turismo(int id_coche, int id_oficina, String matricula, String marca, String modelo, int km) {
		super(id_coche, id_oficina, matricula, marca, modelo, km);
	}
	public Turismo() {
	}
	
}
