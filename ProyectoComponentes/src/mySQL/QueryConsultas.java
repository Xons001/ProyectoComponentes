package mySQL;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import modelos.DatosDataBase;

public class QueryConsultas implements PropertyChangeListener{

	private ArrayList<DatosDataBase> enter = new ArrayList<>();
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		enter.add((DatosDataBase)event.getNewValue());
	}

	public ArrayList<DatosDataBase> getEnter() {
		return enter;
	}

	public void setEnter(ArrayList<DatosDataBase> enter) {
		this.enter = enter;
	}

	public DatosDataBase getQuery (int i) {
		return enter.get(i);
	}
	
	public void setDatosDataBase(DatosDataBase regis, int i) {
		this.enter.set(i, regis);
	}
}
