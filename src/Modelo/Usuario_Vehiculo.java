package Modelo;

public class Usuario_Vehiculo {

	 //Atributos
    private String dni;
    private int id_coche;
    private double precio_total;
    private String fecha_entrega;
    private String fecha_recogida;
    private String lugarEntrega;
    private String lugarRecogida;
    private boolean conConductor;
    private boolean alquilado;

    
    //Getters & Setters
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public int getId_coche() {
        return id_coche;
    }
    public void setId_coche(int id_coche) {
        this.id_coche = id_coche;
    }
    public double getPrecio_total() {
		return precio_total;
	}
	public void setPrecio_total(double precio_total) {
		this.precio_total = precio_total;
	}
	public boolean isConConductor() {
		return conConductor;
	}
	public void setConConductor(boolean conConductor) {
		this.conConductor = conConductor;
	}
	public String getFecha_entrega() {
		return fecha_entrega;
	}
	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	public String getFecha_recogida() {
		return fecha_recogida;
	}
	public void setFecha_recogida(String fecha_recogida) {
		this.fecha_recogida = fecha_recogida;
	}
    public String getLugarEntrega() {
		return lugarEntrega;
	}
	public void setLugarEntrega(String lugarEntrega) {
		this.lugarEntrega = lugarEntrega;
	}
	public String getLugarRecogida() {
		return lugarRecogida;
	}
	public void setLugarRecogida(String lugarRecogida) {
		this.lugarRecogida = lugarRecogida;
	}
	public boolean isAlquilado() {
		return alquilado;
	}
	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}
    
    //Constructores
	public Usuario_Vehiculo() {
    }
	public Usuario_Vehiculo(String dni, int id_coche, double precio_total, String fecha_entrega, String fecha_recogida,
			String lugarEntrega, String lugarRecogida, boolean conConductor, boolean alquilado) {
		super();
		this.dni = dni;
		this.id_coche = id_coche;
		this.precio_total = precio_total;
		this.fecha_entrega = fecha_entrega;
		this.fecha_recogida = fecha_recogida;
		this.lugarEntrega = lugarEntrega;
		this.lugarRecogida = lugarRecogida;
		this.conConductor = conConductor;
		this.alquilado = alquilado;
	}
	
	
}

