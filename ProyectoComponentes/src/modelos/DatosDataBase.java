package modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatosDataBase {

	private String nameDataBase;
	private String nameUser;
	private String query;
	private String typeQuery;
	private Date fecha;
	private int numRegisters;
	
	public DatosDataBase() {
		
	}
	
	public DatosDataBase(String nameDataBase, String nameUser, String query, String typeQuery, Date fecha,
			int numRegisters) {
		this.nameDataBase = nameDataBase;
		this.nameUser = nameUser;
		this.query = query;
		this.typeQuery = typeQuery;
		this.fecha = fecha;
		this.numRegisters = numRegisters;
	}
	
	//Getters
	public String getNameDataBase() {
		return nameDataBase;
	}
	public String getNameUser() {
		return nameUser;
	}
	public String getQuery() {
		return query;
	}
	public String getTypeQuery() {
		return typeQuery;
	}
	public Date getFecha() {
		return fecha;
	}
	public int getNumRegisters() {
		return numRegisters;
	}
	
	//Setters
	public void setNameDataBase(String nameDataBase) {
		this.nameDataBase = nameDataBase;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public void setTypeQuery(String typeQuery) {
		this.typeQuery = typeQuery;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setNumRegisters(int numRegisters) {
		this.numRegisters = numRegisters;
	}
	
	@Override
	public String toString() {
		return "DatosDataBase [nameDataBase=" + nameDataBase + ", nameUser=" + nameUser + ", query=" + query
				+ ", typeQuery=" + typeQuery + ", fecha=" + fecha + ", numRegisters=" + numRegisters + "]";
	}
	
	//Puede que el import de data sea el otro
	public String getDate() {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(new Date());
		return date;
	}
	
}
