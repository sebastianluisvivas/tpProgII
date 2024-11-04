package tpprg2;
import java.util.List;
import java.util.ArrayList;

public class Vuelo {
	protected String origen;
	protected String destino;
	protected String fecha;
	protected int precioPasaje;
	protected String horaSalida;
	protected String horaLlegada;
    protected String aeropuertoSalida;
	protected String aeropuertoLlegada;
    protected List<Asiento> asientos; // Lista de asientos

    protected Asiento asiento;
    protected List<Pasajero> registroPasajeros;
    protected Seccion seccion;
    protected int tripulantes;
	
    
	public Vuelo(String origen, String destino, String fecha, int precioPasaje, String horaSalida, String horaLlegada,
			List<Asiento> asientos, String aeropuertoSalida, String aeropuertoLlegada, Asiento asiento, List<Pasajero> registroPasajeros,
			Seccion seccion, int tripulantes) {
		
		
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
		this.precioPasaje = precioPasaje;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.aeropuertoSalida = aeropuertoSalida;
		this.aeropuertoLlegada = aeropuertoLlegada;
		this.asiento = asiento;
		this.asientos = asientos;
		this.registroPasajeros =  new ArrayList<>();
		this.seccion = seccion;
		this.tripulantes = tripulantes;
	}


	public void cancelarPasaje (String dni, String codigo, int numeroAsiento) {
		
	}
	
	// Método para verificar si un asiento está disponible
	public boolean asientoEstaDisponible(int nroAsiento) {
        if (nroAsiento < 0 || nroAsiento >= asientos.size()) {
            throw new IllegalArgumentException("Número de asiento inválido.");
        }
        return asientos.get(nroAsiento).isDisponible();
    }

	
	
	public void registrarPasajero(Pasajero pasajero) {
        registroPasajeros.add(pasajero); // Añade el pasajero al registro

        // Marcar el asiento como ocupado
        Asiento asiento = asientos.get(pasajero.nroAsiento); // Acceso directo al atributo nroAsiento
        asiento.ocupar();
    }

	
    
    
    
}
