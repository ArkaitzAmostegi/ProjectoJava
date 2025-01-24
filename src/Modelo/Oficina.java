package Modelo;

public class Oficina {

	 ///Atributos
    private String id_oficina;
    private String nombre;
    private String calle;
    private int telefono;
    private String email;

    //Getters & Setters
    public String getId_oficina() {
        return id_oficina;
    }
    public void setId_oficina(String id_oficina) {
        this.id_oficina = id_oficina;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //Constructor
    public Oficina(String id_oficina, String nombre, String calle, int telefono, String email) {
        this.id_oficina = id_oficina;
        this.nombre = nombre;
        this.calle = calle;
        this.telefono = telefono;
        this.email = email;
    }
    public Oficina() {
    }
}

