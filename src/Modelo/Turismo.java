package Modelo;

public class Turismo extends Vehiculo{

	//Atributos
	private int potencia;
	private double precio_turismo;
	
	//Getters & Setters
	public int getPotencia() {
		return potencia;
	}
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}	
	public double getPrecio_turismo() {
		return precio_turismo;
	}
	public void setPrecio_turismo(double precio_turismo) {
		this.precio_turismo = precio_turismo;
	}
	
	//Constructores
	public Turismo(int potencia, double precio_turismo) {
		super();
		this.potencia = potencia;
		this.precio_turismo = precio_turismo;
	}
	public Turismo(int id_coche, int id_oficina, String matricula, String marca, String modelo, int km, String tipo,
			boolean alquilado, int potencia, double precio_turismo) {
		super(id_coche, id_oficina, matricula, marca, modelo, km, tipo);
		this.potencia = potencia;
		this.precio_turismo = precio_turismo;
	}
	public Turismo() {
	}
	
	
	
}
