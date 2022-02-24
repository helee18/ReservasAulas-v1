package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {
	private String nombre;
	
	public Aula(String nombre) {
		setNombre(nombre);
	}
	
	public Aula(Aula aulaOriginal) {
		if (aulaOriginal == null)
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		
		setNombre(aulaOriginal.getNombre());
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null)
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		
		if (nombre.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede estar vacío.");
		
		nombre = nombre.replaceAll("^\\s*","");
		nombre = nombre.replaceAll("\\s*$","");
		
		
		nombre = nombre.trim().replaceAll("\\s{2,}"," ");
 		
		// Mayusculas y minusculas
		String nombreNuevo = "";
		String[] palabras = nombre.split(" "); 
		
		for (int i=0; i<=palabras.length-1; i++) { 
			if (i == 0)
				palabras[i] = palabras[i].toUpperCase().charAt(0) + palabras[i].substring(1).toLowerCase();
			else
				palabras[i] = palabras[i].substring(0).toLowerCase();
				
			nombreNuevo = nombreNuevo + palabras[i] + " "; 
		}
		
		this.nombre = nombreNuevo.trim();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre Aula=" + getNombre();
	}
	
	
}
