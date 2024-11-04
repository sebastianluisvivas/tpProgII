package tpprg2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class Aerolinea implements IAerolinea {
	private String nombre;
	private String cuit;
	private HashMap<Integer, Cliente> clientes = new HashMap<>();
	private HashMap<String, Aeropuerto> aeropuertos = new HashMap<>();
	private HashMap<String, Vuelo> vuelos = new HashMap<>();
    private int contadorVuelosPrivados = 1;
    
    
	public Aerolinea (String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit=cuit;
	}
	@Override
	
	public void registrarCliente(int dni, String nombre, String telefono) {
		if (this.clientes.containsKey(dni))
			throw new RuntimeException("El cliente ya está registrado");
		clientes.put(dni, new Cliente(dni,nombre,telefono));
	}
	
	@Override
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
		if(this.aeropuertos.containsKey(nombre))
			throw new RuntimeException("Aeropuerto ya registrado");
		aeropuertos.put(nombre, new Aeropuerto(nombre, pais, provincia, direccion));
}
	
	
	
	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
	        double valorRefrigerio, double[] precios, int[] cantAsientos) {

	    boolean destinoRegistrado = false; // Variable para verificar si el destino está registrado

	    for (Aeropuerto aeropuerto : aeropuertos.values()) {
	        if (aeropuerto.nombre.equalsIgnoreCase(destino)) {
	            destinoRegistrado = true; // Marcamos que el destino está registrado
	            
	            Asiento asiento = new Asiento();
	            String codVuelo = fecha + "-" + origen + "-" + destino + "-PUB";

	            List<Pasajero> registroPasajeros = new ArrayList<>();
	            Seccion seccion = new Seccion();

	            vueloPublico vuelo = new vueloPublico(origen, destino, fecha, 0, origen, destino, fecha, origen,
	                    tripulantes, valorRefrigerio, precios, cantAsientos,
	                    asiento, registroPasajeros, seccion);
	            vuelos.put(codVuelo, vuelo);
	            return codVuelo; // Retornamos el código del vuelo si se registró correctamente
	        }
	    }
	    
	    // Si salimos del bucle y el destino no está registrado, lanzamos una excepción
	    if (!destinoRegistrado) {
	        throw new RuntimeException("Destino no registrado");
	    }
	    
	    return null; // Esto nunca debería alcanzarse debido a la excepción
	}
	
	
		
	
	@Override
	public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
		
		return null;
		
	}
	
	
	
	@Override
	public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio,
			int dniComprador, int[] acompaniantes) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Date fechaVuelo;
	    
	    try {
	        fechaVuelo = sdf.parse(fecha);
	        
	    } catch (ParseException e) {
	        throw new RuntimeException("Formato de fecha inválido. Use dd/MM/yyyy.");
	    }

	    // 2. Obtener la fecha actual
	    Date fechaActual = new Date();

	    // 3. Comparar la fecha del vuelo con la fecha actual
	    if (fechaVuelo.before(fechaActual)) {
	        throw new RuntimeException("La fecha de vuelo caducó. No se puede vender un vuelo en una fecha anterior.");
	    }
	    
	    Cliente comprador = clientes.get(dniComprador);
	    if (comprador == null) {
	        throw new RuntimeException("El cliente comprador no está registrado.");
	    }
	    
	    
	    // 3. Verificar si los acompañantes están registrados
	    for (int dni : acompaniantes) {
	        if (clientes.get(dni) == null) {
	            throw new RuntimeException("Uno o más acompañantes no están registrados.");
	        }
	    }
	    
	 // 4. Generar un código de vuelo único que termine en "-PRI"
	    String codigoVuelo = contadorVuelosPrivados + "-PRI";
	    contadorVuelosPrivados++;
	    
	    // 5. Crear y registrar el vuelo privado
	    Vuelo vueloPrivado = new Vuelo(origen, destino, fecha, (int) precio, null, null, new ArrayList<>(),
                origen, destino, null, new ArrayList<>(), null, tripulantes);
	    vuelos.put(codigoVuelo, vueloPrivado);

	    // 6. Retornar el código del vuelo
	    return codigoVuelo;
	}
	
	
	
	@Override
	public Map<Integer, String> asientosDisponibles(String codVuelo) {
	    Vuelo vuelo = vuelos.get(codVuelo);
	    if (vuelo == null) {
	        throw new RuntimeException("El vuelo no existe.");
	    }

	    Map<Integer, String> asientosDisponibles = new HashMap<>();
	    for (int i = 0; i < vuelo.asientos.size(); i++) {
	        Asiento asiento = vuelo.asientos.get(i);
	        if (asiento.isDisponible()) {
	            asientosDisponibles.put(i, "Disponible");
	        }
	    }

	    return asientosDisponibles;
	}
	
	 // Método para buscar un cliente por su DNI sin usar get directamente
    public Cliente buscarCliente(int dni) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.coincideDni(dni)) {
                return cliente;
            }
        }
        throw new RuntimeException("El cliente no está registrado.");
    }
	
	
	@Override
	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		 Cliente cliente = buscarCliente(dni);

		    // Verificar si el vuelo existe
		    Vuelo vuelo = vuelos.get(codVuelo);
		    if (vuelo == null) {
		        throw new RuntimeException("El vuelo no existe.");
		    }

		    // Verificar si el asiento está disponible
		    if (!vuelo.asientoEstaDisponible(nroAsiento)) {
		        throw new RuntimeException("El asiento ya está ocupado.");
		    }

		    // Registrar al pasajero
		    Pasajero pasajero = new Pasajero(cliente.dni, cliente.nombre, cliente.telefono, nroAsiento);
		    vuelo.registrarPasajero(pasajero);

		    return nroAsiento;
		}
	    
	    
	
	@Override
	public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cancelarPasaje(int dni, int codPasaje) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<String> cancelarVuelo(String codVuelo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double totalRecaudado(String destino) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String detalleDeVuelo(String codVuelo) {
		// TODO Auto-generated method stub
		return null;
	}






	}

