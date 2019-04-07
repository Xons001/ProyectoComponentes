package mySQL;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
			infoDB.setTypeQuery("Select");
			infoDB.setQuery(query);
			infoDB.setFecha(infoDB.getDate());
			infoDB.setNameUser(this.username);
			infoDB.setNumRegisters(cont);

			evento.firePropertyChange("Select", null, infoDB);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String query) {
		try {
			DatosDataBase infoDB = new DatosDataBase();

			statement = conn.createStatement();
			//Este es executeUpdate
			statement.executeUpdate(query);

			infoDB.setNameDataBase(this.database);
			infoDB.setTypeQuery("Insert");
			infoDB.setQuery(query);
			infoDB.setFecha(infoDB.getDate());
			infoDB.setNameUser(this.username);
			infoDB.setNumRegisters(1);

			evento.firePropertyChange("Insert", null, infoDB);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String query) {
		try {
			DatosDataBase infoDB = new DatosDataBase();

			statement = conn.createStatement();

			infoDB.setNameDataBase(this.database);
			infoDB.setTypeQuery("Update");
			infoDB.setQuery(query);
			infoDB.setFecha(infoDB.getDate());
			infoDB.setNameUser(this.username);
			infoDB.setNumRegisters(statement.executeUpdate(query));

			evento.firePropertyChange("Update", null, infoDB);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String query) {
		try {
			DatosDataBase infoDB = new DatosDataBase();

			statement = conn.createStatement();

			infoDB.setNameDataBase(this.database);
			infoDB.setTypeQuery("Delete");
			infoDB.setQuery(query);
			infoDB.setFecha(infoDB.getDate());
			infoDB.setNameUser(this.username);
			infoDB.setNumRegisters(statement.executeUpdate(query));

			evento.firePropertyChange("Delete", null, infoDB);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void queryConsulta(String nameDataBase, String data) {
		if (data.equalsIgnoreCase("Select") || data.equalsIgnoreCase("Insert") || data.equalsIgnoreCase("Update") || data.equalsIgnoreCase("Delete")) {
			for (DatosDataBase ddb : event.getEnter()) {
				if(ddb.getTypeQuery().equals(data) && ddb.getNameDataBase().equals(nameDataBase)) {
					System.out.println(ddb.getNameUser() + " || " + ddb.getQuery() + " || " + ddb.getDate());
				}
			}
		} else {
			for (DatosDataBase ddb : event.getEnter()) {
				if(ddb.getNameUser().equals(data) && ddb.getNameDataBase().equals(nameDataBase)) {
					System.out.println(ddb.getTypeQuery() + " || " + ddb.getQuery() + " || " + ddb.getDate());
				}
			}
		}
	}


	public void queryConsulta(String nameDataBase, String nameUser, String typeQuery) {
		for (DatosDataBase ddb : event.getEnter()) {
			if(ddb.getNameUser().equals(nameUser) && ddb.getTypeQuery().equals(typeQuery) && ddb.getNameDataBase().equals(nameDataBase)) {
				System.out.println(ddb.getQuery() + " || " + ddb.getDate());
			}
		}
	}

	// Constructor de evento
	public ConectarMySQL() {
		evento = new PropertyChangeSupport(this);
		event = new QueryConsultas();
		addPropertyChangeListener(event);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		evento.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		evento.removePropertyChangeListener(listener);
	}
}