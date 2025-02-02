package Modelo;

public class Monovolumen extends Vehiculo{

	
	//Atributos
	private int numPuertas;
	private double precio_monovolumen;
	
	
	//Getters & Setters
	public int getNumPuertas() {
	    return numPuertas;
	}
	public void setNumPuertas(int numPuertas) {
	    this.numPuertas = numPuertas;
	}	
	public double getPrecio_monovolumen() {
		return precio_monovolumen;
	}
	public void setPrecio_monovolumen(double precio_monovolumen) {
		this.precio_monovolumen = precio_monovolumen;
	}
	
	//Constructores
	public Monovolumen(int id_coche, int id_oficina, String matricula, String marca, String modelo, int km, String tipo,
			boolean alquilado, int numPuertas, double precio_monovolumen) {
		super(id_coche, id_oficina, matricula, marca, modelo, km, tipo, alquilado);
		this.numPuertas = numPuertas;
		this.precio_monovolumen = precio_monovolumen;
	}
	public Monovolumen(int numPuertas, double precio_monovolumen) {
		super();
		this.numPuertas = numPuertas;
		this.precio_monovolumen = precio_monovolumen;
	}
	public Monovolumen() {
	}
	
}

