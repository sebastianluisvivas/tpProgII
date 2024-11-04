package tpprg2;

public class Pasajero extends Cliente{
    protected int nroAsiento;
    
    
	public Pasajero(int dni, String nombre, String telefono, int nroAsiento) {
        super(dni, nombre, telefono);
        this.nroAsiento = nroAsiento;
    }

    
}
