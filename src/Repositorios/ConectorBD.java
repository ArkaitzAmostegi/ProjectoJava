package Repositorios;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBD {

	//Método para conectar a la base de datos
	private static Connection conexion;
	
    public static void conectar(){

        try{
            //Cargamos el driver, el driver es la libreria que nos permite conectarnos a la BDD
        	Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado");        
            try{
            //Establecemos la conexion con la BDD           
            //La BDD se encuentra en el localhost(en mi ordenador)
            //El usuario es root y la contraseña es 1DAW3_BBDD
            //La conexion se hace a traves del puerto 3306
            //La BDD se llama AJA
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/AJA","root","1DAW3_BBDD");

            System.out.println("Conexion establecida");
          
            }catch(Exception e){
            System.out.println("Error en la conexion");
            }
        }catch(Exception e){
            System.out.println("Error en el driver");
        }
    }
    
    //Método getConexion
    public static Connection getconexion(){
    	return conexion;
    }
    
    //Método desconectar para desconectarnos de la BDD;
    public static void desconectar() {
    	try {
			conexion.close();
			System.out.println("Se ha desconectado satisfactoramente de la base de datos");
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
		}
    }
}
