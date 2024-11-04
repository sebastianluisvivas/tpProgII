package tpprg2;

import java.util.ArrayList;
import java.util.List;


public class vueloPublico extends Vuelo {
	//private List<Asiento> asientos;
	private double valorRefrigerio;
    private double[] precios;
    private int[] cantAsientos;
	
	
	public vueloPublico(String origen, String destino, String fecha, int precioPasaje, String horaSalida,
			String horaLlegada, String aeropuertoSalida, String aeropuertoLlegada, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos, Asiento asiento, List<Pasajero> registroPasajeros, Seccion seccion ) {
	
    super(origen, destino, fecha, precioPasaje, horaSalida, horaLlegada,new ArrayList<>() ,aeropuertoSalida, aeropuertoLlegada,
		          asiento, registroPasajeros, seccion, tripulantes);
	
	this.valorRefrigerio=valorRefrigerio;
	this.precios=precios;
	this.cantAsientos=cantAsientos;
	
	
    //this.asientos = new ArrayList<>();

	
	// Crear los asientos en función de `cantAsientos`
    for (int i = 0; i < cantAsientos[0] + cantAsientos[1]; i++) {
        this.asientos.add(new Asiento());
    }
}	

}

