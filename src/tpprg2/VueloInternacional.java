package tpprg2;

import java.util.List;

public class VueloInternacional extends Vuelo {

	

	public VueloInternacional(String origen, String destino, String fecha, int precioPasaje, String horaSalida,
			String horaLlegada, List<Asiento> asientos, String aeropuertoSalida, String aeropuertoLlegada,
			Asiento asiento, List<Pasajero> registroPasajeros, Seccion seccion, int tripulantes) {
		super(origen, destino, fecha, precioPasaje, horaSalida, horaLlegada, asientos, aeropuertoSalida, aeropuertoLlegada,
				asiento, registroPasajeros, seccion, tripulantes);
		// TODO Auto-generated constructor stub
	}



	public void agregarEscala(String string) {
		// TODO Auto-generated method stub
		
	}

}
