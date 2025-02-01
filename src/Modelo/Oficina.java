package Modelo;

public class Oficina {

	 ///Atributos
    private String id_oficina;
    private String calle;
    private String ciudad;
    private String pais;
    private String nombre;
    private String telefono;
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
    
    public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	//Constructor
   
	public Oficina() {
    }
	public Oficina(String id_oficina, String calle, String ciudad, String pais, String nombre, String telefono,
			String email) {
		super();
		this.id_oficina = id_oficina;
		this.calle = calle;
		this.ciudad = ciudad;
		this.pais = pais;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
	}
}

