package Modelo;

public class Usuario_Vehiculos {

	 //Atributos
    private int dni;
    private int id_coche;
    private String fecha;
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
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
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
    public Usuario_Vehiculos(int dni, int id_coche, String fecha, String lugarEntregaRecogida, boolean conSinConductor) {
        this.dni = dni;
        this.id_coche = id_coche;
        this.fecha = fecha;
        this.lugarEntregaRecogida = lugarEntregaRecogida;
        this.conSinConductor = conSinConductor;
    }
    public Usuario_Vehiculos() {
    }
}

