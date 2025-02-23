package Modelo;

public abstract class Vehiculo {
    
    //Atributos
    private int id_coche;
    private int id_oficina;
    private String matricula;
    private String marca;
    private String modelo;
    private int km;
    private String tipo;

    
    //Getters & Setters
    public int getId_coche() {
        return id_coche;
    }
    public void setId_coche(int id_coche) {
        this.id_coche = id_coche;
    }
    public int getId_oficina() {
        return id_oficina;
    }
    public void setId_oficina(int id_oficina) {
        this.id_oficina = id_oficina;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getKm() {
        return km;
    }
    public void setKm(int km) {
        this.km = km;
    }
    public String gettipo() {
        return tipo;
    }
    public void settipo(String tipo) {
        this.tipo = tipo;
    }    
    public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	//Constructores
   
    public Vehiculo() {
    }
    
	public Vehiculo(int id_coche, int id_oficina, String matricula, String marca, String modelo, int km, String tipo) {
		super();
		this.id_coche = id_coche;
		this.id_oficina = id_oficina;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.km = km;
		this.tipo = tipo;
	}
}

