package Modelo;

public class Usuario {
	  
	//Atributos
    private String dni;
    private String nombre;
    private String sexo;
    private String telefono;
    private String email;
    private String contrasena;
    private boolean administrador;

    //Getters & Setters
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public boolean isAdministrador() {
        return administrador;
    }
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    //Constructor
	public Usuario(String dni, String nombre, String sexo, String telefono, String email, String contrasena,
			boolean administrador) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.sexo = sexo;
		this.telefono = telefono;
		this.email = email;
		this.contrasena = contrasena;
		this.administrador = administrador;
	}
	public Usuario() {
	}
   
}
