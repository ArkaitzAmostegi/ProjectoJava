package Modelo;

public class Usuario_Vehiculos {

	 //Atributos
    private int dni;
    private int id_coche;
    private String fecha_entrega;
    private String fecha_recogida;
    private String lugarEntregaRecogida;
    private boolean conSinConductor;

    
    //Getters & Setters
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public int getId_coche() {
        return id_coche;
    }
    public void setId_coche(int id_coche) {
        this.id_coche = id_coche;
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
	public String getLugarEntregaRecogida() {
        return lugarEntregaRecogida;
    }
    public void setLugarEntregaRecogida(String lugarEntregaRecogida) {
        this.lugarEntregaRecogida = lugarEntregaRecogida;
    }
    public boolean isConSinConductor() {
        return conSinConductor;
    }
    public void setConSinConductor(boolean conSinConductor) {
        this.conSinConductor = conSinConductor;
    }
    
    //Constructores
    
    public Usuario_Vehiculos() {
    }
	public Usuario_Vehiculos(int dni, int id_coche, String fecha_entrega, String fecha_recogida,
			String lugarEntregaRecogida, boolean conSinConductor) {
		super();
		this.dni = dni;
		this.id_coche = id_coche;
		this.fecha_entrega = fecha_entrega;
		this.fecha_recogida = fecha_recogida;
		this.lugarEntregaRecogida = lugarEntregaRecogida;
		this.conSinConductor = conSinConductor;
	}
}

