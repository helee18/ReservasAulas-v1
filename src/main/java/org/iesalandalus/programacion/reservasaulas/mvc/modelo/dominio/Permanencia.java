package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Permanencia {
	private LocalDate dia;
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //despues usar constante directamente, no funcion para formatear
	
	private Tramo tramo; 
	
	public Permanencia(LocalDate dia, Tramo tramo) {
		setDia(dia);
		setTramo(tramo);
	}
	
	public Permanencia(Permanencia permanenciaOriginal) {
		//Not Null
		if (permanenciaOriginal == null)
			throw new NullPointerException ("ERROR: No se puede copiar una permanencia nula.");
		
		setDia(permanenciaOriginal.getDia());
		setTramo(permanenciaOriginal.getTramo());
	}
	
	public LocalDate getDia() {
		return dia;
	}
	
	private void setDia(LocalDate dia) {
		if (dia == null)
			throw new NullPointerException ("ERROR: El día de una permanencia no puede ser nulo.");
			
		this.dia = dia; //FORMATO_DIA.format(dia);// no se por que no lo coge
	}

	public Tramo getTramo() {
		return tramo;
	}

	private void setTramo(Tramo tramo) {
		if (tramo == null)
			throw new NullPointerException("ERROR: El tramo de una permanencia no puede ser nulo.");
		
		this.tramo = tramo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dia, tramo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permanencia other = (Permanencia) obj;
		return Objects.equals(dia, other.dia) && tramo == other.tramo;
	}

	@Override
	public String toString() {
		return "dia=" + FORMATO_DIA.format(dia) + ", tramo=" + getTramo();
	}
	
	
	
}
