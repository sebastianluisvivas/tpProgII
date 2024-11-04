package tpprg2;

public class Asiento {

	    private boolean disponible;

	    public Asiento() {
	        this.disponible = true; // El asiento comienza disponible
	    }

	    public boolean isDisponible() {
	        return disponible;
	    }

	    public void ocupar() {
	        this.disponible = false;
	    }
	}
	
