package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarMySQL {
	
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

	public static void desconectarMySQL() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
	
}
