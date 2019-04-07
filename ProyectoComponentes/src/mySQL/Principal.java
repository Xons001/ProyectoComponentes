package mySQL;

import modelos.DatosDataBase;

public class Principal {

	public static void main(String[] args) {
		ConectarMySQL con = new ConectarMySQL("forhonor", "localhost", "8888", "root", "");
		con.conectarMySQL();
		DatosDataBase dbClass = new DatosDataBase();
		System.out.println(dbClass.getDate());
	}

}
