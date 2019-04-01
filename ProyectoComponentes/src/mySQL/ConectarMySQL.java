package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarMySQL {
	
	private static Connection cnx = null;
	private String ipUser;
	private String nameDB;
	private String user;
	private String pass;
	
	public ConectarMySQL() {
		
	}
	
	public ConectarMySQL(String ipUser, String nameDB, String user, String pass) {
		this.ipUser = ipUser;
		this.nameDB = nameDB;
		this.user = user;
		this.pass = pass;
	}

	public String getIpUser() {
		return ipUser;
	}

	public String getNameDB() {
		return nameDB;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public void setIpUser(String ipUser) {
		this.ipUser = ipUser;
	}

	public void setNameDB(String nameDB) {
		this.nameDB = nameDB;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public static Connection obtener(String ipUser, String nameDB, String user, String pass) throws SQLException,  ClassNotFoundException {
		 
		if (cnx == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cnx = DriverManager.getConnection("jdbc:mysql://"+ ipUser +"/"+ nameDB , user, pass);
			} catch (SQLException ex) {
				throw new SQLException(ex);
			} catch (ClassNotFoundException ex) {
				throw new ClassCastException(ex.getMessage());
			}
		}
		return cnx;
	}

	public static void cerrar() throws SQLException {
		if (cnx != null) {
			cnx.close();
		}
	}
}
