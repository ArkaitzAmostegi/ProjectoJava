package Modelo;

public class Furgoneta extends Vehiculo{

	
	//Atributos
	private Tamano tamano;
	private double precio_furgoneta;
	
	//Getters & Setters
	public Tamano getTamano() {
		return tamano;
	}
	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}	
	public double getPrecio_furgoneta() {
		return precio_furgoneta;
	}
	public void setPrecio_furgoneta(double precio_furgoneta) {
		this.precio_furgoneta = precio_furgoneta;
	}
	
	//Constructores
	public Furgoneta(int id_coche, int id_oficina, String matricula, String marca, String modelo, int km, String tipo,
			boolean alquilado, Tamano tamano, double precio_furgoneta) {
		super(id_coche, id_oficina, matricula, marca, modelo, km, tipo, alquilado);
		this.tamano = tamano;
		this.precio_furgoneta = precio_furgoneta;
	}
	public Furgoneta(Tamano tamano, double precio_furgoneta) {
		super();
		this.tamano = tamano;
		this.precio_furgoneta = precio_furgoneta;
	}
	public Furgoneta() {
	}
	
	
}
