package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public enum Tramo {
	MANANA("0.- Mañana"),TARDE("1.- Tarde"); //opciones
	
	private String cadenaAMostrar; 
	
	private Tramo (String cadenaAMostrar) {
		this.cadenaAMostrar = cadenaAMostrar; //Referencia a Mañana o Tarde ()
    }
	
	public String toString() {
        return cadenaAMostrar; // Devuelve Mañana o Tarde ()
    }
}
