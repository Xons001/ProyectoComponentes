package mySQL;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.DatosDataBase;

/**
 * 
 * @author Sean Saez
 *
 */
public class ConectarMySQL implements Serializable{

	// Librería de MySQL
	private String driver = "com.mysql.jdbc.Driver";

	// Nombre de la base de datos
	private String database;

	// Host
	private String hostname;

	// Puerto
	private String port;

	// Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
	private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

	// Nombre de usuario
	private String username;

	// Clave de usuario
	private String password;

	// Conexion
	private static Connection conn = null;

	// Para crear query
	private Statement statement;
	
	// Resultado de la query
	private ResultSet resultset;
	
	// Llamando el objeto de los eventos
	private static QueryConsultas event;
	
	private PropertyChangeSupport evento;
	
	// Constructor vacio
	public ConectarMySQL() {

	}

	//Constructor lleno
	public ConectarMySQL(String database, String hostname, String port, String username,
			String password) {
		this.database = database;
		this.hostname = hostname;
		this.port = port;
		this.username = "root";
		this.password = "";
	}

	// Getters
	public String getDriver() {
		return driver;
	}
	public String getDatabase() {
		return database;
	}
	public String getHostname() {
		return hostname;
	}
	public String getPort() {
		return port;
	}
	public String getUrl() {
		return url;
	}

	public Statement getStatement() {
		return statement;
	}

	public ResultSet getResultset() {
		return resultset;
	}

	public static QueryConsultas getEvent() {
		return event;
	}

	public PropertyChangeSupport getEvento() {
		return evento;
	}

	// Setters
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public void setResultset(ResultSet resultset) {
		this.resultset = resultset;
	}

	public static void setEvent(QueryConsultas event) {
		ConectarMySQL.event = event;
	}

	public void setEvento(PropertyChangeSupport evento) {
		this.evento = evento;
	}
	
	public Connection conectarMySQL() {
		//Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public void desconectar() {
		try {
			conn.close();
			resultset.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void select(String query) {
		try {
			DatosDataBase infoDB = new DatosDataBase();
			
			statement = conn.createStatement();
			resultset = statement.executeQuery(query);
			int cont = 0;
			while (resultset.next()) {
				cont++;
			}
			
			infoDB.setNameDataBase(this.database);
			//infoDB.set
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
